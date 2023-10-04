package org.pancakeapple.mapper.emoji;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.pancakeapple.entity.emoji.Emoji;
import org.pancakeapple.vo.emoji.EmojiDetailVO;
import org.pancakeapple.vo.emoji.EmojiGeneralVO;

@Mapper
public interface EmojiMapper {
    /**
     * 上传表情包
     * @param emoji 数据封装
     */
    @Insert("insert into tb_emoji (name, description, url, author, upload_time) " +
            "values (#{name},#{description},#{url},#{author},#{uploadTime})")
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
