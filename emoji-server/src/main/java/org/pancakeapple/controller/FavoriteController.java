package org.pancakeapple.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.pancakeapple.constant.MessageConstant;
import org.pancakeapple.result.Result;
import org.pancakeapple.service.FavoriteService;
import org.pancakeapple.vo.emoji.EmojiGeneralVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin
@Tag(name = "用户收藏表情包的接口")
@RequestMapping("/favorite")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    /**
     * 用户收藏表情包
     * @param emojiId 表情包id
     * @return 提示信息
     */
    @PostMapping
    @Operation (summary = "收藏表情包")
    @CacheEvict(cacheNames = "favoriteList", key = "T(org.pancakeapple.context.BaseContext).getCurrentId()")
    public Result<String> favorite(Long emojiId) {
        log.info("用户收藏表情包：{}",emojiId);
        favoriteService.favorite(emojiId);
        return Result.success(MessageConstant.FAVORITE_SUCCESS);
    }

    /**
     * 用户查询收藏列表
     * @return 收藏列表
     */
    @GetMapping
    @Operation(summary = "查看收藏列表")
    @Cacheable(cacheNames = "favoriteList", key = "T(org.pancakeapple.context.BaseContext).getCurrentId()")
    public Result<List<EmojiGeneralVO>> list() {
        log.info("用户查看收藏列表");
        List<EmojiGeneralVO> list=favoriteService.list();
        return Result.success(list);
    }

    /**
     * 用户取消收藏表情包
     * @param emojiId 表情包id
     * @return 提示信息
     */
    @DeleteMapping
    @Operation(summary = "取消收藏")
    @CacheEvict(cacheNames = "favoriteList", key = "T(org.pancakeapple.context.BaseContext).getCurrentId()")
    public Result<String> cancelFavorite(Long emojiId) {
        log.info("用户取消收藏，表情包id:{}",emojiId);
        favoriteService.cancelFavorite(emojiId);
        return Result.success(MessageConstant.CANCEL_FAVORITE_SUCCESS);
    }
}
