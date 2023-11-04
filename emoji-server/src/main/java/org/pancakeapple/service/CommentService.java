package org.pancakeapple.service;

import org.pancakeapple.dto.interaction.CommentDTO;
import org.pancakeapple.dto.interaction.ReplyDTO;
import org.pancakeapple.vo.interaction.CommentVO;
import org.pancakeapple.vo.interaction.ReplyVO;

import java.util.List;

public interface CommentService {
    /**
     * 评论某个表情包
     * @param commentDTO 表情包id+评论内容
     */
    void comment(CommentDTO commentDTO);

    /**
     * 查询某一个表情包的评论列表
     * @param emojiId 表情包id
     * @return 评论列表
     */
    List<CommentVO> getComments(Long emojiId);

    /**
     * 回复某一条评论
     * @param replyDTO emojiId,commentId,content
     */
    void reply(ReplyDTO replyDTO);

    /**
     * 获取某一条评论的所有回复
     * @param commentId 评论id
     * @return 回复信息列表
     */
    List<ReplyVO> getReply(Long commentId);
}
