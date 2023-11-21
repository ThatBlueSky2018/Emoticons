package org.pancakeapple.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.pancakeapple.dto.emoji.PageQueryDTO;
import org.pancakeapple.result.PageBean;
import org.pancakeapple.result.Result;
import org.pancakeapple.service.EmojiQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 表情包查询相关接口
 */
@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/query")
@Tag(name = "表情包查询相关接口")
public class EmojiQueryController {
    @Autowired
    private EmojiQueryService emojiQueryService;

    /**
     * 查询静态表情包
     * @param pageQueryDTO 分页查询参数
     * @return 分页查询结果
     */
    @GetMapping("/static")
    @Operation(summary = "查询静态表情包")
    public Result<PageBean> getStatic(PageQueryDTO pageQueryDTO) {
        log.info("查询静态表情包");
        PageBean pageBean = emojiQueryService.getStatic(pageQueryDTO);
        return Result.success(pageBean);
    }

    @GetMapping("/dynamic")
    @Operation(summary = "查询动态表情包")
    public Result<PageBean> getDynamic(PageQueryDTO pageQueryDTO) {
        log.info("查询动态表情包");
        PageBean pageBean = emojiQueryService.getDynamic(pageQueryDTO);
        return Result.success(pageBean);
    }

    @GetMapping("/latest")
    @Operation(summary = "查询最新的表情包")
    public Result<PageBean> getLatest(PageQueryDTO pageQueryDTO) {
        log.info("查询最新表情包");
        PageBean pageBean = emojiQueryService.getLatest(pageQueryDTO);
        return Result.success(pageBean);
    }
}
