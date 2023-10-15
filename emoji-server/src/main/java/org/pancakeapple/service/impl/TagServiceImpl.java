package org.pancakeapple.service.impl;

import org.pancakeapple.constant.MessageConstant;
import org.pancakeapple.constant.StatusConstant;
import org.pancakeapple.dto.emoji.AddTagDTO;
import org.pancakeapple.entity.emoji.Tag;
import org.pancakeapple.entity.emoji.TagGroup;
import org.pancakeapple.exception.TagExistException;
import org.pancakeapple.exception.TagGroupNotExistException;
import org.pancakeapple.mapper.emoji.EmojiTagMapper;
import org.pancakeapple.mapper.emoji.TagGroupMapper;
import org.pancakeapple.mapper.emoji.TagMapper;
import org.pancakeapple.service.TagService;
import org.pancakeapple.vo.emoji.TagGeneralVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagGroupMapper tagGroupMapper;

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
     * 根据分组id查询标签列表
     * @param groupId 分组id
     * @return 标签列表
     */
    @Override
    public List<TagGeneralVO> listByGroupId(Long groupId) {
        TagGroup tagGroup = tagGroupMapper.getById(groupId);
        if(tagGroup==null) {
            throw new TagGroupNotExistException(MessageConstant.TAG_GROUP_NOT_EXIST);
        }
        return tagMapper.getByGroupId(groupId);
    }

    /**
     * 增加标签
     * @param addTagDTO 标签信息
     */
    @Override
    public void addTag(AddTagDTO addTagDTO) throws TagExistException{
        //1.确保标签分组存在
        TagGroup tagGroup = tagGroupMapper.getById(addTagDTO.getGroupId());
        if(tagGroup==null) {
            throw new TagGroupNotExistException(MessageConstant.TAG_GROUP_NOT_EXIST);
        }

        //2.保证标签名称不重复
        if(tagMapper.findByName(addTagDTO.getName())==null) {
            Tag tag = new Tag();
            BeanUtils.copyProperties(addTagDTO, tag);
            tag.setRefCount(0L);
            tag.setStatus(StatusConstant.ABLE);
            tagMapper.insert(tag);
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
