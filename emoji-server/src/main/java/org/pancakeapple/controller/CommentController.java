package org.pancakeapple.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.pancakeapple.constant.MessageConstant;
import org.pancakeapple.dto.emoji.PageQueryDTO;
import org.pancakeapple.dto.interaction.CommentDTO;
import org.pancakeapple.dto.interaction.ReplyDTO;
import org.pancakeapple.result.PageBean;
import org.pancakeapple.result.Result;
import org.pancakeapple.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/comment")
@Tag(name = "评论相关接口")
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * 评论某个表情包
     * @param commentDTO 表情包id+评论内容
     * @return 提示信息
     */
    @PostMapping
    @Operation(summary = "对表情包评论的接口")
    public Result<String> comment(@RequestBody CommentDTO commentDTO) {
        log.info("评论表情包：{}",commentDTO);
        commentService.comment(commentDTO);
        return Result.success(MessageConstant.COMMENT_SUCCESS);
    }

    /**
     * 回复某一条评论
     * @param replyDTO emojiId,commentId,content
     * @return 提示信息
     */
    @PostMapping("/reply")
    @Operation(summary = "回复评论的接口")
    public Result<String> reply(@RequestBody ReplyDTO replyDTO) {
        log.info("回复评论：{}",replyDTO);
        commentService.reply(replyDTO);
        return Result.success(MessageConstant.REPLY_SUCCESS);
    }

    /**
     * 查询某一个表情包的评论列表(分页查询，滚动加载)
     * @param emojiId 表情包id
     * @return 列表
     */
    @GetMapping("/{emojiId}")
    @Operation(summary = "查询某一个表情包的评论列表")
    public Result<PageBean> getComments(@PathVariable Long emojiId, PageQueryDTO pageQueryDTO) {
        log.info("获取某一个表情包的评论列表：{}",emojiId);
        PageBean pageBean=commentService.getComments(emojiId,pageQueryDTO);
        return Result.success(pageBean);
    }

    /**
     * 查询某一条评论的回复列表(分页查询，滚动加载)
     * @param commentId 评论id
     * @return 列表
     */
    @GetMapping("/reply/{commentId}")
    @Operation(summary = "获取某一条评论的回复列表")
    public Result<PageBean> getReply(@PathVariable Long commentId,PageQueryDTO pageQueryDTO) {
        log.info("获取某一条评论的回复列表：{}",commentId);
        PageBean pageBean=commentService.getReply(commentId,pageQueryDTO);
        return Result.success(pageBean);
    }

}
