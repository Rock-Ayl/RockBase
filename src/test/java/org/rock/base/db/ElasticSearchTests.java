package org.rock.base.db;

import org.junit.jupiter.api.Test;
import org.rock.base.pojo.index.FileIndex;
import org.rock.base.serivce.TestElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class ElasticSearchTests {

    @Autowired
    private TestElasticSearchService testElasticSearchService;

    @Test
    void search() {

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
    }

    @Test
    void delete() {
        //删除
        testElasticSearchService.delete(FileIndex.class, "1f59707c22d84fe2acffdab8b6870279");
        System.out.println(123);
    }

    @Test
    void update() {
        //初始化
        FileIndex fileIndex = new FileIndex();
        fileIndex.setId("ac7eb80e513b4e4c9ad196c9e687862c");
        fileIndex.setExt("");
        fileIndex.setMd5("cvdsqwd");
        fileIndex.setName("测试文件2-comjoisa1xvn.word");
        fileIndex.setSize(41239999L);
        //更新
        testElasticSearchService.updateSkipNull(fileIndex);
    }

    @Test
    void batchUpdate() {
        //初始化
        FileIndex fileIndex = new FileIndex();
        fileIndex.setId("ac7eb80e513b4e4c9ad196c9e687862c");
        fileIndex.setExt("");
        fileIndex.setMd5("cvdsqwd");
        fileIndex.setName("批量更新1-comjoisa1xvn.word");
        fileIndex.setSize(41239999L);

        //初始化
        FileIndex fileIndex2 = new FileIndex();
        fileIndex2.setId("b138d267485f475f8d0c2290198cf5c9");
        fileIndex2.setExt("vasj1");
        fileIndex2.setMd5("cvdsqwd");
        fileIndex2.setName("批量更新2-comjoisa1xvn.word");
        fileIndex2.setSize(4992319L);

        List<FileIndex> list = new ArrayList<>();
        list.add(fileIndex);
        list.add(fileIndex2);

        //更新
        testElasticSearchService.batchUpdateSkipNull(list);
    }

}
