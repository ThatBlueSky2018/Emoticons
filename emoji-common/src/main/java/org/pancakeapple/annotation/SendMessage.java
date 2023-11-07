package org.pancakeapple.annotation;

import org.pancakeapple.enumeration.MessageType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 拥有此注解的方法，表明需要推送消息
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SendMessage {
    MessageType messageType();
}
