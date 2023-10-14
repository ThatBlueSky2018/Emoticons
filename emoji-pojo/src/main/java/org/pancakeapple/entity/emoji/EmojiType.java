package org.pancakeapple.entity.emoji;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 表情包类型实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmojiType {
    private Long id;
    private String name;
    private Long refCount;

    private Integer status;  //是否通过审核

    private LocalDateTime createTime;
    private Long createUser;
    private LocalDateTime updateTime;
    private Long updateUser;
}
