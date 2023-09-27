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
@Schema(description = "用户注册后返回的数据模型")
public class RegisterVO {
    @Schema(description = "用户注册后返回的信息", defaultValue = "注册成功!", requiredMode = Schema.RequiredMode.REQUIRED)
    private String msg;
}
