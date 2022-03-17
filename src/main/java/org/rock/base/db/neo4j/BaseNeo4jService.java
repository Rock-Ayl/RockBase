package org.rock.base.db.neo4j;

import org.rock.base.pojo.base.BaseNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;

/**
 * neo4j 服务基底
 *
 * @Author ayl
 * @Date 2022-03-17
 */
public interface BaseNeo4jService<T extends BaseNode> extends Neo4jRepository<T, String> {

}
