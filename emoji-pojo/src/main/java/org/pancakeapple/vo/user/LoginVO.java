package org.pancakeapple.vo.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "用户登录后返回的数据模型")
public class LoginVO {
    @Schema(description = "用户id", defaultValue = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "用户名", defaultValue = "user",requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    @Schema(description = "jwt令牌",
            defaultValue = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJVU0VSIl0sImlkIjoyLCJ1c2VybmFtZSI6InVzZXIxIiwiZXhwIjoxNjk1MTM5OTkxfQ.-2UcMSLeGWbYb1ehHsOOEpg9PwhTvBHLu7hDddLuLXU",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String token;
}
