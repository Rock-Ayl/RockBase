package org.rock.base.impl;

import org.rock.base.db.neo4j.BaseNeo4jServiceImpl;
import org.rock.base.pojo.node.PersonNode;
import org.rock.base.serivce.TestNeo4jService;
import org.springframework.stereotype.Service;

/**
 * 测试neo4j实现层
 */
@Service
public class TestNeo4jServiceImpl extends BaseNeo4jServiceImpl<PersonNode> implements TestNeo4jService {

}
