package org.pancakeapple.service.impl;

import org.pancakeapple.constant.MessageConstant;
import org.pancakeapple.dto.emoji.AddTagGroupDTO;
import org.pancakeapple.entity.emoji.TagGroup;
import org.pancakeapple.exception.TagGroupExistException;
import org.pancakeapple.mapper.emoji.TagGroupMapper;
import org.pancakeapple.mapper.emoji.TagMapper;
import org.pancakeapple.service.TagGroupService;
import org.pancakeapple.vo.emoji.TagGroupVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagGroupServiceImpl implements TagGroupService {
    @Autowired
    private TagGroupMapper tagGroupMapper;

    @Autowired
    private TagMapper tagMapper;
    /**
     * 添加标签分组
     * @param addTagGroupDTO 包含分组名称
     */
    @Override
    public void add(AddTagGroupDTO addTagGroupDTO) {
        if(tagGroupMapper.getByName(addTagGroupDTO.getName())!=null) {
            throw new TagGroupExistException(MessageConstant.TAG_GROUP_EXIST);
        }
        TagGroup tagGroup = new TagGroup();
        tagGroup.setName(addTagGroupDTO.getName());
        tagGroupMapper.insert(tagGroup);
    }

    /**
     * 查询标签分组列表
     * @return 包含id和name的列表
     */
    @Override
    public List<TagGroupVO> list() {
        return tagGroupMapper.list();
    }

    /**
     * 根据id删除某个标签分组
     * @param id 分组id
     */
    @Override
    @Transactional
    public void deleteById(Long id) {
        //1.删除该分组下的所有标签
        tagMapper.deleteByGroupId(id);

        //2.删除分组
        tagGroupMapper.deleteById(id);
    }

}
