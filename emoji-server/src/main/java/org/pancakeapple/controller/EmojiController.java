package org.pancakeapple.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.pancakeapple.constant.PromptConstant;
import org.pancakeapple.dto.emoji.EmojiUploadDTO;
import org.pancakeapple.dto.emoji.PageQueryDTO;
import org.pancakeapple.result.PageBean;
import org.pancakeapple.result.Result;
import org.pancakeapple.service.EmojiService;
import org.pancakeapple.vo.emoji.EmojiDetailVO;
import org.pancakeapple.vo.emoji.EmojiGeneralVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * @param emojiUploadDTO 数据封装
     * @return 提示信息
     */
    @PostMapping
    @Operation(summary = "上传表情包的接口")
    public Result<String> upload(@RequestBody EmojiUploadDTO emojiUploadDTO) {
        log.info("上传表情包：{}", emojiUploadDTO);
        emojiService.saveWithTag(emojiUploadDTO);
        return Result.success(PromptConstant.EMOJI_UPLOAD_SUCCESS);
    }

    /**
     * 表情包整体信息分页查询
     * @param pageQueryDTO 封装页码数以及每页记录数
     * @return 总记录数以及当前页记录列表
     */
    @GetMapping
    @Operation(summary = "表情包信息分页查询")
    @ApiOperationSupport(author = "3010796910sky@gmail.com")
    public Result<PageBean> pageQuery(PageQueryDTO pageQueryDTO) {
        log.info("表情包分页查询：{}", pageQueryDTO);
        PageBean pageBean = emojiService.pageQuery(pageQueryDTO);
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
    /**
     * 查询自己上传的表情包(分页查询)
     * @return 分页查询结果
     * @param pageQueryDTO 分页查询参数
     */
    @GetMapping("/uploaded")
    @Operation(summary = "查询自己上传的表情包(分页查询)")
    public Result<PageBean> getUploaded(PageQueryDTO pageQueryDTO) {
        log.info("查询自己上传的表情包");
        PageBean pageBean=emojiService.getUploaded(pageQueryDTO);
        return Result.success(pageBean);
    }

    /**
     * 查询某个用户上传的表情包
     * @param userId 用户id
     * @param pageQueryDTO 分页查询参数
     * @return 分页查询结果
     */
    @GetMapping("/queryByUserId")
    @Operation(summary = "查询某个用户上传的表情包(分页查询)")
    public Result<PageBean> getByUserId(Long userId,PageQueryDTO pageQueryDTO) {
        log.info("查询用户上传的表情包，用户id:{}",userId);
        PageBean pageBean=emojiService.getByUserId(userId,pageQueryDTO);
        return Result.success(pageBean);
    }

    /**
     * 下载表情包
     * @param emojiId  表情包id
     * @return 提示信息
     */
    @PutMapping("/download")
    @Operation(summary = "下载表情包(下载量+1即可)")
    public Result<String> download(Long emojiId) {
        log.info("下载表情包：{}",emojiId);
        emojiService.download(emojiId);
        return Result.success(PromptConstant.DOWNLOAD_SUCCESS);
    }

    /**
     * 根据相似列表查看相似表情包
     * @param similarList 相似id列表[1,2,3]
     * @return 表情包列表
     */
    @GetMapping("/similar")
    @Operation(summary = "根据相似列表查看相似表情包(需要提前转换好类型)")
    public Result<List<EmojiGeneralVO>> getSimilar(@RequestParam List<Long> similarList) {
        log.info("查询相似表情包列表：{}",similarList);
        List<EmojiGeneralVO> similarEmojiList = emojiService.getSimilar(similarList);
        return Result.success(similarEmojiList);
    }
}
