package org.pancakeapple.service.impl;

import org.pancakeapple.context.BaseContext;
import org.pancakeapple.mapper.interaction.MessageMapper;
import org.pancakeapple.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;

    /**
     * 查询未读消息数量
     * @return 未读消息数量
     */
    @Override
    public Integer unreadCount() {
        return  messageMapper.getUnReadCount(BaseContext.getCurrentId());
    }
}
