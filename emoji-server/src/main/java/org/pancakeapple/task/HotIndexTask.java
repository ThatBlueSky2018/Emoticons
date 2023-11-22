package org.pancakeapple.task;

import lombok.extern.slf4j.Slf4j;
import org.pancakeapple.algorithm.PopularAlgorithm;
import org.pancakeapple.constant.MessageConstant;
import org.pancakeapple.dto.search.UpdateDocumentDTO;
import org.pancakeapple.entity.emoji.Emoji;
import org.pancakeapple.enumeration.BehaviorType;
import org.pancakeapple.mapper.emoji.EmojiMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class HotIndexTask {
    @Autowired
    private EmojiMapper emojiMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Scheduled(cron = "0 0 2 * * ?")
    public void updateHotIndex() {
        log.info("更新表情包热门指数");
        int page = 0;
        Long count = emojiMapper.getCount();
        Integer min_hits = emojiMapper.getMinHits();
        Integer max_hits = emojiMapper.getMaxHits();
        Integer min_comments = emojiMapper.getMinComments();
        Integer max_comments = emojiMapper.getMaxComments();
        Integer min_downloads = emojiMapper.getMinDownloads();
        Integer max_downloads = emojiMapper.getMaxDownloads();
        Integer min_favorite = emojiMapper.getMinFavorite();
        Integer max_favorite = emojiMapper.getMaxFavorite();

        int pageSize = 500;
        while((long) page * pageSize <count) {
            page++;
            List<Emoji> emojis = emojiMapper.page((page - 1) * pageSize, pageSize);
            for (Emoji emoji : emojis) {
                double hits_normalization = PopularAlgorithm.normalization(min_hits, max_hits, emoji.getHits());
                double comments_normalization = PopularAlgorithm.normalization(min_comments, max_comments, emoji.getComments());
                double downloads_normalization = PopularAlgorithm.normalization(min_downloads, max_downloads, emoji.getDownloads());
                double favorite_normalization = PopularAlgorithm.normalization(min_favorite, max_favorite, emoji.getFavorite());
                Duration duration = Duration.between(emoji.getCreateTime(), LocalDateTime.now());
                double timeDecay = PopularAlgorithm.timeDecay(duration.toDays());
                Double hotIndex = PopularAlgorithm.calculateHotIndex(hits_normalization, comments_normalization, downloads_normalization, favorite_normalization, timeDecay);
                emojiMapper.updateHotIndex(hotIndex,emoji.getId());

                UpdateDocumentDTO updateDocumentDTO = UpdateDocumentDTO.builder()
                        .id(emoji.getId())
                        .behaviorType(BehaviorType.CALCULATE_HOT_INDEX)
                        .build();
                rabbitTemplate.convertAndSend(MessageConstant.ES_UPDATE_QUEUE,updateDocumentDTO);
            }
        }
    }
}
