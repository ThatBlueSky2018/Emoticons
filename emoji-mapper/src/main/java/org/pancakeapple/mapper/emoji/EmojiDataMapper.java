package org.pancakeapple.mapper.emoji;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.pancakeapple.entity.emoji.EmojiData;

@Mapper
public interface EmojiDataMapper {
    @Insert("insert into emoji_data(emoji_id, hits, downloads, favorite) " +
            "values (#{emojiId},#{hits},#{downloads},#{favorite})")
    void insert(EmojiData emojiData);
}
