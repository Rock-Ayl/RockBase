package org.rock.main.db;

import org.junit.jupiter.api.Test;
import org.rock.main.serivce.TestElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ElasticSearchTests {


    @Autowired
    private TestElasticSearchService testElasticSearchService;

    @Test
    void test() {
        //搜索
        TestElasticSearchService.FileIndexSearchResult result = testElasticSearchService.fileSearch();
        System.out.println(result);
    }

}
