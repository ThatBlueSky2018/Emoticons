package org.pancakeapple.dto.interaction;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "评论表情包时传送的数据模型")
public class ReplyDTO {
    @Schema(description = "表情包id", defaultValue = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long emojiId;

    @Schema(description = "要回复的评论id",defaultValue = "1",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long commentId;

    @Schema(description = "评论内容", defaultValue = "xxx", requiredMode = Schema.RequiredMode.REQUIRED)
    private String content;
}