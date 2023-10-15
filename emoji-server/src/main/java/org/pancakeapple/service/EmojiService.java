package org.pancakeapple.service;

import org.pancakeapple.dto.emoji.EmojiUploadDTO;
import org.pancakeapple.dto.emoji.EmojiPageQueryDTO;
import org.pancakeapple.result.PageBean;
import org.pancakeapple.vo.emoji.EmojiDetailVO;

public interface EmojiService {
    /**
     * 表情包上传
     * @param emojiUploadDTO 数据封装
     */
    void saveWithTag(EmojiUploadDTO emojiUploadDTO);

    /**
     * 表情包分页查询
     * @param emojiPageQueryDTO 封装页码数以及每页记录数
     * @return 总记录数以及当前页记录列表
     */
    PageBean pageQuery(EmojiPageQueryDTO emojiPageQueryDTO);

    /**
     * 根据id查询表情包详细信息
     * @param id 主键id
     * @return 详细信息
     */
    EmojiDetailVO getById(Long id);
}
