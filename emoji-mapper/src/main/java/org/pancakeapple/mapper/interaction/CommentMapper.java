package org.pancakeapple.mapper.interaction;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.pancakeapple.annotation.AutoFill;
import org.pancakeapple.entity.interaction.Comment;
import org.pancakeapple.enumeration.OperationType;
import org.pancakeapple.vo.interaction.CommentVO;
import org.pancakeapple.vo.interaction.ReplyVO;

import java.util.List;

@Mapper
public interface CommentMapper {
    /**
     * 评论某个表情包
     * @param comment 评论相关信息
     */
    @AutoFill(value = OperationType.INSERT)
    void comment(Comment comment);

    /**
     * 根据id查询某一条评论
     * @param id 主键id
     * @return 评论信息
     */
    @Select("select * from emoticons.tb_comment where id=#{id}")
    Comment getById(Long id);

    List<CommentVO> getComments(Long emojiId);

    /**
     * 回复某一条评论
     * @param comment 回复信息
     */
    @AutoFill(value = OperationType.INSERT)
    void reply(Comment comment);

    List<ReplyVO> getReply(Long commentId);
}
