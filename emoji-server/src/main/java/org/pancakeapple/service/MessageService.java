package org.pancakeapple.service;

public interface MessageService {
    /**
     * 查询未读消息数量
     * @return 未读消息数量
     */
    Integer unreadCount();
}
