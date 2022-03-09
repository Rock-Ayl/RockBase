package org.rock.main;

import org.junit.jupiter.api.Test;
import org.rock.main.serivce.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MainApplicationTests {

    @Autowired
    FileService fileService;

    @Test
    void test() {
        FileService.FileIndexSearchResult result = fileService.search();
        System.out.println(123);
    }

}
