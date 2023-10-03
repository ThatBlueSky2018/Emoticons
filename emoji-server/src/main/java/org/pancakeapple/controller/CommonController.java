package org.pancakeapple.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.pancakeapple.constant.MessageConstant;
import org.pancakeapple.result.Result;
import org.pancakeapple.utils.AliOSSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
        return Result.error(MessageConstant.FILE_UPLOAD_FAILED);
    }
}
