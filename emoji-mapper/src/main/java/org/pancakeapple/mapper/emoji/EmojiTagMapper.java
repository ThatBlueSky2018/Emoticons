package org.pancakeapple.mapper.emoji;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.pancakeapple.entity.emoji.EmojiTag;

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
     * 批量添加表情包标签信息
     * @param emojiTags 表情包与标签的对应关系
     */
    void insertBatch(List<EmojiTag> emojiTags);
}
