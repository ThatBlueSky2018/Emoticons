package org.pancakeapple.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.pancakeapple.context.BaseContext;
import org.pancakeapple.dto.emoji.PageQueryDTO;
import org.pancakeapple.mapper.interaction.MessageMapper;
import org.pancakeapple.result.PageBean;
import org.pancakeapple.service.MessageService;
import org.pancakeapple.vo.interaction.MessageDetailVO;
import org.pancakeapple.vo.interaction.MessageGeneralVO;
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

    /**
     * 查询消息列表
     * @return 分页查询结果
     */
    @Override
    public PageBean getMessageList(PageQueryDTO pageQueryDTO) {
        PageHelper.startPage(pageQueryDTO.getPage(),pageQueryDTO.getPageSize());
        Page<MessageGeneralVO> page=messageMapper.getMessageList(BaseContext.getCurrentId());
        return new PageBean(page.getTotal(),page.getResult());
    }

    /**
     * 根据id查询详细信息
     * @param id 消息id
     * @return 详细信息
     */
    @Override
    public MessageDetailVO getById(Long id) {
        MessageDetailVO messageDetailVO=messageMapper.getById(id);

        //如果是未读消息，设置为已读
        if(messageDetailVO.getIsRead() == 0) {
            messageMapper.read(id);
        }
        return messageDetailVO;
    }
}
