package org.pancakeapple.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.pancakeapple.constant.DataConstant;
import org.pancakeapple.context.BaseContext;
import org.pancakeapple.dto.emoji.EmojiDTO;
import org.pancakeapple.dto.emoji.EmojiPageQueryDTO;
import org.pancakeapple.entity.emoji.Emoji;
import org.pancakeapple.entity.emoji.EmojiData;
import org.pancakeapple.entity.emoji.EmojiTag;
import org.pancakeapple.entity.emoji.Tag;
import org.pancakeapple.mapper.emoji.EmojiDataMapper;
import org.pancakeapple.mapper.emoji.EmojiMapper;
import org.pancakeapple.mapper.emoji.EmojiTagMapper;
import org.pancakeapple.result.PageBean;
import org.pancakeapple.service.EmojiService;
import org.pancakeapple.vo.emoji.EmojiGeneralVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class EmojiServiceImpl implements EmojiService {

    @Autowired
    private EmojiMapper emojiMapper;

    @Autowired
    private EmojiDataMapper emojiDataMapper;

    @Autowired
    private EmojiTagMapper emojiTagMapper;

    /**
     * 表情包上传
     * 同时保存表情包数据以及对应的标签信息
     * @param emojiDTO 数据封装
     */
    @Transactional
    public void saveWithTag(EmojiDTO emojiDTO) {
        Emoji emoji=new Emoji();
        BeanUtils.copyProperties(emojiDTO,emoji);

        //1.向表情包信息表中插入一条数据
        emoji.setAuthor(BaseContext.getCurrentId());
        emoji.setUploadTime(LocalDateTime.now());
        emojiMapper.insert(emoji);

        //2.向表情包数据表中插入一条数据
        EmojiData emojiData=new EmojiData();
        emojiData.setEmojiId(emoji.getId());  //查询回显，主键返回
        emojiData.setHits(DataConstant.DEFAULT_HITS);
        emojiData.setDownloads(DataConstant.DEFAULT_DOWNLOADS);
        emojiData.setFavorite(DataConstant.DEFAULT_FAVORITE);
        emojiDataMapper.insert(emojiData);

        //3.向表情包与标签的对应关系表中插入若干条数据
        //接收到前端的数据中，标签列表是id列表
        // TODO 后续是否需要改成标签名列表？代码是否需要优化？
        List<Tag> tags=emojiDTO.getTags();
        if(tags!=null && tags.size()>0) {
            List<EmojiTag> emojiTags=new ArrayList<>();
            tags.forEach(tag -> {
                EmojiTag emojiTag=new EmojiTag();
                emojiTag.setEmojiId(emoji.getId());
                emojiTag.setTagId(tag.getId());
                emojiTags.add(emojiTag);
            });
            emojiTagMapper.insertBatch(emojiTags);
        }
    }

    /**
     * 表情包分页查询
     * @param emojiPageQueryDTO 封装页码数以及每页记录数
     * @return 总记录数以及当前页记录列表
     */
    @Override
    public PageBean pageQuery(EmojiPageQueryDTO emojiPageQueryDTO) {
        PageHelper.startPage(emojiPageQueryDTO.getPage(),emojiPageQueryDTO.getPageSize());
        Page<EmojiGeneralVO> page=emojiMapper.pageQuery();
        return new PageBean(page.getTotal(),page.getResult());
    }
}
