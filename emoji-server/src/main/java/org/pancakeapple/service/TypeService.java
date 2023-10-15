package org.pancakeapple.service;

import org.pancakeapple.dto.emoji.AddTypeDTO;
import org.pancakeapple.vo.emoji.TypeVO;

import java.util.List;

public interface TypeService {
    /**
     * 查询表情包类型列表
     * @return id,name,refCount列表
     */
    List<TypeVO> list();

    /**
     * 新增表情包类型
     * @param addTypeDTO 只包含name
     */
    void add(AddTypeDTO addTypeDTO);
}
