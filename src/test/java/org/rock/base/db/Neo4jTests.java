package org.rock.base.db;

import org.junit.jupiter.api.Test;
import org.rock.base.pojo.node.PersonNode;
import org.rock.base.serivce.TestNeo4jSqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Neo4jTests {

    @Autowired
    private TestNeo4jSqlService testNeo4jSqlService;

    @Test
    void createNode() {
        PersonNode personNode = new PersonNode();
        personNode.setName("张三");
        personNode.setSex("男");
        testNeo4jSqlService.create(personNode);
    }

}
