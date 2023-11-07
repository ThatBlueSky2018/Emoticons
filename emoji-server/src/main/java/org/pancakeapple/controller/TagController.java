package org.pancakeapple.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.pancakeapple.constant.PromptConstant;
import org.pancakeapple.dto.emoji.AddTagDTO;
import org.pancakeapple.exception.TagExistException;
import org.pancakeapple.result.Result;
import org.pancakeapple.service.TagService;
import org.pancakeapple.vo.emoji.TagGeneralVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
     * 根据分组id查询标签列表
     * @param groupId 分组id
     * @return 满足条件的标签列表
     */
    @GetMapping("/groupList")
    @Operation(summary = "根据分组id查询标签列表")
    @Cacheable(cacheNames = "tagGroup",key = "#groupId")
    public Result<List<TagGeneralVO>> listByGroupId(Long groupId) {
        log.info("根据分组id查询标签：{}",groupId);
        List<TagGeneralVO> list=tagService.listByGroupId(groupId);
        return Result.success(list);
    }
    /**
     * 新增表情包标签
     * @param addTagDTO 标签信息
     * @return 是否添加成功的信息
     */
    @PostMapping
    @Operation(summary = "增加一个标签")
    @CacheEvict(cacheNames = "tagGroup",key = "#addTagDTO.getGroupId()")
    public Result<String> addTag(@RequestBody AddTagDTO addTagDTO) {
        log.info("新增表情包标签：{}",addTagDTO);
        try {
            tagService.addTag(addTagDTO);
            return Result.success(PromptConstant.ADD_SUCCESS);
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
    @CacheEvict(cacheNames = "tagGroup", allEntries = true)
    public Result<String> deleteTag(@PathVariable Long id) {
        log.info("删除表情包标签，id为{}",id);
        tagService.deleteById(id);
        return Result.success(PromptConstant.DELETE_SUCCESS);
    }
}
