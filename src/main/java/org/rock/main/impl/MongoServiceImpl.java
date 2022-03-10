package org.rock.main.impl;

import org.rock.main.mongo.BaseMongoServiceImpl;
import org.rock.main.pojo.doc.TestDoc;
import org.rock.main.serivce.MongoService;
import org.springframework.stereotype.Service;

/**
 * 测试mongo实现层
 */
@Service
public class MongoServiceImpl extends BaseMongoServiceImpl<TestDoc> implements MongoService {

}
