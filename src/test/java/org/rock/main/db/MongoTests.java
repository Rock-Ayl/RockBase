package org.rock.main.db;

import org.junit.jupiter.api.Test;
import org.rock.main.db.mongo.BaseMongoService;
import org.rock.main.pojo.doc.TestDoc;
import org.rock.main.serivce.MongoTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@SpringBootTest
class MongoTests {

    @Autowired
    private MongoTestService mongoService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void test() {
        //创建
        TestDoc create = new TestDoc();
        create.setNumber("编号");
        mongoService.create(create);
        //查询
        TestDoc old = mongoService.get(TestDoc.class, create.getId());
        TestDoc update = new TestDoc();
        update.setId(old.getId());
        update.setValue("测试123");
        //更新
        mongoService.updateSkipNull(update, old.getVer());
        //查询
        old = mongoService.get(TestDoc.class, create.getId());
        Update update2 = new Update();
        update2.set("123", 45);
        //覆盖更新
        mongoService.update(TestDoc.class, old.getId(), update2, old.getVer());
        //查询
        old = mongoService.get(TestDoc.class, create.getId());
        System.out.println(123);
        //分页查询
        BaseMongoService.RollPageResult<TestDoc> find = mongoService.rollPage(TestDoc.class, new Criteria(), null, null);
        System.out.println(123);
        //初始化批量更新
        BulkOperations bulkOperations = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, TestDoc.class);
        bulkOperations.updateMulti(new Query(Criteria.where("_id").is(old.getId())), new Update().set("444", 5));
        bulkOperations.updateMulti(new Query(Criteria.where("_id").is(old.getId())), new Update().set("555", 6));
        //执行批量更新
        bulkOperations.execute();
        System.out.println(123);
    }

}
