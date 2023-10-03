package org.pancakeapple.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.pancakeapple.constant.MessageConstant;
import org.pancakeapple.dto.emoji.AddTagDTO;
import org.pancakeapple.exception.TagExistException;
import org.pancakeapple.result.Result;
import org.pancakeapple.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/tag")
@Tag(name = "表情包标签相关接口")
public class TagController {
    @Autowired
    private TagService tagService;

    /**
     * 获取表情包标签列表
     * @return 标签列表
     */
    @GetMapping
    @Operation(summary = "获取表情包标签列表")
    public Result<List<org.pancakeapple.entity.emoji.Tag>> list() {
        log.info("获取表情包标签列表");
        List<org.pancakeapple.entity.emoji.Tag> list = tagService.list();
        return Result.success(list);
    }

    /**
     * 新增表情包标签
     * @param addTagDTO 标签信息
     * @return 是否添加成功的信息
     */
    @PostMapping
    @Operation(summary = "增加一个标签")
    public Result<String> addTag(@RequestBody AddTagDTO addTagDTO) {
        log.info("新增表情包标签：{}",addTagDTO);
        try {
            tagService.addTag(addTagDTO);
            return Result.success(MessageConstant.ADD_SUCCESS);
        }catch (TagExistException e) {
            return Result.error(e.getMessage());
        }
    }

    /**
     * 根据id删除标签
     * @param id 标签id
     * @return 删除后的信息
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "根据id删除标签")
    public Result<String> deleteTag(@PathVariable Long id) {
        log.info("删除表情包标签，id为{}",id);
        tagService.deleteById(id);
        return Result.success(MessageConstant.DELETE_SUCCESS);
    }
}
