package org.pancakeapple.mapper.emoji;

import org.apache.ibatis.annotations.Mapper;
import org.pancakeapple.entity.emoji.EmojiData;

@Mapper
public interface EmojiDataMapper {
    void insert(EmojiData emojiData);
}
