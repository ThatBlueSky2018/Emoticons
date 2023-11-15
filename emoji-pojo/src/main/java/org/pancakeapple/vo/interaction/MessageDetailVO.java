package org.pancakeapple.vo.interaction;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pancakeapple.enumeration.ContentType;
import org.pancakeapple.enumeration.MessageType;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "消息详细信息")
public class MessageDetailVO {
    @Schema(description = "消息id")
    private Long id;

    @Schema(description = "消息类型",defaultValue = "COMMENT",requiredMode = Schema.RequiredMode.REQUIRED)
    private MessageType messageType; //消息类型

    @Schema(description = "是否已读",defaultValue = "1",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer isRead; //0表示未读，1表示已读

    @Schema(description = "发送者id",defaultValue = "2",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long senderId;  //发送者id

    @Schema(description = "发送者头像",defaultValue = "https://xxx/xxx",requiredMode = Schema.RequiredMode.REQUIRED)
    private String profilePhoto; //发送者头像

    @Schema(description = "内容类型",defaultValue = "TEXT",requiredMode = Schema.RequiredMode.REQUIRED)
    private ContentType contentType;

    @Schema(description = "消息内容",defaultValue = "你好！",requiredMode = Schema.RequiredMode.REQUIRED)
    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "发送时间")
    private LocalDateTime createTime;
}
