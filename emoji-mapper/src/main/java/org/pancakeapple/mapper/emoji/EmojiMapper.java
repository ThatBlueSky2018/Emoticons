package org.pancakeapple.mapper.emoji;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;
import org.pancakeapple.annotation.AutoFill;
import org.pancakeapple.entity.emoji.Emoji;
import org.pancakeapple.enumeration.OperationType;
import org.pancakeapple.vo.emoji.EmojiDetailVO;
import org.pancakeapple.vo.emoji.EmojiGeneralVO;

@Mapper
public interface EmojiMapper {
    /**
     * 上传表情包
     * @param emoji 数据封装
     */
    @AutoFill(value = OperationType.INSERT)
    @Insert("insert into tb_emoji(name,description,url,type_id,hits,downloads,favorite," +
            "create_time,create_user,update_time,update_user)" +
            "values (#{name},#{description},#{url},#{typeId},#{hits},#{downloads},#{favorite}," +
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
    @Select("select id,name,description,url,type_id,hits,favorite,create_user,create_time " +
            "from tb_emoji where id=#{id}")
    EmojiDetailVO getById(Long id);

    /**
     * 增加点击量
     * @param id 点击的表情包id
     */
    @Update("update tb_emoji set hits=hits+1 where id=#{id}")
    void increaseHits(Long id);

    @Update("update tb_emoji set favorite=favorite+1 where id=#{id}")
    void increaseFavorite(Long id);

    @Update("update tb_emoji set favorite=favorite-1 where id=#{id}")
    void decreaseFavorite(Long id);
}
