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
@Schema(description = "表情包整体信息,用于主界面展示")
public class EmojiGeneralVO {
    @Schema(description = "表情包id(可根据id查看详情)",defaultValue = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "表情包名称",defaultValue = "xxx",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(description = "表情包存储url",defaultValue = "https://xxx/xxx",requiredMode = Schema.RequiredMode.REQUIRED)
    private String url;

    @Schema(description = "上传者id(可据此查看作者主页)",defaultValue = "1",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long createUser;

    @Schema(description = "上传者头像url",defaultValue = "https://xxx/xxx",requiredMode = Schema.RequiredMode.REQUIRED)
    private String profilePhoto;
}
