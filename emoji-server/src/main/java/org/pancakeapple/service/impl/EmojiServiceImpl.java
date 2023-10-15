package org.pancakeapple.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.pancakeapple.constant.DataConstant;
import org.pancakeapple.constant.MessageConstant;
import org.pancakeapple.constant.StatusConstant;
import org.pancakeapple.dto.emoji.EmojiUploadDTO;
import org.pancakeapple.dto.emoji.EmojiPageQueryDTO;
import org.pancakeapple.entity.emoji.Emoji;
import org.pancakeapple.entity.emoji.EmojiTag;
import org.pancakeapple.entity.emoji.EmojiType;
import org.pancakeapple.exception.EmojiTypeNotExistException;
import org.pancakeapple.mapper.emoji.EmojiMapper;
import org.pancakeapple.mapper.emoji.EmojiTagMapper;
import org.pancakeapple.mapper.emoji.TypeMapper;
import org.pancakeapple.result.PageBean;
import org.pancakeapple.service.EmojiService;
import org.pancakeapple.vo.emoji.EmojiDetailVO;
import org.pancakeapple.vo.emoji.EmojiGeneralVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class EmojiServiceImpl implements EmojiService {

    @Autowired
    private EmojiMapper emojiMapper;

    @Autowired
    private EmojiTagMapper emojiTagMapper;

    @Autowired
    private TypeMapper typeMapper;

    /**
     * 表情包上传
     * 同时保存表情包数据以及对应的标签信息
     * @param emojiUploadDTO 数据封装
     */
    @Transactional
    public void saveWithTag(EmojiUploadDTO emojiUploadDTO) {
        Emoji emoji=new Emoji();
        BeanUtils.copyProperties(emojiUploadDTO,emoji);

        //1.查看类型是否存在
        EmojiType emojiType = typeMapper.getById(emojiUploadDTO.getTypeId());
        if(emojiType == null) {
            throw new EmojiTypeNotExistException(MessageConstant.EMOJI_TYPE_NOT_EXIST);
        }

        //2.向表情包信息表中插入一条数据
        emoji.setHits(DataConstant.DEFAULT_HITS);
        emoji.setDownloads(DataConstant.DEFAULT_DOWNLOADS);
        emoji.setFavorite(DataConstant.DEFAULT_FAVORITE);
        emoji.setStatus(StatusConstant.ABLE);
        emojiMapper.insert(emoji);

        //3.向表情包与标签的对应关系表中插入若干条数据
        //接收到前端的数据中，标签列表是id列表
        // TODO 后续是否需要改成标签名列表？代码是否需要优化？
        List<Long> tags= emojiUploadDTO.getTags();
        if(tags!=null && tags.size()>0) {
            tags.forEach(tag -> {
                EmojiTag emojiTag=new EmojiTag();
                emojiTag.setEmojiId(emoji.getId());
                emojiTag.setTagId(tag);
                emojiTagMapper.insert(emojiTag);
            });
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

    /**
     * 根据id查询表情包详细信息
     * @param id 主键id
     * @return 详细信息
     */
    @Override
    public EmojiDetailVO getById(Long id) {
        return emojiMapper.getById(id);
    }
}
