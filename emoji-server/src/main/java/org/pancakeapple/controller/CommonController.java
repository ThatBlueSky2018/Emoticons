package org.pancakeapple.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.pancakeapple.constant.PromptConstant;
import org.pancakeapple.constant.SseConstant;
import org.pancakeapple.result.Result;
import org.pancakeapple.session.SseSession;
import org.pancakeapple.utils.AliOSSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

/**
 * 通用控制器
 */
@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/common")
@Tag(name = "通用接口")
public class CommonController {

    @Autowired
    private AliOSSUtils aliOSSUtils;

    @Autowired
    private SseSession sseSession;

    /**
     * 文件上传
     * @param file 要上传的文件
     * @return 文件访问url
     */
    @PostMapping("/upload")
    @Operation(summary = "文件上传接口")
    public Result<String> upload(MultipartFile file) {
        log.info("文件上传，{}",file);
        try {
            String url = aliOSSUtils.upload(file);
            return Result.success(url);
        } catch (IOException e) {
            log.error("文件上传失败！");
        }
        return Result.error(PromptConstant.FILE_UPLOAD_FAILED);
    }

    /**
     * 建立连接
     * @param username 用户名
     * @return SseEmitter对象
     */
    @GetMapping(value = "/connect/{username}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @Operation(summary = "建立Sse连接")
    public SseEmitter connect(@PathVariable("username") String username){
        log.info("开始建立Sse连接，用户名：{}",username);
        if(sseSession.exists(username)) {
            //如果已经连接，先删除一次
            sseSession.remove(username);
        }
        SseEmitter sseEmitter = new SseEmitter(0L);

        //绑定回调函数
        sseEmitter.onError((err)-> {
            log.error(SseConstant.ON_ERROR+err.getMessage()+username);
            sseSession.onError(username, err);
        });

        sseEmitter.onTimeout(() -> {
            log.info(SseConstant.ON_TIME_OUT+username);
            //连接超时直接删除
            sseSession.remove(username);
        });

        sseEmitter.onCompletion(() -> {
            log.info(SseConstant.ON_COMPLETION+username);
            //完成后直接删除
            sseSession.remove(username);
        });

        //添加到Map
        sseSession.add(username, sseEmitter);
        return sseEmitter;
    }

    /**
     * 断开连接
     * @param username 用户名
     * @return 提示信息
     */
    @GetMapping("/close/{username}")
    @Operation(summary = "断开Sse连接")
    public Result<String> close(@PathVariable String username) {
        log.info("用户断开连接，用户名: {}", username);
        sseSession.remove(username);
        return Result.success(SseConstant.CLOSE_SUCCESS);
    }
}
