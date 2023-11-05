package org.pancakeapple.dto.emoji;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "分页查询参数封装")
public class PageQueryDTO implements Serializable {
    @Schema(description = "页码",defaultValue = "1",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer page;

    @Schema(description = "每页记录数",defaultValue = "10",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer pageSize;
}
