package org.rock.main;

import org.junit.jupiter.api.Test;
import org.rock.main.pojo.doc.TestDoc;
import org.rock.main.serivce.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MainApplicationTests {

    @Autowired
    private MongoService mongoService;

    @Test
    void test() {
        TestDoc testDoc = new TestDoc();
        testDoc.setNumber("444");
        mongoService.create(testDoc);
        System.out.println();
    }

}
