package org.pancakeapple.service;

import org.pancakeapple.dto.emoji.PageQueryDTO;
import org.pancakeapple.result.PageBean;
import org.pancakeapple.vo.interaction.MessageDetailVO;

public interface MessageService {
    /**
     * 查询未读消息数量
     * @return 未读消息数量
     */
    Integer unreadCount();

    /**
     * 查询消息列表
     * @return 分页查询结果
     */
    PageBean getMessageList(PageQueryDTO pageQueryDTO);

    /**
     * 根据id查询详细信息
     * @param id 消息id
     * @return 详细信息
     */
    MessageDetailVO getById(Long id);
}
