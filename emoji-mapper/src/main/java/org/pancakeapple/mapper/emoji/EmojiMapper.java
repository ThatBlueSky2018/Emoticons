package org.pancakeapple.mapper.emoji;

import org.apache.ibatis.annotations.Mapper;
import org.pancakeapple.entity.emoji.Emoji;

@Mapper
public interface EmojiMapper {
    /**
     * 上传表情包
     * @param emoji 数据封装
     */
    void insert(Emoji emoji);
}
