package org.pancakeapple.mapper.emoji;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.pancakeapple.annotation.AutoFill;
import org.pancakeapple.entity.emoji.EmojiTag;
import org.pancakeapple.enumeration.OperationType;

import java.util.List;

@Mapper
public interface EmojiTagMapper {
    /**
     * 删除表情包与特定标签之间的关系
     * @param tagId 标签id
     */
    @Delete("delete from emoji_tag where tag_id=#{id}")
    void deleteByTagId(Long tagId);

    /**
     * 插入一条数据
     * @param emojiTag 数据封装
     */
    @AutoFill(value = OperationType.INSERT)
    @Insert("insert into emoji_tag(emoji_id,tag_id,create_time,create_user,update_time,update_user)" +
            "values (#{emojiId},#{tagId},#{createTime},#{createUser},#{updateTime},#{updateUser})")
    void insert(EmojiTag emojiTag);

    @Select("select id,emoji_id,tag_id from emoji_tag where emoji_id=#{emojiId}")
    List<EmojiTag> getByEmojiId(Long emojiId);
}
