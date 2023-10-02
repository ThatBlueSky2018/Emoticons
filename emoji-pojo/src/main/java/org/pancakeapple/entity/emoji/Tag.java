package org.pancakeapple.entity.emoji;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 表情包标签实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tag {
    private Long id;
    private String name;
}
