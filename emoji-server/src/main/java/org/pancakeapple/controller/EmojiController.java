package org.pancakeapple.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.pancakeapple.constant.MessageConstant;
import org.pancakeapple.dto.emoji.EmojiDTO;
import org.pancakeapple.dto.emoji.EmojiPageQueryDTO;
import org.pancakeapple.result.PageBean;
import org.pancakeapple.result.Result;
import org.pancakeapple.service.EmojiService;
import org.pancakeapple.vo.emoji.EmojiDetailVO;
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

    /**
     * 表情包整体信息分页查询
     * @param emojiPageQueryDTO 封装页码数以及每页记录数
     * @return 总记录数以及当前页记录列表
     */
    @GetMapping
    @Operation(summary = "表情包信息分页查询")
    @ApiOperationSupport(author = "3010796910sky@gmail.com")
    public Result<PageBean> pageQuery(EmojiPageQueryDTO emojiPageQueryDTO) {
        log.info("表情包分页查询：{}", emojiPageQueryDTO);
        PageBean pageBean = emojiService.pageQuery(emojiPageQueryDTO);
        return Result.success(pageBean);
    }

    /**
     * 根据id查询表情包详细信息
     * @param id 主键id
     * @return 表情包信息
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据id获取表情包详细信息")
    @ApiOperationSupport(author = "3010796910sky@gmail.com")
    public Result<EmojiDetailVO> getById(@PathVariable Long id) {
        log.info("根据id查询表情包详细信息：{}",id);
        EmojiDetailVO emojiDetailVO=emojiService.getById(id);
        return Result.success(emojiDetailVO);
    }
}