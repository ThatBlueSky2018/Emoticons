package org.pancakeapple.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.pancakeapple.annotation.AutoIncrease;
import org.pancakeapple.dto.interaction.CommentDTO;
import org.pancakeapple.enumeration.BehaviorType;
import org.pancakeapple.mapper.emoji.EmojiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 点赞、评论、收藏数量自动增长切面类
 */
@Aspect
@Component
@Slf4j
public class AutoIncreaseAspect {
   @Autowired
    private EmojiMapper emojiMapper;

    /**
     * 切入点
     * 在service包及其子包下，带有@AutoIncrease注解
     */
    @Pointcut("execution(* org.pancakeapple.service..*(..)) && @annotation(org.pancakeapple.annotation.AutoIncrease)")
    public void autoIncreasePointCut(){}

    /**
     * 返回后通知比较合理
     * @param joinPoint 切入点
     */
    @AfterReturning("autoIncreasePointCut()")
    public void autoIncrease(JoinPoint joinPoint) {
        //1.获取当前被拦截的方法上的用户行为
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        AutoIncrease autoFill = signature.getMethod().getAnnotation(AutoIncrease.class);
        BehaviorType behaviorType = autoFill.type();

        //2.获取参数数组
        Object[] args = joinPoint.getArgs();

        //3.根据不同的操作，完成自动增长
        if(behaviorType == BehaviorType.CLICK) {
            //确保id为Mapper方法的第一个参数
            emojiMapper.increaseHits((Long) args[0]);
            log.info("表情包点击量自动增长：{}",args[0]);
        } else if (behaviorType == BehaviorType.FAVORITE) {
            emojiMapper.increaseFavorite((Long)args[0]);
            log.info("表情包收藏量自动增长：{}",args[0]);
        } else if (behaviorType == BehaviorType.COMMENT) {
            emojiMapper.increaseComment(((CommentDTO)args[0]).getEmojiId());
            log.info("表情报评论数量自动增长：{}",((CommentDTO)args[0]).getEmojiId());
        }
    }
}
