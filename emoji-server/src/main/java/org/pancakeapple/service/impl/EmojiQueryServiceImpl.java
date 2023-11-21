package org.pancakeapple.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.pancakeapple.constant.DataConstant;
import org.pancakeapple.dto.emoji.PageQueryDTO;
import org.pancakeapple.mapper.emoji.EmojiQueryMapper;
import org.pancakeapple.mapper.emoji.TagMapper;
import org.pancakeapple.result.PageBean;
import org.pancakeapple.service.EmojiQueryService;
import org.pancakeapple.vo.emoji.EmojiGeneralVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class EmojiQueryServiceImpl implements EmojiQueryService {
    @Autowired
    private EmojiQueryMapper emojiQueryMapper;

    @Autowired
    private TagMapper tagMapper;
    /**
     * 查询静态表情包
     * @param pageQueryDTO 分页查询参数
     * @return 分页查询结果
     */
    @Override
    public PageBean getStatic(PageQueryDTO pageQueryDTO) {
        Long staticId=tagMapper.getByName(DataConstant.STATIC_TAG_NAME);
        PageHelper.startPage(pageQueryDTO.getPage(),pageQueryDTO.getPageSize());
        Page<EmojiGeneralVO> page = emojiQueryMapper.getStatic(staticId);
        return new PageBean(page.getTotal(),page.getResult());
    }

    /**
     * 查询动态表情包
     * @param pageQueryDTO 分页查询参数
     * @return 分页查询结果
     */
    @Override
    public PageBean getDynamic(PageQueryDTO pageQueryDTO) {
        Long dynamicId=tagMapper.getByName(DataConstant.DYNAMIC_TAG_NAME);
        PageHelper.startPage(pageQueryDTO.getPage(),pageQueryDTO.getPageSize());
        Page<EmojiGeneralVO> page = emojiQueryMapper.getDynamic(dynamicId);
        return new PageBean(page.getTotal(),page.getResult());
    }

    /**
     * 查询最新表情包
     * @param pageQueryDTO 分页查询参数
     * @return 分页查询结果
     */
    @Override
    public PageBean getLatest(PageQueryDTO pageQueryDTO) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime sevenDaysAgo = now.minus(Duration.ofDays(7));
        PageHelper.startPage(pageQueryDTO.getPage(),pageQueryDTO.getPageSize());
        Page<EmojiGeneralVO> page=emojiQueryMapper.getLatest(sevenDaysAgo);
        return new PageBean(page.getTotal(),page.getResult());
    }
}
