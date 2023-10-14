package org.pancakeapple.exception;

import lombok.extern.slf4j.Slf4j;
import org.pancakeapple.constant.MessageConstant;
import org.pancakeapple.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 * Author SKY 2023/9/18
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result<String> ex(Exception e) {
        log.info("捕获到全局异常：{}",e.toString());
        e.printStackTrace();
        if(e.getMessage()!=null) {
            return Result.error(e.getMessage());
        }
        return Result.error(MessageConstant.GLOBAL_ERROR);
    }
}
