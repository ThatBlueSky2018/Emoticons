package org.pancakeapple.vo.emoji;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "表情包详细信息")
public class EmojiDetailVO {
    @Schema(description = "表情包id",defaultValue = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "表情包名称",defaultValue = "xxx",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(description = "表情包描述",defaultValue = "xxx",requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;

    @Schema(description = "表情包存储url",defaultValue = "https://xxx/xxx",requiredMode = Schema.RequiredMode.REQUIRED)
    private String url;

    @Schema(description = "上传者id(可据此查看作者主页)",defaultValue = "1",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long createUser;

    @Schema(description = "上传时间",defaultValue = "2023-10-01 00:00:00",requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime uploadTime;

    @Schema(description = "点击量",defaultValue = "0",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer hits;

    @Schema(description = "评论量",defaultValue = "0",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer comments;

    @Schema(description = "下载量",defaultValue = "0",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer downloads;

    @Schema(description = "收藏量",defaultValue = "0",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer favorite;

    @Schema(description = "标签列表",defaultValue = "['标签1','标签2']",requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<String> tags;

    @Schema(description = "相似表情包列表(字符串类型)",defaultValue = "1,2,3",requiredMode = Schema.RequiredMode.REQUIRED)
    private String similarList;
}
