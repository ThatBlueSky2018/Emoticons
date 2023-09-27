package org.pancakeapple.exception;

import org.pancakeapple.constant.MessageConstant;
import org.pancakeapple.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 * Author SKY 2023/9/18
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result<String> ex(Exception e) {
        e.printStackTrace();
        return Result.error(MessageConstant.GLOBAL_ERROR);
    }
}
