package org.pancakeapple.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.pancakeapple.annotation.AutoDecrease;
import org.pancakeapple.enumeration.BehaviorType;
import org.pancakeapple.mapper.emoji.EmojiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 评论、收藏数量自动减少切面类
 */
@Aspect
@Component
@Slf4j
public class AutoDecreaseAspect {
   @Autowired
    private EmojiMapper emojiMapper;

    /**
     * 切入点
     * 在service包及其子包下，带有@AutoIncrease注解
     */
    @Pointcut("execution(* org.pancakeapple.service..*(..)) && @annotation(org.pancakeapple.annotation.AutoDecrease)")
    public void autoIncreasePointCut(){}

    /**
     * 返回后通知
     * @param joinPoint 切入点
     */
    @AfterReturning("autoIncreasePointCut()")
    public void autoIncrease(JoinPoint joinPoint) {
        //1.获取当前被拦截的方法上的用户行为
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        AutoDecrease autoDecrease = signature.getMethod().getAnnotation(AutoDecrease.class);
        BehaviorType behaviorType = autoDecrease.type();

        //2.获取参数数组
        Object[] args = joinPoint.getArgs();

        //3.根据不同的操作，完成自动减少
        if (behaviorType == BehaviorType.FAVORITE) {
            emojiMapper.decreaseFavorite((Long)args[0]);
            log.info("表情包收藏量自动增长：{}",args[0]);
        }
    }
}
