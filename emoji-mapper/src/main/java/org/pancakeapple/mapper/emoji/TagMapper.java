package org.pancakeapple.mapper.emoji;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.pancakeapple.annotation.AutoFill;
import org.pancakeapple.dto.emoji.AddTagDTO;
import org.pancakeapple.entity.emoji.Tag;
import org.pancakeapple.enumeration.OperationType;
import org.pancakeapple.vo.emoji.TagGeneralVO;

import java.util.List;

@Mapper
public interface TagMapper {
    /**
     * 获取标签列表
     * @return 标签列表
     */
    @Select("select * from tb_tag")
    List<Tag> list();

    /**
     * 添加一个表情包标签
     * @param addTagDTO 表情包标签信息
     */
    @AutoFill(value = OperationType.INSERT)
    @Insert("insert into tb_tag(name,description,group_id)" +
            "values (#{name},#{description},#{groupId})")
    void insert(AddTagDTO addTagDTO);

    /**
     * 根据名称查找标签
     * @param name 标签名
     * @return 标签信息
     */
    @Select("select * from tb_tag where name=#{name}")
    Tag findByName(String name);

    /**
     * 根据id删除标签
     * @param id 标签id
     */
    @Delete("delete from tb_tag where id=#{id}")
    void deleteById(Long id);

    @Delete("delete from tb_tag where group_id=#{id}")
    void deleteByGroupId(Integer GroupId);

    @Select("select id,name from tb_tag where group_id=#{groupId} and status=1")
    List<TagGeneralVO> getByGroupId(Integer groupId);
}
