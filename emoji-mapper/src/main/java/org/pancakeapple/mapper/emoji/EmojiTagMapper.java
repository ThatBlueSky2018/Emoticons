package org.pancakeapple.mapper.emoji;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface EmojiTagMapper {
    /**
     * 删除表情包与特定标签之间的关系
     * @param tagId 标签id
     */
    @Delete("delete from emoji_tag where tag_id=#{id}")
    void deleteByTagId(Long tagId);
}
