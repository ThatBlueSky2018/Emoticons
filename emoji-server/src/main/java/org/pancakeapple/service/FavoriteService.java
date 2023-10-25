package org.pancakeapple.service;

import org.pancakeapple.vo.emoji.EmojiGeneralVO;

import java.util.List;

public interface FavoriteService {
    /**
     * 用户收藏表情包
     * @param emojiId 表情包id
     */
    void favorite(Long emojiId);

    /**
     * 用户查看收藏列表
     * @return 收藏列表
     */
    List<EmojiGeneralVO> list();
}
