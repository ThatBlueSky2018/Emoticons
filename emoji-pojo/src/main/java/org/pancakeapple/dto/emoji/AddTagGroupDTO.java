package org.pancakeapple.dto.emoji;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "添加标签分组时传递的数据")
public class AddTagGroupDTO {
    @Schema(description = "分组名称",defaultValue = "搞笑",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;
}
