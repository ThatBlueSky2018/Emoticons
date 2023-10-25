package org.pancakeapple.service;

public interface FavoriteService {
    /**
     * 用户收藏表情包
     * @param emojiId 表情包id
     */
    void favorite(Long emojiId);
}
