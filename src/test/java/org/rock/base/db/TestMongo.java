package org.rock.base.db;

import org.junit.jupiter.api.Test;
import org.rock.base.db.mongo.BaseMongoService;
import org.rock.base.pojo.doc.TestDoc;
import org.rock.base.serivce.test.TestMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest
class TestMongo {

    @Autowired
    private TestMongoService testMongoService;

    @Test
    void allTest() {

        //创建
        TestDoc createDoc = new TestDoc();
        createDoc.setNumber("编号123");
        testMongoService.create(createDoc);

        //查询单个
        TestDoc oldDoc = testMongoService.getById(TestDoc.class, createDoc.getId());

        //更新单个
        TestDoc update = new TestDoc();
        update.setId(oldDoc.getId());
        update.setValue("测试123");
        testMongoService.updateSkipNullById(update);

        //批量更新
        update.setValue("测试12341231231231231");
        testMongoService.batchUpdateSkipNullById(TestDoc.class, Arrays.asList(update));

        //分页查询
        BaseMongoService.RollPageResult<TestDoc> find = testMongoService.rollPage(TestDoc.class, new ArrayList<>(), null, null);
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
