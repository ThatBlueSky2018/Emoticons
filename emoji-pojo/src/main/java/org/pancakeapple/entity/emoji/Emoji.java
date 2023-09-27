package org.pancakeapple.entity.emoji;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
