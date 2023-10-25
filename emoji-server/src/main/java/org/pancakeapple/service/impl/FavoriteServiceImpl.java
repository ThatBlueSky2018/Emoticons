package org.pancakeapple.service.impl;

import org.pancakeapple.annotation.AutoIncrease;
import org.pancakeapple.context.BaseContext;
import org.pancakeapple.entity.interaction.Favorite;
import org.pancakeapple.enumeration.BehaviorType;
import org.pancakeapple.mapper.interaction.FavoriteMapper;
import org.pancakeapple.service.FavoriteService;
import org.pancakeapple.vo.emoji.EmojiGeneralVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    private FavoriteMapper favoriteMapper;
    /**
     * 用户收藏表情包
     * @param emojiId 表情包id
     */
    @Override
    @AutoIncrease(type = BehaviorType.FAVORITE)
    public void favorite(Long emojiId) {
        Favorite favorite = Favorite.builder()
                .userId(BaseContext.getCurrentId())
                .emojiId(emojiId)
                .createTime(LocalDateTime.now())
                .build();
        favoriteMapper.insert(favorite);
    }

    /**
     * 用户查看收藏列表
     * @return 收藏列表
     */
    @Override
    public List<EmojiGeneralVO> list() {
        return favoriteMapper.list(BaseContext.getCurrentId());
    }
}

