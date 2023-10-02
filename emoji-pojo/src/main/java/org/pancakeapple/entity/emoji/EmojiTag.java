package org.pancakeapple.entity.emoji;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Emoji与Tag的多对多关系
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmojiTag {
    private Integer id;
    private Long emojiId;  //表情包id
    private Long tagId;  //标签id
}
