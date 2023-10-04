package org.pancakeapple.mapper.emoji;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.pancakeapple.entity.emoji.Emoji;
import org.pancakeapple.vo.emoji.EmojiGeneralVO;

@Mapper
public interface EmojiMapper {
    /**
     * 上传表情包
     * @param emoji 数据封装
     */
    void insert(Emoji emoji);

    /**
     * 表情包总体信息分页查询
     * @return Page对象
     */
    Page<EmojiGeneralVO> pageQuery();
}
