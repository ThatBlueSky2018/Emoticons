package org.pancakeapple.service;

import org.pancakeapple.dto.emoji.AddTagGroupDTO;
import org.pancakeapple.vo.emoji.TagGroupVO;

import java.util.List;

public interface TagGroupService {
    /**
     * 添加标签分组
     * @param addTagGroupDTO 封装分组名称
     */
    void add(AddTagGroupDTO addTagGroupDTO);

    /**
     * 查询标签分组列表
     * @return 包含id和name的列表
     */
    List<TagGroupVO> list();

    /**
     * 根据id删除某个标签分组
     * @param id 分组id
     */
    void deleteById(Long id);
}
