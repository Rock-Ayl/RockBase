package com.rock.base.impl.test;

import com.rock.base.db.mongo.BaseMongoServiceImpl;
import com.rock.base.pojo.doc.TestDoc;
import com.rock.base.serivce.test.TestMongoService;
import org.springframework.stereotype.Service;

/**
 * 测试mongo实现层
 */
@Service
public class TestMongoServiceImpl extends BaseMongoServiceImpl<TestDoc> implements TestMongoService {

}
