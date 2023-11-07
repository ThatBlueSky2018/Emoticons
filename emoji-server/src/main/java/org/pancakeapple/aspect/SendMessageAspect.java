package org.pancakeapple.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.pancakeapple.annotation.SendMessage;
import org.pancakeapple.constant.MessageConstant;
import org.pancakeapple.context.BaseContext;
import org.pancakeapple.dto.interaction.CommentDTO;
import org.pancakeapple.entity.interaction.Message;
import org.pancakeapple.enumeration.ContentType;
import org.pancakeapple.enumeration.MessageType;
import org.pancakeapple.mapper.emoji.EmojiMapper;
import org.pancakeapple.mapper.interaction.CommentMapper;
import org.pancakeapple.mapper.interaction.MessageMapper;
import org.pancakeapple.vo.emoji.EmojiDetailVO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 发送消息的切面类
 * 将消息内容发送到RabbitMQ，由消费者实时推送消息
 */
@Aspect
@Component
@Slf4j
public class SendMessageAspect {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private EmojiMapper emojiMapper;

    /**
     * 提取切入点
     * 在service包及其子包下，带有@SendMessage注解
     */
    @Pointcut("execution(* org.pancakeapple.service..*(..)) && @annotation(org.pancakeapple.annotation.SendMessage)")
    public void sendMessagePointCut(){}

    /**
     * Service方法返回之后，构造一条消息，并发送到消息队列
     * @param joinPoint 切入点
     */
    @AfterReturning("sendMessagePointCut()")
    public void addAndSendMessage(JoinPoint joinPoint) {
        //1.获取当前被拦截的方法上的用户行为
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        SendMessage sendMessage = signature.getMethod().getAnnotation(SendMessage.class);
        MessageType messageType = sendMessage.messageType();

        //2.获取参数数组
        Object[] args = joinPoint.getArgs();

        //3.根据不同的消息类型，构造消息并发送
        if(messageType == MessageType.COMMENT) {
            CommentDTO commentDTO = (CommentDTO) args[0];
            addAndSendCommentMessage(commentDTO);
        }

    }

    //抽取方法，此方法功能是构造一条评论消息，并发送到消息队列
    private void addAndSendCommentMessage(CommentDTO commentDTO) {
        EmojiDetailVO emojiDetailVO = emojiMapper.getById(commentDTO.getEmojiId());
        if(Objects.equals(BaseContext.getCurrentId(), emojiDetailVO.getCreateUser())) {
            //如果是评论自己的作品，不需要发送消息
            return;
        }
        Message message = Message.builder()
                .messageType(MessageType.COMMENT.toString())
                .isRead(0)
                .senderId(BaseContext.getCurrentId())
                .receiverId(emojiDetailVO.getCreateUser())
                .contentType(ContentType.TEXT)
                .content(commentDTO.getContent())
                .build();
        messageMapper.insert(message);
        rabbitTemplate.convertAndSend(MessageConstant.COMMENT_QUEUE,message);
    }
}
