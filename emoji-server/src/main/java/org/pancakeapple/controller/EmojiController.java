package org.pancakeapple.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.pancakeapple.constant.MessageConstant;
import org.pancakeapple.dto.emoji.EmojiDTO;
import org.pancakeapple.result.Result;
import org.pancakeapple.service.EmojiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 表情包相关接口
 */
@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/emoji")
@Tag(name = "表情包相关接口")
public class EmojiController {
    @Autowired
    private EmojiService emojiService;

    /**
     * 上传表情包
     * @param emojiDTO 数据封装
     * @return 提示信息
     */
    @PostMapping
    @Operation(summary = "上传表情包的接口")
    public Result<String> upload(@RequestBody EmojiDTO emojiDTO) {
        log.info("上传表情包：{}",emojiDTO);
        emojiService.saveWithTag(emojiDTO);
        return Result.success(MessageConstant.EMOJI_UPLOAD_SUCCESS);
    }
}
