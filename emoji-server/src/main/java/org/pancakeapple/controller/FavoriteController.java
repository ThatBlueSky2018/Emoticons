package org.pancakeapple.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.pancakeapple.constant.MessageConstant;
import org.pancakeapple.result.Result;
import org.pancakeapple.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@CrossOrigin
@Tag(name = "用户收藏表情包的接口")
@RequestMapping("/favorite")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    @PostMapping
    @Operation (summary = "收藏表情包")
    public Result<String> favorite(Long emojiId) {
        log.info("用户收藏表情包：{}",emojiId);
        favoriteService.favorite(emojiId);
        return Result.success(MessageConstant.FAVORITE_SUCCESS);
    }
}
