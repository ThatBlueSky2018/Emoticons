package org.pancakeapple.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "用户登录时传递的数据模型")
public class LoginDTO {
    @Schema(description = "用户名", defaultValue = "user",requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    @Schema(description = "密码", defaultValue = "123456",requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
}
