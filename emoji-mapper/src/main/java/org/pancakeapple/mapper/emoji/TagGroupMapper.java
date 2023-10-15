package org.pancakeapple.mapper.emoji;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.pancakeapple.annotation.AutoFill;
import org.pancakeapple.entity.emoji.TagGroup;
import org.pancakeapple.enumeration.OperationType;
import org.pancakeapple.vo.emoji.TagGroupVO;

import java.util.List;

@Mapper
public interface TagGroupMapper {
    /**
     * 根据分组名称查询
     * @param name 分组名称
     * @return TagGroup
     */
    @Select("select * from tag_group where name=#{name}")
    TagGroup getByName(String name);

    /**
     * 插入一条分组数据
     * @param tagGroup 标签分组数据封装
     */
    @AutoFill(value = OperationType.INSERT)
    void insert(TagGroup tagGroup);

    /**
     * 查询标签分组列表
     * @return 分组列表
     */
    @Select("select id,name from tag_group;")
    List<TagGroupVO> list();

    /**
     * 删除一个标签分组
     * @param id 要删除的分组的id
     */
    @Delete("delete from tag_group where id=#{id}")
    void deleteById(Long id);

    /**
     * 根据id查询
     * @param id id
     * @return TagGroup
     */
    @Select("select * from tag_group where id=#{id}")
    TagGroup getById(Long id);
}
