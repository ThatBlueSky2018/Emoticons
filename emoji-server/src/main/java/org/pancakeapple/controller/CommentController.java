package org.pancakeapple.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.pancakeapple.constant.MessageConstant;
import org.pancakeapple.dto.interaction.CommentDTO;
import org.pancakeapple.dto.interaction.ReplyDTO;
import org.pancakeapple.result.Result;
import org.pancakeapple.service.CommentService;
import org.pancakeapple.vo.interaction.CommentVO;
import org.pancakeapple.vo.interaction.ReplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * 查询某一个表情包的评论列表
     * @param emojiId 表情包id
     * @return 列表
     */
    @GetMapping("/{emojiId}")
    @Operation(summary = "查询某一个表情包的评论列表")
    public Result<List<CommentVO>> getComments(@PathVariable Long emojiId) {
        log.info("获取某一个表情包的评论列表：{}",emojiId);
        List<CommentVO> commentVOs=commentService.getComments(emojiId);
        return Result.success(commentVOs);
    }

    /**
     * 查询某一条评论的回复列表
     * @param commentId 评论id
     * @return 列表
     */
    @GetMapping("/reply/{commentId}")
    @Operation(summary = "获取某一条评论的回复列表")
    public Result<List<ReplyVO>> getReply(@PathVariable Long commentId) {
        log.info("获取某一条评论的回复列表：{}",commentId);
        List<ReplyVO> replyList=commentService.getReply(commentId);
        return Result.success(replyList);
    }

}
