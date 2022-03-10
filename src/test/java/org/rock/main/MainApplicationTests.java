package org.rock.main;

import org.junit.jupiter.api.Test;
import org.rock.main.pojo.doc.TestDoc;
import org.rock.main.serivce.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootTest
class MainApplicationTests {

    @Autowired
    private FileService fileService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void test() {
        TestDoc testDoc = new TestDoc();
        testDoc.setNumber("123");
        mongoTemplate.insert(testDoc);
        System.out.println();
    }

}
