package org.pancakeapple.entity.document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pancakeapple.entity.emoji.Emoji;

import java.time.LocalDateTime;

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
    private Double hotIndex;
    private String similarList;

    private Long createUser; //上传者

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime; //上传时间
    private String profilePhoto; //上传者头像

    public EmojiDoc(Emoji emoji) {
        this.id = emoji.getId();
        this.name = emoji.getName();
        this.description = emoji.getDescription();
        this.url = emoji.getUrl();
        this.hits = emoji.getHits();
        this.comments = emoji.getComments();
        this.downloads = emoji.getDownloads();
        this.favorite = emoji.getFavorite();
        this.hotIndex = emoji.getHotIndex();
        this.similarList = emoji.getSimilarList();
        this.createUser = emoji.getCreateUser();
        this.createTime = emoji.getCreateTime();
    }
}
