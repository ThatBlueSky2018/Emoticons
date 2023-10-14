package org.pancakeapple.vo.emoji;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "标签整体信息")
public class TagGeneralVO implements Serializable {
    @Schema(description = "主键id",defaultValue = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "标签名称",defaultValue = "xxx",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;
}
