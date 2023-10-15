package org.pancakeapple.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.pancakeapple.constant.MessageConstant;
import org.pancakeapple.dto.emoji.AddTagGroupDTO;
import org.pancakeapple.result.Result;
import org.pancakeapple.service.TagGroupService;
import org.pancakeapple.vo.emoji.TagGroupVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/tagGroup")
@Tag(name = "标签分组相关接口")
public class TagGroupController {
    @Autowired
    private TagGroupService tagGroupService;
    /**
     * 添加标签分组
     * @param addTagGroupDTO 封装分组名称
     * @return 反馈信息
     */
    @PostMapping
    @Operation(summary = "添加一个标签分组")
    public Result<String> add(@RequestBody AddTagGroupDTO addTagGroupDTO) {
        log.info("添加标签分组，分组名称：{}",addTagGroupDTO.getName());
        tagGroupService.add(addTagGroupDTO);
        return Result.success(MessageConstant.ADD_SUCCESS);
    }

    /**
     * 查询标签分组列表
     * @return 标签分组列表
     */
    @GetMapping("/list")
    @Operation(summary = "查询标签分组列表")
    public Result<List<TagGroupVO>> list() {
        log.info("查询标签分组列表");
        List<TagGroupVO> list=tagGroupService.list();
        return Result.success(list);
    }

    /**
     * 根据id删除某个标签分组
     * 分组下所有标签也将一并删除
     * @param id 标签分组id
     * @return 反馈信息
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "根据id删除某个标签分组(同时会删除分组下所有标签)")
    public Result<String> deleteById(@PathVariable Long id) {
        log.info("根据id删除标签分组：{}",id);
        tagGroupService.deleteById(id);
        return Result.success(MessageConstant.DELETE_SUCCESS);
    }
}
