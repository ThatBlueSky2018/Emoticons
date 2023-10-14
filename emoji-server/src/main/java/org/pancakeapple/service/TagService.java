package org.pancakeapple.service;

import org.pancakeapple.dto.emoji.AddTagDTO;
import org.pancakeapple.entity.emoji.Tag;
import org.pancakeapple.vo.emoji.TagGeneralVO;

import java.util.List;

public interface TagService {
    /**
     * 获取标签列表
     * @return 标签列表
     */
    List<Tag> list();

    /**
     * 增加标签
     * @param addTagDTO 标签信息
     */
    void addTag(AddTagDTO addTagDTO);

    /**
     * 根据id删除标签
     * @param id 标签id
     */
    void deleteById(Long id);

    /**
     * 根据分组id查询标签列表
     *
     * @param groupId 分组id
     * @return 标签列表
     */
    List<TagGeneralVO> listByGroupId(Integer groupId);
}
