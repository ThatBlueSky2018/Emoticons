package org.pancakeapple.service.impl;

import org.pancakeapple.constant.MessageConstant;
import org.pancakeapple.dto.emoji.AddTagDTO;
import org.pancakeapple.entity.emoji.Tag;
import org.pancakeapple.exception.TagExistException;
import org.pancakeapple.mapper.emoji.EmojiTagMapper;
import org.pancakeapple.mapper.emoji.TagMapper;
import org.pancakeapple.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private EmojiTagMapper emojiTagMapper;

    /**
     * 获取标签列表
     * @return 标签列表
     */
    // TODO 更好的实现方式
    @Override
    public List<Tag> list() {
        return tagMapper.list();
    }

    /**
     * 增加标签
     * @param addTagDTO 标签信息
     */
    @Override
    public void addTag(AddTagDTO addTagDTO) throws TagExistException{
        if(tagMapper.findByName(addTagDTO.getName())==null) {
            tagMapper.add(addTagDTO);
        }
        else {
            throw new TagExistException(MessageConstant.TAG_EXIST);
        }
    }

    /**
     * 根据id删除标签
     * @param id 标签id
     */
    @Override
    @Transactional
    public void deleteById(Long id) {
        emojiTagMapper.deleteByTagId(id);
        tagMapper.deleteById(id);
    }
}
