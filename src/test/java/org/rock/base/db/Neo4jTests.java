package org.rock.base.db;

import org.junit.jupiter.api.Test;
import org.rock.base.db.neo4j.BasNeo4TemplateMapper;
import org.rock.base.pojo.base.BaseNode;
import org.rock.base.pojo.node.PersonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Neo4jTests {

    @Autowired
    private BasNeo4TemplateMapper basNeo4TemplateMapper;

    @Test
    void createNode() {
        PersonNode personNode = new PersonNode();
        personNode.setName("王二麻子");
        personNode.setSex("男");
        BaseNode.createBuild(personNode);
        basNeo4TemplateMapper.save(personNode);
    }

}
