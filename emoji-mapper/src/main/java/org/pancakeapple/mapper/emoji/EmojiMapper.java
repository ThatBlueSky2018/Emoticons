package org.pancakeapple.mapper.emoji;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;
import org.pancakeapple.annotation.AutoFill;
import org.pancakeapple.entity.emoji.Emoji;
import org.pancakeapple.enumeration.OperationType;
import org.pancakeapple.vo.emoji.EmojiDetailVO;
import org.pancakeapple.vo.emoji.EmojiGeneralVO;

import java.util.List;

@Mapper
public interface EmojiMapper {
    /**
     * 上传表情包
     * @param emoji 数据封装
     */
    @AutoFill(value = OperationType.INSERT)
    @Insert("insert into tb_emoji(name,description,url,hits,downloads,favorite," +
            "create_time,create_user,update_time,update_user)" +
            "values (#{name},#{description},#{url},#{hits},#{downloads},#{favorite}," +
            "#{createTime},#{createUser},#{updateTime},#{updateUser})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void insert(Emoji emoji);

    /**
     * 表情包总体信息分页查询
     * @return Page对象
     */
    Page<EmojiGeneralVO> pageQuery();

    /**
     * 根据id查询表情包详细信息
     * @param id 主键id
     * @return 详细信息
     */
    @Select("select id,name,description,url,hits,comments,downloads,favorite,similar_list,create_user,create_time " +
            "from tb_emoji where id=#{id}")
    EmojiDetailVO getById(Long id);

    @Select("select * from tb_emoji where id=#{id}")
    Emoji getEmojiById(Long id);

    /**
     * 根据id查询表情包具有的标签列表
     * @param id 表情包id
     * @return 标签名列表
     */
    List<String> getTagsById(Long id);

    /**
     * 增加点击量
     * @param id 点击的表情包id
     */
    @Update("update tb_emoji set hits=hits+1 where id=#{id}")
    void increaseHits(Long id);

    /**
     * 增加收藏量
     * @param id 表情包id
     */
    @Update("update tb_emoji set favorite=favorite+1 where id=#{id}")
    void increaseFavorite(Long id);

    /**
     * 减少收藏量
     * @param id 表情包id
     */
    @Update("update tb_emoji set favorite=favorite-1 where id=#{id}")
    void decreaseFavorite(Long id);

    /**
     * 增加评论量
     * @param emojiId 表情包id
     */
    @Update("update tb_emoji set comments=comments+1 where id=#{id}")
    void increaseComment(Long emojiId);

    /**
     * 根据用户id查询上传的表情包
     * @param userId 用户id
     * @return Page
     */
    Page<EmojiGeneralVO> getByUserId(Long userId);

    /**
     * 根据用户id查询其上传的表情包数量
     * @param userId 用户id
     * @return 上传的表情包数量
     */
    @Select("select count(id) from tb_emoji where create_user=#{userId}")
    Integer getUploadCountByUserId(Long userId);

    /**
     * 下载一个表情包
     * @param emojiId 表情包id
     */
    @Update("update tb_emoji set downloads=downloads+1 where id=#{emojiId}")
    void download(Long emojiId);

    /**
     * 查询相似表情包列表
     * @param similarList 相似id列表
     * @return 表情包列表
     */
    List<EmojiGeneralVO> getSimilar(List<Long> similarList);

    @Select("select min(hits) from tb_emoji;")
    Integer getMinHits();

    @Select("select max(hits) from tb_emoji;")
    Integer getMaxHits();

    @Select("select min(comments) from tb_emoji;")
    Integer getMinComments();

    @Select("select max(comments) from tb_emoji;")
    Integer getMaxComments();

    @Select("select min(downloads) from tb_emoji;")
    Integer getMinDownloads();

    @Select("select max(downloads) from tb_emoji;")
    Integer getMaxDownloads();

    @Select("select min(favorite) from tb_emoji;")
    Integer getMinFavorite();

    @Select("select max(favorite) from tb_emoji;")
    Integer getMaxFavorite();

    @Select("select * from tb_emoji limit #{start},#{size};")
    List<Emoji> page(Integer start,Integer size);

    @Select("select count(*) from tb_emoji;")
    Long getCount();

    @Update("update tb_emoji set hot_index=#{hotIndex} where id=#{id};")
    void updateHotIndex(Double hotIndex,Long id);
}
