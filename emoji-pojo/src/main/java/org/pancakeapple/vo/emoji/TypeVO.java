package org.pancakeapple.vo.emoji;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "表情包分类的信息")
public class TypeVO {
    @Schema(description = "分类id",defaultValue = "1",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "分类名称",defaultValue = "xxx",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(description = "引用数量",defaultValue = "0",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long refCount;
}
