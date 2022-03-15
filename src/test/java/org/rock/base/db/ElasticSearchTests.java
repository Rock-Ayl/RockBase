package org.rock.base.db;

import org.junit.jupiter.api.Test;
import org.rock.base.pojo.index.FileIndex;
import org.rock.base.serivce.TestElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ElasticSearchTests {


    @Autowired
    private TestElasticSearchService testElasticSearchService;

    @Test
    void test() {
        //初始化
        FileIndex fileIndex = new FileIndex();
        fileIndex.setExt("xlsx");
        fileIndex.setMd5("vasdhwqojoi");
        fileIndex.setName("测试文件2.xlsx");
        fileIndex.setSize(1900000L);
        //插入
        testElasticSearchService.create(fileIndex);
        System.out.println();
    }

}
