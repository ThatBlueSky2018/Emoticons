package org.pancakeapple.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.pancakeapple.result.Result;
import org.pancakeapple.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/message")
@Tag(name = "消息相关接口")
public class MessageController {
    @Autowired
    private MessageService messageService;

    /**
     * 查询未读消息数量
     * @return 数量
     */
    @GetMapping("/unread")
    @Operation(summary = "查询未读消息数量")
    public Result<Integer> unreadCount() {
        log.info("查询未读消息数量");
        Integer count = messageService.unreadCount();
        return Result.success(count);
    }
}
