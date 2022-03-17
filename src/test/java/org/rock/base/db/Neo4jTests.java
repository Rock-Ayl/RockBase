package org.rock.base.db;

import org.junit.jupiter.api.Test;
import org.rock.base.pojo.node.PersonNode;
import org.rock.base.serivce.TestNeo4jService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Neo4jTests {

    @Autowired
    private TestNeo4jService testNeo4jService;

    @Test
    void createNode() {
        PersonNode personNode = new PersonNode();
        personNode.setName("李四");
        personNode.setSex("男");
        testNeo4jService.create(personNode);
    }

}
