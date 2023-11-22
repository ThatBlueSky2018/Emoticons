package org.pancakeapple.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.pancakeapple.annotation.AutoIncrease;
import org.pancakeapple.constant.DataConstant;
import org.pancakeapple.constant.MessageConstant;
import org.pancakeapple.constant.StatusConstant;
import org.pancakeapple.context.BaseContext;
import org.pancakeapple.dto.emoji.EmojiUploadDTO;
import org.pancakeapple.dto.emoji.PageQueryDTO;
import org.pancakeapple.entity.emoji.Emoji;
import org.pancakeapple.entity.emoji.EmojiTag;
import org.pancakeapple.enumeration.BehaviorType;
import org.pancakeapple.mapper.emoji.EmojiMapper;
import org.pancakeapple.mapper.emoji.EmojiTagMapper;
import org.pancakeapple.result.PageBean;
import org.pancakeapple.service.EmojiService;
import org.pancakeapple.vo.emoji.EmojiDetailVO;
import org.pancakeapple.vo.emoji.EmojiGeneralVO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
    private RabbitTemplate rabbitTemplate;

    /**
     * 表情包上传
     * 同时保存表情包数据以及对应的标签信息（可以为空）
     * @param emojiUploadDTO 数据封装
     */
    @Transactional
    public void saveWithTag(EmojiUploadDTO emojiUploadDTO) {
        Emoji emoji=new Emoji();
        BeanUtils.copyProperties(emojiUploadDTO,emoji);

        //1.向表情包信息表中插入一条数据
        emoji.setHits(DataConstant.DEFAULT_HITS);
        emoji.setDownloads(DataConstant.DEFAULT_DOWNLOADS);
        emoji.setComments(DataConstant.DEFAULT_COMMENTS);
        emoji.setFavorite(DataConstant.DEFAULT_FAVORITE);
        emoji.setStatus(StatusConstant.ABLE);
        emojiMapper.insert(emoji);

        //2.向表情包与标签的对应关系表中插入若干条数据
        //接收到前端的数据中，标签列表是id列表
        List<Long> tags= emojiUploadDTO.getTags();
        if(tags!=null && tags.size()>0) {
            tags.forEach(tag -> {
                EmojiTag emojiTag=new EmojiTag();
                emojiTag.setEmojiId(emoji.getId());
                emojiTag.setTagId(tag);
                emojiTagMapper.insert(emojiTag);
            });
        }

        //3.通知RabbitMQ,向搜索引擎中添加一条数据
        rabbitTemplate.convertAndSend(MessageConstant.ES_POST_QUEUE,emoji.getId());
    }

    /**
     * 表情包分页查询
     * @param pageQueryDTO 封装页码数以及每页记录数
     * @return 总记录数以及当前页记录列表
     */
    @Override
    public PageBean pageQuery(PageQueryDTO pageQueryDTO) {
        PageHelper.startPage(pageQueryDTO.getPage(), pageQueryDTO.getPageSize());
        Page<EmojiGeneralVO> page=emojiMapper.pageQuery();
        return new PageBean(page.getTotal(),page.getResult());
    }

    /**
     * 根据id查询表情包详细信息
     * @param id 主键id
     * @return 详细信息
     */
    @Override
    @AutoIncrease(type = BehaviorType.CLICK)
    public EmojiDetailVO getById(Long id) {
        EmojiDetailVO emojiDetailVO = emojiMapper.getById(id);
        List<String> tags = emojiMapper.getTagsById(id);
        emojiDetailVO.setTags(tags);
        return emojiDetailVO;
    }

    /**
     * 查询自己上传的表情包
     * @param pageQueryDTO 分页查询参数
     * @return 分页查询结果
     */
    @Override
    public PageBean getUploaded(PageQueryDTO pageQueryDTO) {
        PageHelper.startPage(pageQueryDTO.getPage(),pageQueryDTO.getPageSize());
        Page<EmojiGeneralVO> page=emojiMapper.getByUserId(BaseContext.getCurrentId());
        return new PageBean(page.getTotal(),page.getResult());
    }

    /**
     * 查询某个用户上传的表情包
     * @param userId       用户id
     * @param pageQueryDTO 分页查询参数
     * @return 分页查询结果
     */
    @Override
    public PageBean getByUserId(Long userId, PageQueryDTO pageQueryDTO) {
        PageHelper.startPage(pageQueryDTO.getPage(),pageQueryDTO.getPageSize());
        Page<EmojiGeneralVO> page = emojiMapper.getByUserId(userId);
        return new PageBean(page.getTotal(),page.getResult());
    }
}
