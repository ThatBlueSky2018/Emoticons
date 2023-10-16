package org.pancakeapple.annotation;

import org.pancakeapple.enumeration.BehaviorType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoIncrease {
    BehaviorType type();
}
