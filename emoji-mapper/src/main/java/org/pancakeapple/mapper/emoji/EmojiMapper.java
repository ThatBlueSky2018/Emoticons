package org.pancakeapple.mapper.emoji;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
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
    EmojiDetailVO getById(Long id);
}
