package org.pancakeapple.entity.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmojiDoc {
    private Long id;
    private String name;
    private String description;
    private String url;
    private String tags;  //标签,使用逗号分隔存储
    private Integer hits;
    private Integer comments;
    private Integer downloads;
    private Integer favorite;

    private Long createUser; //上传者
    private String profilePhoto; //上传者头像
}
