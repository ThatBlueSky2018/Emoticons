package org.pancakeapple.service;

import org.pancakeapple.dto.emoji.EmojiDTO;

public interface EmojiService {
    /**
     * 表情包上传
     * @param emojiDTO 数据封装
     */
    void saveWithTag(EmojiDTO emojiDTO);
}
