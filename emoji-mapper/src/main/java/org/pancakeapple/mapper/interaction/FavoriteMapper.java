package org.pancakeapple.mapper.interaction;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.pancakeapple.entity.interaction.Favorite;
import org.pancakeapple.vo.emoji.EmojiGeneralVO;

import java.util.List;

@Mapper
public interface FavoriteMapper {
    /**
     * 查询具体的一条记录
     * @param userId 用户id
     * @param emojiId 表情包id
     * @return 某条记录
     */
    @Select("select * from tb_favorite where user_id=#{userId} and emoji_id=#{emojiId}")
    List<Favorite> getByUserIdAndEmojiId(Long userId,Long emojiId);

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

    /**
     * 用户取消收藏
     * @param userId 用户id
     * @param emojiId 表情包id
     */
    @Delete("delete from tb_favorite where user_id=#{userId} and emoji_id=#{emojiId}")
    void deleteByUserIdAndEmojiId(Long userId, Long emojiId);

    Page<EmojiGeneralVO> getOtherFavoriteList(Long userId);
}
