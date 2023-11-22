package org.pancakeapple.service;

import org.pancakeapple.dto.search.SearchParams;
import org.pancakeapple.result.PageBean;

import java.io.IOException;

public interface SearchService {
    /**
     * 搜索表情包
     * @param searchParams 搜索参数
     * @return 分页查询结果
     */
    PageBean search(SearchParams searchParams) throws IOException;
}
