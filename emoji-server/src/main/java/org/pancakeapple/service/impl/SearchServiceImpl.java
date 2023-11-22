package org.pancakeapple.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.RangeQuery;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.json.JsonData;
import org.pancakeapple.constant.DataConstant;
import org.pancakeapple.dto.search.SearchParams;
import org.pancakeapple.entity.document.EmojiDoc;
import org.pancakeapple.result.PageBean;
import org.pancakeapple.service.SearchService;
import org.pancakeapple.vo.emoji.EmojiGeneralVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private ElasticsearchClient client;

    @Autowired
    private Map<Integer,String> sortMappings;

    /**
     * 搜索表情包
     * @param searchParams 搜索参数
     * @return 分页查询结果
     */
    @Override
    public PageBean search(SearchParams searchParams) throws IOException {
        // 1.参数断言
        assert searchParams.getKind() != null;
        assert searchParams.getKey() != null;
        assert searchParams.getSort() != null;

        // 2.获取搜索的类型
        Integer kind = searchParams.getKind();

        // 3.根据不同的类型搜索
        SearchResponse<EmojiDoc> response;
        if (kind == 1) {
            // 搜索类型为热门
            response = searchPopular(searchParams);
        } else if (kind == 2) {
            //搜索类型为动态
            response = searchDynamic(searchParams);
        } else if(kind == 3) {
            //搜索类型为静态
            response = searchStatic(searchParams);
        } else {
            //搜索类型为最新
            response = searchLatest(searchParams);
        }

        // 4.封装查询结果
        if (response != null) {
            long total = 0;
            List<EmojiGeneralVO> searchResult = new ArrayList<>();
            if (response.hits().total() != null) {
                total = response.hits().total().value();
            }
            List<Hit<EmojiDoc>> hitList = response.hits().hits();
            hitList.forEach(hit->{
                EmojiDoc emojiDoc = hit.source();
                EmojiGeneralVO emojiGeneralVO=new EmojiGeneralVO();
                if (emojiDoc != null) {
                    BeanUtils.copyProperties(emojiDoc,emojiGeneralVO);
                }
                searchResult.add(emojiGeneralVO);
            });
            return new PageBean(total,searchResult);
        }
        return null;
    }

    //指定排序方式的搜索
    private SearchResponse<EmojiDoc> sortSearch(SearchParams searchParams, Query all,Query kind) throws IOException {
        return client.search(s -> s
                        .index(DataConstant.ES_INDEX_NAME)
                        .query(q->q
                                .bool(b->b
                                        .must(all)
                                        .must(kind)))
                        .from((searchParams.getPage()-1)* searchParams.getPageSize())
                        .size(searchParams.getPageSize())
                        .sort(f->f
                                .field(o->o
                                        .field(sortMappings.get(searchParams.getSort()))
                                        .order(SortOrder.Desc))),
                EmojiDoc.class
        );
    }

    //默认排序方式的搜索
    private SearchResponse<EmojiDoc> scoreSearch(SearchParams searchParams,Query all,Query kind) throws IOException {
        return client.search(s -> s
                        .index(DataConstant.ES_INDEX_NAME)
                        .query(q->q
                                .bool(b->b
                                        .must(all)
                                        .must(kind)))
                        .from((searchParams.getPage()-1)* searchParams.getPageSize())
                        .size(searchParams.getPageSize()),
                EmojiDoc.class
        );
    }

    private SearchResponse<EmojiDoc> searchPopular(SearchParams searchParams) throws IOException {
        Query all = MatchQuery
                .of(m -> m
                .field("all")
                .query(searchParams.getKey())
                )._toQuery();

        Query hotIndex = RangeQuery.of(m -> m
                        .field("hotIndex")
                        .gte(JsonData.of(0.3))
                )._toQuery();

        if(searchParams.getSort() != 0) {
            return sortSearch(searchParams,all,hotIndex);
        }
        else {
            return scoreSearch(searchParams,all,hotIndex);
        }
    }

    private SearchResponse<EmojiDoc> searchDynamic(SearchParams searchParams) throws IOException {
        Query all = MatchQuery
                .of(m -> m
                        .field("all")
                        .query(searchParams.getKey())
                )._toQuery();

        Query dynamic = MatchQuery
                .of(m->m
                        .field("tags")
                        .query(DataConstant.DYNAMIC_TAG_NAME)
                )._toQuery();
        if(searchParams.getSort() != 0) {
            return sortSearch(searchParams,all,dynamic);
        }
        else {
            return scoreSearch(searchParams,all,dynamic);
        }
    }

    private SearchResponse<EmojiDoc> searchStatic(SearchParams searchParams) throws IOException {
        Query all = MatchQuery
                .of(m -> m
                        .field("all")
                        .query(searchParams.getKey())
                )._toQuery();

        Query dynamic = MatchQuery
                .of(m->m
                        .field("tags")
                        .query(DataConstant.STATIC_TAG_NAME)
                )._toQuery();
        if(searchParams.getSort() != 0) {
            return sortSearch(searchParams,all,dynamic);
        }
        else {
            return scoreSearch(searchParams,all,dynamic);
        }
    }

    private SearchResponse<EmojiDoc> searchLatest(SearchParams searchParams) throws IOException {
        Query all = MatchQuery
                .of(m -> m
                        .field("all")
                        .query(searchParams.getKey())
                )._toQuery();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime sevenDaysAgo = now.minus(Duration.ofDays(7));

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatSevenDaysAgo = df.format(sevenDaysAgo);

        Query dynamic = RangeQuery
                .of(m->m
                        .field("createTime")
                        .gte(JsonData.of(formatSevenDaysAgo))
                )._toQuery();
        if(searchParams.getSort() != 0) {
            return sortSearch(searchParams,all,dynamic);
        }
        else {
            return scoreSearch(searchParams,all,dynamic);
        }
    }

}
