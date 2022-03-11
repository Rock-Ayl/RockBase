package org.rock.main.impl;

import org.rock.main.db.mongo.BaseMongoServiceImpl;
import org.rock.main.pojo.doc.TestDoc;
import org.rock.main.serivce.MongoTestService;
import org.springframework.stereotype.Service;

/**
 * 测试mongo实现层
 */
@Service
public class MongoTestServiceImpl extends BaseMongoServiceImpl<TestDoc> implements MongoTestService {

}
