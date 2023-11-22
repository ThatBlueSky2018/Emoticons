package org.pancakeapple.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.pancakeapple.constant.PromptConstant;
import org.pancakeapple.dto.search.SearchParams;
import org.pancakeapple.result.PageBean;
import org.pancakeapple.result.Result;
import org.pancakeapple.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@Slf4j
@RequestMapping("/search")
@CrossOrigin
@Tag(name = "表情包搜索相关接口")
public class SearchController {
    @Autowired
    private SearchService searchService;

    /**
     * 搜索表情包
     * @param searchParams 搜索参数
     * @return 分页查询结果
     */
    @PostMapping
    @Operation(summary = "按照关键词搜索")
    public Result<PageBean> search(@RequestBody SearchParams searchParams) throws IOException {
        log.info("搜索表情包：{}",searchParams);
        PageBean pageBean = searchService.search(searchParams);
        if(pageBean!=null) {
            return Result.success(pageBean);
        }
        return Result.error(PromptConstant.NO_SEARCH_RESULT);
    }
}
