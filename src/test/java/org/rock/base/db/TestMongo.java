package org.rock.base.db;

import org.junit.jupiter.api.Test;
import org.rock.base.common.mongo.query.LambdaCriteria;
import org.rock.base.db.mongo.BaseMongoService;
import org.rock.base.pojo.doc.TestDoc;
import org.rock.base.serivce.test.TestMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class TestMongo {

    @Autowired
    private TestMongoService testMongoService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void allTest() {

        //创建
        TestDoc createDoc = new TestDoc();
        createDoc.setNumber("编号123");
        testMongoService.create(createDoc);

        //查询单个
        TestDoc oldDoc = testMongoService.getById(createDoc.getId());

        //更新单个
        TestDoc update = new TestDoc();
        update.setId(oldDoc.getId());
        update.setValue("测试123");
        testMongoService.updateSkipNullById(update);

        //批量更新
        update.setValue("测试12341231231231231");
        testMongoService.batchUpdateSkipNullById(Arrays.asList(update));

    }

    @Test
    void create() {

        //初始化一个实体
        TestDoc create = new TestDoc();
        create.setNumber("编号");

        //创建
        testMongoService.create(create);
        System.out.println();
    }

    @Test
    void rollPageTypeTwo() {

        //初始化参数对象
        BaseMongoService.MongoRollPageParam param = new BaseMongoService.MongoRollPageParam();

        //限制返回参数
        param.setFields("id,number");

        //限制关键词 模糊搜索number
        param.setSearchType("dim");
        param.setKeywordType("number");
        param.setKeywordList(Arrays.asList("编"));

        //是否需要返回count
        param.setNeedCount(true);

        //分页查询
        BaseMongoService.RollPageResult<TestDoc> find = testMongoService.rollPage(param);

        System.out.println();

    }

    @Test
    void listAll() {
        List<TestDoc> testDocs = testMongoService.listAll("id,number");
        System.out.println();

        List<String> idList = testMongoService.listAllId();
        System.out.println();
    }

    @Test
    void lambdaQuery() {
        Criteria criteria = LambdaCriteria
                .where(TestDoc::getNumber).is("编号123")
                .and(TestDoc::getValue).in(Arrays.asList("测试123", "测试12341231231231231"))
                .getCriteria();
        Query query = new Query(criteria);
        List<TestDoc> testDocs = mongoTemplate.find(query, TestDoc.class);
        System.out.println();
    }

}
