package org.pancakeapple.service;

import org.pancakeapple.dto.emoji.EmojiUploadDTO;
import org.pancakeapple.dto.emoji.PageQueryDTO;
import org.pancakeapple.result.PageBean;
import org.pancakeapple.vo.emoji.EmojiDetailVO;
import org.pancakeapple.vo.emoji.EmojiGeneralVO;

import java.util.List;

public interface EmojiService {
    /**
     * 表情包上传
     * @param emojiUploadDTO 数据封装
     */
    void saveWithTag(EmojiUploadDTO emojiUploadDTO);

    /**
     * 表情包分页查询
     * @param pageQueryDTO 封装页码数以及每页记录数
     * @return 总记录数以及当前页记录列表
     */
    PageBean pageQuery(PageQueryDTO pageQueryDTO);

    /**
     * 根据id查询表情包详细信息
     * @param id 主键id
     * @return 详细信息
     */
    EmojiDetailVO getById(Long id);

    /**
     * 查询自己上传的表情包
     * @param pageQueryDTO 分页查询参数
     * @return 分页查询结果
     */
    PageBean getUploaded(PageQueryDTO pageQueryDTO);

    /**
     * 查询某个用户上传的表情包
     * @param userId 用户id
     * @param pageQueryDTO 分页查询参数
     * @return 分页查询结果
     */
    PageBean getByUserId(Long userId, PageQueryDTO pageQueryDTO);

    /**
     * 下载表情包
     * @param emojiId 表情包id
     */
    void download(Long emojiId);

    /**
     * 根据相似列表查询相似表情包
     * @param similarList 相似id列表
     * @return 表情包列表
     */
    List<EmojiGeneralVO> getSimilar(List<Long> similarList);
}
