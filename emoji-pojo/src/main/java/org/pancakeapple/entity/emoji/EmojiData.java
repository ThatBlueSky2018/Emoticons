package org.pancakeapple.entity.emoji;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 表情包相关数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmojiData {
    private Long id;
    private Long emojiId;
    private Integer hits;  //点击量
    private Integer downloads;  //下载量
    private Integer favorite;  //收藏量
}
