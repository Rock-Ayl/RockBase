package org.rock.base.db;

import org.junit.jupiter.api.Test;
import org.rock.base.pojo.node.PersonNode;
import org.rock.base.serivce.TestNeo4jService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class Neo4jTests {

    @Autowired
    private TestNeo4jService testNeo4jService;

    @Test
    void createNode() {
        PersonNode personNode = new PersonNode();
        personNode.setName("赵六");
        personNode.setSex("男");
        testNeo4jService.createNode(personNode);
    }

    @Test
    void createNodeList() {
        PersonNode one = new PersonNode();
        one.setName("王一");
        one.setSex("男");

        PersonNode two = new PersonNode();
        two.setName("王二");
        two.setSex("男");

        List<PersonNode> list = new ArrayList<>();
        list.add(one);
        list.add(two);

        testNeo4jService.createNode(list);
    }

}
