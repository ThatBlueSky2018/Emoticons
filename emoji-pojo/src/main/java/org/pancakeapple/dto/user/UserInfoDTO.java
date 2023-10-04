package org.pancakeapple.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "用户修改信息时传递的数据模型")
public class UserInfoDTO {
    @Schema(description = "用户id",defaultValue = "1",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "用户名",defaultValue = "user",requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String username;

    @Schema(description = "头像url",defaultValue = "https://xxx/xxx",requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String profilePhoto;
}
