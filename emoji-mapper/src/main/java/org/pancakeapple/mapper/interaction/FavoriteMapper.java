package org.pancakeapple.mapper.interaction;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.pancakeapple.entity.interaction.Favorite;

@Mapper
public interface FavoriteMapper {
    @Insert("insert into tb_favorite(user_id, emoji_id, create_time) values" +
            "(#{userId},#{emojiId},#{createTime})")
    void insert(Favorite favorite);
}
