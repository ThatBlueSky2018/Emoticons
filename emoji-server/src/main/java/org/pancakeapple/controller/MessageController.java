package org.pancakeapple.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.pancakeapple.dto.emoji.PageQueryDTO;
import org.pancakeapple.result.PageBean;
import org.pancakeapple.result.Result;
import org.pancakeapple.service.MessageService;
import org.pancakeapple.vo.interaction.MessageDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 查询消息列表
     * @param pageQueryDTO 分页查询参数
     * @return 分页查询结果
     */
    @GetMapping
    @Operation(summary = "查询消息列表(包括已读消息和未读消息，分页查询)")
    public Result<PageBean> getMessageList(PageQueryDTO pageQueryDTO) {
        log.info("查询消息列表");
        PageBean pageBean=messageService.getMessageList(pageQueryDTO);
        return Result.success(pageBean);
    }

    /**
     * 根据id查询某条消息的详细信息
     * @param id 消息id
     * @return 消息详细信息
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据id查询某条消息的详细信息")
    public Result<MessageDetailVO> getById(@PathVariable Long id) {
        log.info("查询消息详细信息，id为{}",id);
        MessageDetailVO messageDetailVO=messageService.getById(id);
        return Result.success(messageDetailVO);
    }
}
