package org.pancakeapple.mapper.interaction;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.pancakeapple.entity.interaction.Message;
import org.pancakeapple.vo.interaction.MessageDetailVO;
import org.pancakeapple.vo.interaction.MessageGeneralVO;

@Mapper
public interface MessageMapper {
    @Insert("insert into tb_message(message_type,is_read, sender_id, receiver_id, content_type, content)" +
            "values (#{messageType},#{isRead},#{senderId},#{receiverId},#{contentType},#{content})")
    void insert(Message message);

    @Select("select count(*) from tb_message where receiver_id=#{receiverId} and is_read=0")
    Integer getUnReadCount(Long receiverId);

    /**
     * 查询消息列表
     * @param receiverId 接收者id
     * @return Page
     */
    Page<MessageGeneralVO> getMessageList(Long receiverId);

    /**
     * 根据id查询消息详情
     * @param id 消息id
     * @return 消息详细信息
     */
    MessageDetailVO getById(Long id);

    /**
     * 设置消息的状态为已读
     * @param id 消息id
     */
    @Update("update tb_message set is_read=1 and update_time=now() where id=#{id}")
    void read(Long id);
}
