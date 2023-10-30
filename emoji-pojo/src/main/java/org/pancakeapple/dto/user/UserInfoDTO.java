package org.pancakeapple.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pancakeapple.enumeration.Gender;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "用户修改信息时传递的数据模型")
public class UserInfoDTO {
    @Schema(description = "邮箱",defaultValue = "123456@qq.com",requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String email;

    @Schema(description = "用户名",defaultValue = "user",requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String username;

    @Schema(description = "性别(必须MALE/FEMALE)",defaultValue = "MALE",requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Gender gender;

    @Schema(description = "头像url",defaultValue = "https://xxx/xxx",requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String profilePhoto;

    @Schema(description = "个性签名",defaultValue = "个性签名",requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String signature;
}
