package org.pancakeapple.service.impl;

import org.pancakeapple.annotation.AutoIncrease;
import org.pancakeapple.dto.interaction.CommentDTO;
import org.pancakeapple.dto.interaction.ReplyDTO;
import org.pancakeapple.entity.interaction.Comment;
import org.pancakeapple.enumeration.BehaviorType;
import org.pancakeapple.mapper.interaction.CommentMapper;
import org.pancakeapple.service.CommentService;
import org.pancakeapple.vo.interaction.CommentVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    /**
     * 评论某个表情包
     * @param commentDTO 表情包id+评论内容
     */
    @Override
    @Transactional
    @AutoIncrease(type = BehaviorType.COMMENT)
    public void comment(CommentDTO commentDTO) {
        Comment comment=new Comment();
        BeanUtils.copyProperties(commentDTO,comment);
        commentMapper.comment(comment);
    }

    /**
     * 查询某一个表情包的评论列表
     * @param emojiId 表情包id
     * @return 评论列表
     */
    @Override
    public List<CommentVO> getComments(Long emojiId) {
        return commentMapper.getComments(emojiId);
    }

    /**
     * 回复某一条评论
     * @param replyDTO emojiId,commentId,content
     */
    @Override
    @Transactional
    @AutoIncrease(type = BehaviorType.REPLY)
    public void reply(ReplyDTO replyDTO) {
        //获取要回复的评论
        Comment replyComment = commentMapper.getById(replyDTO.getCommentId());

        //回复楼主
        if(replyComment.getReplyId()==null) {
            Comment comment = Comment.builder()
                    .replyId(replyDTO.getCommentId())
                    .emojiId(replyDTO.getEmojiId())
                    .content(replyDTO.getContent())
                    .build();
            commentMapper.reply(comment);
        } else if (replyComment.getReplyId()!=null) {
            //回复的回复
            Comment comment = Comment.builder()
                    .replyId(replyComment.getReplyId())  //楼主评论id
                    .replyReplyId(replyDTO.getCommentId()) //要回复的评论id
                    .emojiId(replyDTO.getEmojiId())
                    .content(replyDTO.getContent())
                    .build();
            commentMapper.reply(comment);
        }
    }
}