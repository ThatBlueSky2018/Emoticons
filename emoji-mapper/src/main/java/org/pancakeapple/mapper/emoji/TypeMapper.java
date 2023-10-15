package org.pancakeapple.mapper.emoji;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.pancakeapple.annotation.AutoFill;
import org.pancakeapple.entity.emoji.EmojiType;
import org.pancakeapple.enumeration.OperationType;
import org.pancakeapple.vo.emoji.TypeVO;

import java.util.List;

@Mapper
public interface TypeMapper {
    /**
     * 查询表情包类型列表
     * @return id,name,ref_count
     */
    @Select("select id,name,ref_count from emoji_type")
    List<TypeVO> list();

    /**
     * 插入一条数据
     * @param emojiType 数据封装
     */
    @AutoFill(value = OperationType.INSERT)
    @Insert("insert into emoji_type(name, ref_count, status, create_time, create_user, update_time, update_user)" +
            "values (#{name},#{refCount},#{status},#{createTime},#{createUser},#{updateTime},#{updateUser})")
    void insert(EmojiType emojiType);

    /**
     * 根据id查询某个类型
     * @param id 主键id
     * @return 实体类
     */
    @Select("select * from emoji_type where id=#{id};")
    EmojiType getById(Long id);
}