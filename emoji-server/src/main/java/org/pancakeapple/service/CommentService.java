package org.pancakeapple.service;

import org.pancakeapple.dto.interaction.CommentDTO;

public interface CommentService {
    /**
     * 评论某个表情包
     * @param commentDTO 表情包id+评论内容
     */
    void comment(CommentDTO commentDTO);
}
