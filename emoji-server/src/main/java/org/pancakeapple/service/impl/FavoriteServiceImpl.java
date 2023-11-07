package org.pancakeapple.service.impl;

import org.pancakeapple.annotation.AutoDecrease;
import org.pancakeapple.annotation.AutoIncrease;
import org.pancakeapple.constant.PromptConstant;
import org.pancakeapple.context.BaseContext;
import org.pancakeapple.entity.interaction.Favorite;
import org.pancakeapple.enumeration.BehaviorType;
import org.pancakeapple.exception.DuplicateFavoriteException;
import org.pancakeapple.exception.HasPublicFavoritesException;
import org.pancakeapple.exception.NoFavoriteException;
import org.pancakeapple.mapper.interaction.FavoriteMapper;
import org.pancakeapple.mapper.user.UserMapper;
import org.pancakeapple.service.FavoriteService;
import org.pancakeapple.vo.emoji.EmojiGeneralVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    private FavoriteMapper favoriteMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户收藏表情包
     * @param emojiId 表情包id
     */
    @Override
    @AutoIncrease(type = BehaviorType.FAVORITE)
    public void favorite(Long emojiId) {
        List<Favorite> list = favoriteMapper.getByUserIdAndEmojiId(BaseContext.getCurrentId(), emojiId);
        if(list!=null&&list.size()>0) {
            throw new DuplicateFavoriteException(PromptConstant.DUPLICATE_FAVORITE);
        }
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

    /**
     * 用户取消收藏
     * @param emojiId 表情包id
     */
    @Override
    @AutoDecrease(type = BehaviorType.FAVORITE)
    public void cancelFavorite(Long emojiId) {
        List<Favorite> list = favoriteMapper.getByUserIdAndEmojiId(BaseContext.getCurrentId(), emojiId);
        if(list == null || list.size() == 0) {
            throw new NoFavoriteException(PromptConstant.NOT_FAVORITE);
        }
        Long userId = BaseContext.getCurrentId();
        favoriteMapper.deleteByUserIdAndEmojiId(userId, emojiId);
    }

    /**
     * 用户设置收藏夹状态，公开or私密
     */
    @Override
    public void publicFavorite(Integer isOpen) {
        Integer favoriteStatus = userMapper.getFavoritesStatus(BaseContext.getCurrentId());
        if(Objects.equals(favoriteStatus, isOpen)) {
            throw new HasPublicFavoritesException(PromptConstant.HAS_SET_FAVORITES_PERMISSION);
        }
        userMapper.setFavoritesStatus(BaseContext.getCurrentId(),isOpen);
    }
}
