package org.rock.base.db;

import org.junit.jupiter.api.Test;
import org.rock.base.pojo.base.BaseNode;
import org.rock.base.pojo.node.PersonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.neo4j.repository.Neo4jRepository;

@SpringBootTest
class Neo4jTests {

    @Autowired
    private Neo4jRepository neo4jRepository;

    @Test
    void createNode() {
        PersonNode personNode = new PersonNode();
        personNode.setName("张三");
        personNode.setSex("男");
        BaseNode.createBuild(personNode);
        neo4jRepository.save(personNode);
    }

}
