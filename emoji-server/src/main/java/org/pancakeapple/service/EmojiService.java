package org.pancakeapple.service;

import org.pancakeapple.dto.emoji.EmojiDTO;
import org.pancakeapple.dto.emoji.EmojiPageQueryDTO;
import org.pancakeapple.result.PageBean;

public interface EmojiService {
    /**
     * 表情包上传
     * @param emojiDTO 数据封装
     */
    void saveWithTag(EmojiDTO emojiDTO);

    /**
     * 表情包分页查询
     * @param emojiPageQueryDTO 封装页码数以及每页记录数
     * @return 总记录数以及当前页记录列表
     */
    PageBean pageQuery(EmojiPageQueryDTO emojiPageQueryDTO);
}
