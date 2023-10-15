package org.pancakeapple.service.impl;

import org.pancakeapple.constant.StatusConstant;
import org.pancakeapple.dto.emoji.AddTypeDTO;
import org.pancakeapple.entity.emoji.EmojiType;
import org.pancakeapple.mapper.emoji.TypeMapper;
import org.pancakeapple.service.TypeService;
import org.pancakeapple.vo.emoji.TypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;
    /**
     * 查询表情包类型列表
     * @return id, name, refCount列表
     */
    @Override
    public List<TypeVO> list() {
        return typeMapper.list();
    }

    /**
     * 新增表情包类型
     * @param addTypeDTO 只包含name
     */
    @Override
    public void add(AddTypeDTO addTypeDTO) {
        EmojiType emojiType = new EmojiType();
        emojiType.setName(addTypeDTO.getName());
        emojiType.setRefCount(0L);
        emojiType.setStatus(StatusConstant.ABLE);
        typeMapper.insert(emojiType);
    }
}
