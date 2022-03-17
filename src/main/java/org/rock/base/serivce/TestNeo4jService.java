package org.rock.base.serivce;

import org.rock.base.pojo.node.PersonNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Service;

/**
 * neo4j 测试服务
 */
@Service
public interface TestNeo4jService extends Neo4jRepository<PersonNode, String> {
}
