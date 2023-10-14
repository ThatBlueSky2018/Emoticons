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
@Schema(description = "标签分组信息")
public class TagGroupVO {
    @Schema(description = "分组id",defaultValue = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "分组名称",defaultValue = "xxx",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;
}
