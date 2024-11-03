package com.rock.base.serivce.test;

import com.rock.base.pojo.node.PersonNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;

/**
 * neo4j 测试服务
 */
public interface TestNeo4jService extends Neo4jRepository<PersonNode, String> {

}
