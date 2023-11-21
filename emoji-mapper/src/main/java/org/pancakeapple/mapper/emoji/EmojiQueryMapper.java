package org.pancakeapple.mapper.emoji;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.pancakeapple.vo.emoji.EmojiGeneralVO;

import java.time.LocalDateTime;

@Mapper
public interface EmojiQueryMapper {
    Page<EmojiGeneralVO> getStatic(Long staticId);

    Page<EmojiGeneralVO> getDynamic(Long dynamicId);

    Page<EmojiGeneralVO> getLatest(LocalDateTime sevenDaysAgo);
}
