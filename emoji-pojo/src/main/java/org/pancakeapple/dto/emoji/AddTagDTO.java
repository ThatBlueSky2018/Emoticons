package org.pancakeapple.dto.emoji;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "添加表情包标签时传递的数据模型")
public class AddTagDTO {
    @Schema(description = "标签名",defaultValue = "搞笑",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;
}
