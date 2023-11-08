package org.pancakeapple.mapper.interaction;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.pancakeapple.entity.interaction.Message;

@Mapper
public interface MessageMapper {
    @Insert("insert into tb_message(message_type,is_read, sender_id, receiver_id, content_type, content)" +
            "values (#{messageType},#{isRead},#{senderId},#{receiverId},#{contentType},#{content})")
    void insert(Message message);

    @Select("select count(*) from tb_message where receiver_id=#{receiverId} and is_read=0")
    Integer getUnReadCount(Long receiverId);
}
