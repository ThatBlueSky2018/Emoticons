package org.pancakeapple.dto.emoji;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "上传表情包信息时传递的数据模型")
public class EmojiUploadDTO {
    @Schema(description = "表情包名称",defaultValue = "流汗黄豆",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(description = "表情包描述", defaultValue = "xxx",requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String description;

    @Schema(description = "存储路径(用户选择文件后已经返回url)",defaultValue = "https://xxx/xxx",requiredMode = Schema.RequiredMode.REQUIRED)
    private String url;

    @Schema(description = "表情包标签列表",requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<Long> tags=new ArrayList<>();
}
