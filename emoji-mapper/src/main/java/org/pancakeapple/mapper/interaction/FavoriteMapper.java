package org.pancakeapple.mapper.interaction;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.pancakeapple.entity.interaction.Favorite;
import org.pancakeapple.vo.emoji.EmojiGeneralVO;

import java.util.List;

@Mapper
public interface FavoriteMapper {
    /**
     * 添加一条收藏记录
     * @param favorite userId,emojiId,createTime
     */
    @Insert("insert into tb_favorite(user_id, emoji_id, create_time) values" +
            "(#{userId},#{emojiId},#{createTime})")
    void insert(Favorite favorite);

    /**
     * 用户查看收藏列表
     * @param userId 用户id
     * @return 收藏列表
     */
    List<EmojiGeneralVO> list(Long userId);
}
