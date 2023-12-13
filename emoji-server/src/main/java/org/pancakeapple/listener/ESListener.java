package org.pancakeapple.listener;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.UpdateResponse;
import lombok.extern.slf4j.Slf4j;
import org.pancakeapple.constant.DataConstant;
import org.pancakeapple.dto.search.UpdateDocumentDTO;
import org.pancakeapple.entity.document.EmojiDoc;
import org.pancakeapple.entity.emoji.Emoji;
import org.pancakeapple.entity.emoji.EmojiTag;
import org.pancakeapple.entity.emoji.Tag;
import org.pancakeapple.enumeration.BehaviorType;
import org.pancakeapple.mapper.emoji.EmojiMapper;
import org.pancakeapple.mapper.emoji.EmojiTagMapper;
import org.pancakeapple.mapper.emoji.TagMapper;
import org.pancakeapple.mapper.user.UserMapper;
import org.pancakeapple.vo.user.UserInfoVO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class ESListener {
    @Autowired
    private EmojiMapper emojiMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EmojiTagMapper emojiTagMapper;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private ElasticsearchClient client;

    /**
     * 监听文档新增消息队列的内容
     * 根据emojiId，构造一个文档加入索引库
     * @param emojiId 上传的表情包的id
     */
    @RabbitListener(queues = "es.post.queue")
    public void listenPostQueue(Long emojiId) throws IOException {
        Emoji emoji = emojiMapper.getEmojiById(emojiId);
        UserInfoVO createUser = userMapper.getById(emoji.getCreateUser());
        List<EmojiTag> emojiTags = emojiTagMapper.getByEmojiId(emojiId);
        List<String> tagList=new ArrayList<>();
        emojiTags.forEach(emojiTag -> {
            Tag tag = tagMapper.getById(emojiTag.getTagId());
            tagList.add(tag.getName());
        });
        String tags = String.join(",", tagList);
        EmojiDoc emojiDoc = new EmojiDoc(emoji);
        emojiDoc.setTags(tags);
        emojiDoc.setProfilePhoto(createUser.getProfilePhoto());

        IndexResponse response = client.index(s -> s
                .index(DataConstant.ES_INDEX_NAME)
                .id(emojiId.toString())
                .document(emojiDoc)
        );
        if(response!=null) {
            log.info("向ES中添加一条数据：{}",emojiDoc);
        }
    }

    /**
     * 监听文档更新消息队列的内容
     * @param updateDocumentDTO 包含id、操作类型
     * 根据操作类型决定要更新的字段
     */
    @RabbitListener(queues = "es.update.queue")
    public void listenUpdateQueue(UpdateDocumentDTO updateDocumentDTO) throws IOException {
        BehaviorType behaviorType = updateDocumentDTO.getBehaviorType();
        Emoji emoji = emojiMapper.getEmojiById(updateDocumentDTO.getId());
        String field;
        Object data;
        if(behaviorType == BehaviorType.CLICK) {
            field = "hits";
            data = emoji.getHits();
        } else if (behaviorType == BehaviorType.COMMENT || behaviorType == BehaviorType.REPLY) {
            field = "comments";
            data = emoji.getComments();
        } else if (behaviorType == BehaviorType.FAVORITE) {
            field = "favorite";
            data = emoji.getFavorite();
        } else if (behaviorType == BehaviorType.CALCULATE_HOT_INDEX) {
            field = "hotIndex";
            data = emoji.getHotIndex();
        } else {
            field = "downloads";
            data = emoji.getDownloads();
        }
        Map<String,Object> map=new HashMap<>();
        map.put(field,data);
        UpdateResponse<Object> response = client.update(e -> e
                        .index(DataConstant.ES_INDEX_NAME)
                        .id(updateDocumentDTO.getId().toString())
                        .doc(map)
                , Object.class);
        if(response != null) {
            log.info("成功更新ES文档：{}",map);
        }
    }
}
