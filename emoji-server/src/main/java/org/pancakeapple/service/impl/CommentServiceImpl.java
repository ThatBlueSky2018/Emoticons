package org.pancakeapple.service.impl;

import org.pancakeapple.annotation.AutoIncrease;
import org.pancakeapple.dto.interaction.CommentDTO;
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
}
