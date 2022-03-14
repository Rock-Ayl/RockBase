package org.rock.base.impl;

import org.rock.base.db.mongo.BaseMongoServiceImpl;
import org.rock.base.pojo.doc.TestDoc;
import org.rock.base.serivce.TestMongoService;
import org.springframework.stereotype.Service;

/**
 * 测试mongo实现层
 */
@Service
public class TestMongoServiceImpl extends BaseMongoServiceImpl<TestDoc> implements TestMongoService {

}
