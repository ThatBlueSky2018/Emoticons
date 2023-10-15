package org.pancakeapple.dto.emoji;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "添加表情包分类时传递的数据")
public class AddTypeDTO {
    @Schema(description = "分类名称",defaultValue = "xxx",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;
}
