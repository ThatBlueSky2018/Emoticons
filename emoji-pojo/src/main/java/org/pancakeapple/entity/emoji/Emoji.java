package org.pancakeapple.entity.emoji;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 表情包实体类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Emoji {
    private Long id;
    private String name;
    private String description;
    private String url;
    private Integer hits;
    private Integer comments;
    private Integer downloads;
    private Integer favorite;
    private Double hotIndex;

    private Integer status;  //是否通过审核

    private LocalDateTime createTime;
    private Long createUser;
    private LocalDateTime updateTime;
    private Long updateUser;

}
