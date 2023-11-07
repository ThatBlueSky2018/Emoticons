package org.pancakeapple.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.pancakeapple.annotation.AutoIncrease;
import org.pancakeapple.annotation.SendMessage;
import org.pancakeapple.constant.PromptConstant;
import org.pancakeapple.dto.emoji.PageQueryDTO;
import org.pancakeapple.dto.interaction.CommentDTO;
import org.pancakeapple.dto.interaction.ReplyDTO;
import org.pancakeapple.entity.interaction.Comment;
import org.pancakeapple.enumeration.BehaviorType;
import org.pancakeapple.enumeration.MessageType;
import org.pancakeapple.exception.NotTopCommentException;
import org.pancakeapple.mapper.interaction.CommentMapper;
import org.pancakeapple.result.PageBean;
import org.pancakeapple.service.CommentService;
import org.pancakeapple.vo.interaction.CommentVO;
import org.pancakeapple.vo.interaction.ReplyVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @SendMessage(messageType = MessageType.COMMENT)
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
    public PageBean getComments(Long emojiId, PageQueryDTO pageQueryDTO) {
        PageHelper.startPage(pageQueryDTO.getPage(),pageQueryDTO.getPageSize());
        Page<CommentVO> page=commentMapper.getComments(emojiId);
        return new PageBean(page.getTotal(),page.getResult());
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

    /**
     * 获取某一条评论的所有回复
     * @param commentId 评论id
     * @return 回复信息列表
     */
    @Override
    public PageBean getReply(Long commentId,PageQueryDTO pageQueryDTO) {
        Comment comment = commentMapper.getById(commentId);
        if(comment.getReplyId()!=null || comment.getReplyReplyId()!=null) {
            throw new NotTopCommentException(PromptConstant.NOT_TOP_COMMENT);
        }
        PageHelper.startPage(pageQueryDTO.getPage(),pageQueryDTO.getPageSize());
        Page<ReplyVO> page=commentMapper.getReply(commentId);
        return new PageBean(page.getTotal(),page.getResult());
    }
}
