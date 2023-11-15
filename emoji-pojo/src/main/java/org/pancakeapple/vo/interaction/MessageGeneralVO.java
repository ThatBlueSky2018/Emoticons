package org.pancakeapple.vo.interaction;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "消息大体信息")
public class MessageGeneralVO {
    @Schema(description = "消息id",defaultValue = "1",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "是否已读(0表示已读，1表示未读)",defaultValue = "0",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer isRead;

    @Schema(description = "发送者id",defaultValue = "2",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long senderId;

    @Schema(description = "发送者头像",defaultValue = "https://xxx/xxx",requiredMode = Schema.RequiredMode.REQUIRED)
    private String profilePhoto;

    @Schema(description = "消息内容",defaultValue = "你好！",requiredMode = Schema.RequiredMode.REQUIRED)
    private String content;

    @Schema(description = "发送时间",defaultValue = "2023-11-11 08:00:00",requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
