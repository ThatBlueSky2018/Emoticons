package org.pancakeapple.entity.interaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 用户收藏实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Favorite {
    private Long id;
    private Long userId;
    private Long emojiId;

    private LocalDateTime createTime;
}
