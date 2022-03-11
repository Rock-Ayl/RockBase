package org.rock.main.impl;

import org.rock.main.db.mongo.BaseMongoServiceImpl;
import org.rock.main.pojo.doc.TestDoc;
import org.rock.main.serivce.TestMongoService;
import org.springframework.stereotype.Service;

/**
 * 测试mongo实现层
 */
@Service
public class TestMongoServiceImpl extends BaseMongoServiceImpl<TestDoc> implements TestMongoService {

}
