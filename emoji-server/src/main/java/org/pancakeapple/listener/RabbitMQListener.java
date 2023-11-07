package org.pancakeapple.listener;

import lombok.extern.slf4j.Slf4j;
import org.pancakeapple.entity.interaction.Message;
import org.pancakeapple.enumeration.MessageType;
import org.pancakeapple.mapper.user.UserMapper;
import org.pancakeapple.session.SseSession;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 此监听器监听RabbitMQ中的消息，并向客户端实时推送消息
 */
@Component
@Slf4j
public class RabbitMQListener {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SseSession sseSession;

    @RabbitListener(queues = "test.queue")
    public void listenSimpleQueue(String msg) {
        System.out.println("消费者接收到test.queue的消息:【"+msg+"】");
    }

    /**
     * 监听评论消息队列的消息
     * @param message 消息内容
     */
    @RabbitListener(queues = "comment.queue")
    public void listenCommentQueue(Message message) throws IOException {
        String senderName = userMapper.getById(message.getSenderId()).getUsername();
        String receiverName = userMapper.getById(message.getReceiverId()).getUsername();
        log.info("消息队列消费者接收到comment.queue的消息：【"+message+"】");
        sseSession.send(senderName,receiverName, MessageType.COMMENT);
    }
}
