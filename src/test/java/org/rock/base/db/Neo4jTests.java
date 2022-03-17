package org.rock.base.db;

import org.junit.jupiter.api.Test;
import org.rock.base.db.neo4j.BaseNeo4jService;
import org.rock.base.pojo.base.BaseNode;
import org.rock.base.pojo.node.PersonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Neo4jTests {

    @Autowired
    private BaseNeo4jService baseNeo4jService;

    @Test
    void createNode() {
        PersonNode personNode = new PersonNode();
        personNode.setName("李四");
        personNode.setSex("男");
        BaseNode.createBuild(personNode);
        baseNeo4jService.save(personNode);
    }

}
