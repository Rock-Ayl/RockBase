package org.rock.main.db;

import org.junit.jupiter.api.Test;
import org.rock.main.serivce.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ElasticSearchTests {


    @Autowired
    private FileService fileService;

    @Test
    void test() {
        //搜索
        FileService.FileIndexSearchResult result = fileService.search();
        System.out.println(result);
    }

}
