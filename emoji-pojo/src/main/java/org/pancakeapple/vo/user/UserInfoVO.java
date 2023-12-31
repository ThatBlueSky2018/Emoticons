package org.pancakeapple.vo.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pancakeapple.enumeration.Gender;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "用户信息")
public class UserInfoVO {
    @Schema(description = "用户id",defaultValue = "1",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer id;

    @Schema(description = "邮箱",defaultValue = "123456@qq.com",requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Schema(description = "用户名",defaultValue = "xxx",requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    @Schema(description = "性别",defaultValue = "MALE",requiredMode = Schema.RequiredMode.REQUIRED)
    private Gender gender;

    @Schema(description = "头像url",defaultValue = "https://xxx/xxx",requiredMode = Schema.RequiredMode.REQUIRED)
    private String profilePhoto;

    @Schema(description = "个性签名",defaultValue = "我的个性签名",requiredMode = Schema.RequiredMode.REQUIRED)
    private String signature;

    @Schema(description = "是否是官方账号",defaultValue = "0",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer isOfficial;

    @Schema(description = "是否公开收藏夹",defaultValue = "0",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer publicFavorite;

    @Schema(description = "用户上传表情包数量",defaultValue = "0",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer uploadCount;

    @Schema(description = "用户收藏表情包数量",defaultValue = "0",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer favoriteCount;

    @Schema(description = "注册时间",defaultValue = "2023-10-01 00:00:00",requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createTime;
}
