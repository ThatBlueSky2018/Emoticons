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
    private Long author;
    private LocalDateTime uploadTime;
}
