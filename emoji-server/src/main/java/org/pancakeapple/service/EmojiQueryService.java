package org.pancakeapple.service;

import org.pancakeapple.dto.emoji.PageQueryDTO;
import org.pancakeapple.result.PageBean;

public interface EmojiQueryService {
    /**
     * 查询静态表情包
     * @param pageQueryDTO 分页查询参数
     * @return 分页查询结果
     */
    PageBean getStatic(PageQueryDTO pageQueryDTO);

    /**
     * 查询动态表情包
     * @param pageQueryDTO 分页查询参数
     * @return 分页查询结果
     */
    PageBean getDynamic(PageQueryDTO pageQueryDTO);

    /**
     * 查询最新表情包
     * @param pageQueryDTO 分页查询参数
     * @return 分页查询结果
     */
    PageBean getLatest(PageQueryDTO pageQueryDTO);

    /**
     * 查询热门表情包
     * @param pageQueryDTO 分页查询参数
     * @return 分页查询结果
     */
    PageBean getPopular(PageQueryDTO pageQueryDTO);
}
