package org.rock.base.db;

import org.junit.jupiter.api.Test;
import org.rock.base.db.mongo.BaseMongoService;
import org.rock.base.pojo.doc.TestDoc;
import org.rock.base.serivce.test.TestMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.ArrayList;

@SpringBootTest
class MongoTests {

    @Autowired
    private TestMongoService testMongoService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void allTest() {

        //创建
        TestDoc create = new TestDoc();
        create.setNumber("编号123");
        testMongoService.create(create);

        //查询
        TestDoc old = testMongoService.get(TestDoc.class, create.getId());
        TestDoc update = new TestDoc();
        update.setId(old.getId());
        update.setValue("测试123");

        //更新
        testMongoService.updateSkipNull(update);

        //查询
        old = testMongoService.get(TestDoc.class, create.getId());
        Update update2 = new Update();
        update2.set("123", 45);

        //查询
        old = testMongoService.get(TestDoc.class, create.getId());
        System.out.println(123);

        //分页查询
        BaseMongoService.RollPageResult<TestDoc> find = testMongoService.rollPage(TestDoc.class, new ArrayList<>(), null, null);
        System.out.println(123);

        //初始化批量更新
        BulkOperations bulkOperations = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, TestDoc.class);
        bulkOperations.updateMulti(new Query(Criteria.where("_id").is(old.getId())), new Update().set("444", 5));
        bulkOperations.updateMulti(new Query(Criteria.where("_id").is(old.getId())), new Update().set("555", 6));

        //执行批量更新
        bulkOperations.execute();
        System.out.println(123);
    }

    @Test
    void create() {
        //创建
        TestDoc create = new TestDoc();
        create.setNumber("编号");
        testMongoService.create(create);
        System.out.println();
    }

    @Test
    void rollPage() {
        //分页查询
        BaseMongoService.RollPageResult<TestDoc> find = testMongoService.rollPage(TestDoc.class, new ArrayList<>(), null, null);
        System.out.println(123);
    }

    @Test
    void rollPageTemplate() {

        //初始化参数
        BaseMongoService.MongoRollPageParam param = new BaseMongoService.MongoRollPageParam();
        //限制参数
        param.setFields("id,number");
        //限制关键词 模糊搜索number
        param.setSearchType("dim");
        param.setKeywordType("number");
        param.setKeywords("编");
        //需要总count
        param.setNeedCount(true);

        //分页查询
        BaseMongoService.RollPageResult<TestDoc> find = testMongoService.rollPage(TestDoc.class, param);

        System.out.println();

    }

}
