package org.pancakeapple.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.pancakeapple.constant.PromptConstant;
import org.pancakeapple.context.BaseContext;
import org.pancakeapple.dto.emoji.PageQueryDTO;
import org.pancakeapple.result.PageBean;
import org.pancakeapple.result.Result;
import org.pancakeapple.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Result<String> favorite(Long emojiId) {
        log.info("用户收藏表情包：{}",emojiId);
        favoriteService.favorite(emojiId);
        return Result.success(PromptConstant.FAVORITE_SUCCESS);
    }

    /**
     * 用户查询收藏列表
     * @return 收藏列表
     */
    @GetMapping
    @Operation(summary = "查看收藏列表(分页查询)")
    public Result<PageBean> list(PageQueryDTO pageQueryDTO) {
        log.info("用户查看收藏列表");
        PageBean pageBean=favoriteService.list(pageQueryDTO);
        return Result.success(pageBean);
    }

    /**
     * 用户取消收藏表情包
     * @param emojiId 表情包id
     * @return 提示信息
     */
    @DeleteMapping
    @Operation(summary = "取消收藏")
    public Result<String> cancelFavorite(Long emojiId) {
        log.info("用户取消收藏，表情包id:{}",emojiId);
        favoriteService.cancelFavorite(emojiId);
        return Result.success(PromptConstant.CANCEL_FAVORITE_SUCCESS);
    }

    /**
     * 用户设置收藏夹公开状态
     * @param isOpen 1表示公开，0表示私密
     * @return 提示信息
     */
    @PutMapping("/status")
    @Operation(summary = "修改收藏夹公开状态，1表示设置为公开，0表示设置为私密，重复设置后返回的code=0")
    public Result<String> setFavoritesPermission(Integer isOpen) {
        log.info("用户将收藏夹设置为公开，用户id：{}", BaseContext.getCurrentId());
        favoriteService.publicFavorite(isOpen);
        return Result.success(PromptConstant.SET_FAVORITES_PERMISSION_SUCCESS);
    }

    @GetMapping("/list/{userId}")
    @Operation(summary = "查询其他用户的收藏夹(分页查询)")
    public Result<PageBean> getOtherFavoriteList(@PathVariable Long userId, PageQueryDTO pageQueryDTO) {
        log.info("查询某个用户的收藏列表：{}",userId);
        return Result.success(favoriteService.getOtherFavoriteList(userId,pageQueryDTO));
    }
}
