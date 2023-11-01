package org.pancakeapple.mapper.interaction;

import org.apache.ibatis.annotations.Mapper;
import org.pancakeapple.annotation.AutoFill;
import org.pancakeapple.entity.interaction.Comment;
import org.pancakeapple.enumeration.OperationType;
import org.pancakeapple.vo.interaction.CommentVO;

import java.util.List;

@Mapper
public interface CommentMapper {
    /**
     * 评论某个表情包
     * @param comment 评论相关信息
     */
    @AutoFill(value = OperationType.INSERT)
    void comment(Comment comment);

    List<CommentVO> getComments(Long emojiId);
}
