package org.rock.base.db;

import org.junit.jupiter.api.Test;
import org.rock.base.pojo.base.BaseNode;
import org.rock.base.pojo.node.PersonNode;
import org.rock.base.serivce.test.TestNeo4jService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class TestNeo4j {

    @Autowired
    private TestNeo4jService testNeo4jService;

    @Test
    void createNode() {
        PersonNode personNode = new PersonNode();
        personNode.setName("张三");
        personNode.setSex("男");
        BaseNode.createBuild(personNode);
        testNeo4jService.save(personNode);
    }

    @Test
    void createNodeList() {
        PersonNode one = new PersonNode();
        one.setName("王一");
        one.setSex("男");
        BaseNode.createBuild(one);

        PersonNode two = new PersonNode();
        two.setName("王二");
        two.setSex("男");
        BaseNode.createBuild(two);

        List<PersonNode> list = new ArrayList<>();
        list.add(one);
        list.add(two);

        testNeo4jService.saveAll(list);
    }

    @Test
    void listAll() {
        List<PersonNode> list = testNeo4jService.findAll();
        System.out.println();
    }

}
