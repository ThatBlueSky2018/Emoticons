package org.pancakeapple.service;

import org.pancakeapple.dto.emoji.PageQueryDTO;
import org.pancakeapple.result.PageBean;

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
    PageBean list(PageQueryDTO pageQueryDTO);

    /**
     * 用户取消收藏
     * @param emojiId 表情包id
     */
    void cancelFavorite(Long emojiId);

    /**
     * 用户公开收藏夹
     */
    void publicFavorite(Integer isOpen);

    /**
     * 查询某个用户的收藏夹
     * @param userId 用户id
     * @return 分页信息封装
     */
    PageBean getOtherFavoriteList(Long userId, PageQueryDTO pageQueryDTO);
}
