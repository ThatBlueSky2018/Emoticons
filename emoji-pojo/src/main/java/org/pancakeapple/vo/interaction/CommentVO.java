package org.pancakeapple.vo.interaction;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "某个表情包地评论信息(不包括回复)")
public class CommentVO {
    @Schema(description = "主键id",defaultValue = "1",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "表情包id",defaultValue = "1",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long emojiId;

    @Schema(description = "评论内容",defaultValue = "****",requiredMode = Schema.RequiredMode.REQUIRED)
    private String content;

    @Schema(description = "评论时间",defaultValue = "2023-10-10 08:00:00",requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Schema(description = "发布者id",defaultValue = "1",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long createUser;

    @Schema(description = "发布者头像",defaultValue = "https://xxx/xxx",requiredMode = Schema.RequiredMode.REQUIRED)
    private String profilePhoto;

    @Schema(description = "发布者用户名",defaultValue = "user",requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;
}
