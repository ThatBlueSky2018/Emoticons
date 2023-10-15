package org.pancakeapple.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.pancakeapple.constant.MessageConstant;
import org.pancakeapple.dto.emoji.AddTypeDTO;
import org.pancakeapple.result.Result;
import org.pancakeapple.service.TypeService;
import org.pancakeapple.vo.emoji.TypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/type")
@Tag(name = "表情包类型相关接口")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @GetMapping
    @Operation(summary = "查询分类列表")
    public Result<List<TypeVO>> list() {
        log.info("获取表情包分类列表...");
        List<TypeVO> list = typeService.list();
        return Result.success(list);
    }

    @PostMapping
    @Operation(summary = "新增一个类型")
    public Result<String> add(@RequestBody AddTypeDTO addTypeDTO) {
        log.info("新增表情包类型:{}",addTypeDTO.getName());
        typeService.add(addTypeDTO);
        return Result.success(MessageConstant.ADD_SUCCESS);
    }
}
