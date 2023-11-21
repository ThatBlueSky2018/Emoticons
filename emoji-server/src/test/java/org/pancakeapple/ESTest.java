package org.pancakeapple;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.TotalHits;
import co.elastic.clients.elasticsearch.core.search.TotalHitsRelation;
import org.junit.jupiter.api.Test;
import org.pancakeapple.entity.document.EmojiDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
public class ESTest {
    @Autowired
    private ElasticsearchClient client;

    @Test
    void testClient() {
        System.out.println(client);
    }

    @Test
    void testGetById() throws IOException {
        GetResponse<EmojiDoc> response = client.get(g -> g
                        .index("emoji")
                        .id("1"),
                EmojiDoc.class
        );

        if (response.found()) {
            System.out.println(response.source()); //id为null
        } else {
            System.out.println("Document not found");
        }
    }

    @Test
    void testSearch() throws IOException {
        String searchText = "嘲讽";

        SearchResponse<EmojiDoc> response = client.search(s -> s
                        .index("emoji")
                        .query(q -> q
                                .match(t -> t
                                        .field("all")
                                        .query(searchText)
                                )
                        )
                        .sort(f -> f.field(o -> o.field("hits")
                                .order(SortOrder.Desc))),
                EmojiDoc.class
        );

        TotalHits total = response.hits().total();
        boolean isExactResult = false;
        if (total != null) {
            isExactResult = total.relation() == TotalHitsRelation.Eq;
        }

        if (isExactResult) {
            System.out.println("There are " + total.value() + " results");
        } else {
            if (total != null) {
                System.out.println("There are more than " + total.value() + " results");
            }
        }

        List<Hit<EmojiDoc>> hits = response.hits().hits();
        for (Hit<EmojiDoc> hit : hits) {
            EmojiDoc product = hit.source();
            if (product != null) {
                product.setId(Long.parseLong(hit.id()));
            }
            System.out.println(product);
        }
    }
}
