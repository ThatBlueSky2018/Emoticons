package org.pancakeapple.entity.interaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pancakeapple.enumeration.ContentType;
import org.pancakeapple.enumeration.MessageType;

import java.time.LocalDateTime;

/**
 * 消息实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {
    public static Integer UN_READ=0;
    public static Integer HAS_READ=1;

    private Long id;
    private MessageType messageType;
    private Integer isRead;  //0表示未读，1表示已读
    private Long senderId;
    private Long receiverId;
    private ContentType contentType;  //消息内容类型
    private String content;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
