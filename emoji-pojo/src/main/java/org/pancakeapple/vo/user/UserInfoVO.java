package org.pancakeapple.vo.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "用户信息")
public class UserInfoVO {
    @Schema(description = "用户id",defaultValue = "1",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer id;

    @Schema(description = "用户名",defaultValue = "xxx",requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    @Schema(description = "头像url",defaultValue = "https://xxx/xxx",requiredMode = Schema.RequiredMode.REQUIRED)
    private String profilePhoto;

    @Schema(description = "注册时间",defaultValue = "2023-10-01 00:00:00",requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime registerTime;
}
