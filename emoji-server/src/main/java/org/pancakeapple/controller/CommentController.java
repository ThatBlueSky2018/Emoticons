package org.pancakeapple.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.pancakeapple.constant.MessageConstant;
import org.pancakeapple.dto.interaction.CommentDTO;
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
}
