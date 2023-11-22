package org.pancakeapple.dto.search;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "搜索表情包时需要传递的参数")
public class SearchParams {
    @Schema(description = "类型(1,2,3,4)",defaultValue = "1",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer kind; //类型

    @Schema(description = "搜索关键字",defaultValue = "abc",requiredMode = Schema.RequiredMode.REQUIRED)
    private String key;

    @Schema(description = "页码",defaultValue = "1",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer page;

    @Schema(description = "每页数量",defaultValue = "10",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer pageSize;

    @Schema(description = "排序方式(0,1,2,3)",defaultValue = "0",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer sort;
}
