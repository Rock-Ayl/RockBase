package org.rock.base.db;

import org.junit.jupiter.api.Test;
import org.rock.base.pojo.index.FileIndex;
import org.rock.base.serivce.TestElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

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
        //创建
        testElasticSearchService.create(fileIndex);
        System.out.println();

        //单个查询
        FileIndex one = testElasticSearchService.get(FileIndex.class, "fcca086604d64ea8a6365abc1ebaeb30");
        //批量查询
        List<FileIndex> list = testElasticSearchService.list(FileIndex.class, Arrays.asList("fcca086604d64ea8a6365abc1ebaeb30", "ad48a5967f574b94b4e38b06a60b908e", "1f59707c22d84fe2acffdab8b6870279"));
        System.out.println(12321312);

        //删除
        testElasticSearchService.delete("1f59707c22d84fe2acffdab8b6870279", FileIndex.class);
        System.out.println(123);

    }

}
