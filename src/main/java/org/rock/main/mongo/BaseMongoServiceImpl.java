package org.rock.main.mongo;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.rock.main.pojo.base.BaseDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.*;

@Service
public class BaseMongoServiceImpl<T> implements BaseMongoService<T> {

    private static final Logger LOG = LoggerFactory.getLogger(BaseMongoServiceImpl.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    public T create(T document) {
        //创建前给document初始化
        initDocument(document);
        //插入
        return this.mongoTemplate.insert(document);
    }

    public Collection<T> create(List<T> documents) {
        //循环
        for (T document : documents) {
            //创建前初始化
            initDocument(document);
        }
        //批量插入
        return this.mongoTemplate.insertAll(documents);
    }

    /**
     * 创建前给document初始化
     *
     * @param document
     */
    private void initDocument(T document) {
        BaseDocument doc = (BaseDocument) document;
        doc.setVer(System.currentTimeMillis());
        doc.setCreateDate(new Date());
        doc.setUpdateDate(new Date());
    }

    public T get(Class<T> clazz, String id) {
        return this.mongoTemplate.findById(id, clazz);
    }

    public boolean delete(String id, Class<T> clazz) {
        //根据id删除
        return this.mongoTemplate.remove(new Query(Criteria
                .where("_id").is(id)
        ), clazz).getDeletedCount() == 1L;
    }

    public boolean updateSkipNull(T document, long ver) {
        //判空
        if (document == null) {
            //过
            return false;
        }
        //强转基类
        BaseDocument doc = (BaseDocument) document;
        //id
        String id = doc.getId();
        //判空
        if (StringUtils.isBlank(id)) {
            //过
            return false;
        }
        //限制条件
        Query query = new Query(Criteria
                .where("_id").is(id)
                .and("ver").is(ver));
        //更新
        Update update = new Update();
        Method[] var7 = doc.getClass().getMethods();
        int var8 = var7.length;
        try {
            for (int var9 = 0; var9 < var8; ++var9) {
                Method method = var7[var9];
                String methodName = method.getName();
                //第一层过滤
                if (methodName.equals("getClass") == false && (methodName.startsWith("get") || methodName.startsWith("is"))) {
                    //初始化key
                    String key = new String();
                    //切割
                    if (methodName.startsWith("get")) {
                        key = methodName.substring(3);
                    } else if (methodName.startsWith("is")) {
                        key = methodName.substring(2);
                    }
                    key.substring(0, 1).toLowerCase();
                    key = key.substring(1);
                    //第二层过滤,过滤掉基类字段
                    switch (key) {
                        case "id":
                        case "createDate":
                        case "updateDate":
                        case "createUserId":
                        case "updateUserId":
                        case "del":
                        case "ver":
                            //本次过
                            continue;
                            //其他
                        default:
                            //value
                            Object value = method.invoke(doc);
                            //判空
                            if (value != null) {
                                //设置
                                update.set(key, value);
                            }
                            break;
                    }
                }
            }
        } catch (Exception e) {
            //日志
            LOG.error("Update Skip Null fail:[{}]", e);
            //过
            return false;
        }
        //固定更新最后更新时间、版本
        update.set("updateDate", new Date());
        update.set("ver", System.currentTimeMillis());
        //日志
        LOG.info("Update Skip Null Query Condition:{}", query.getQueryObject().toJson());
        //只更新一个
        return this.mongoTemplate.updateFirst(query, update, doc.getClass()).getModifiedCount() > 0L;
    }

    public List<T> list(Class<T> clazz, List<String> idList) {
        //查询
        Query query = new Query(Criteria.where("_id").in(idList));
        //日志
        LOG.info("QueryCondition:{}", query.getQueryObject().toJson());
        //查询
        return this.mongoTemplate.find(query, clazz);
    }

    public long update(Class<T> clazz, String id, Update update, long ver) {
        //限制条件
        Query query = new Query(Criteria
                .where("_id").is(id)
                .and("ver").is(ver)
        );
        //固定更新最后更新时间、版本
        update.set("updateDate", new Date());
        update.set("ver", System.currentTimeMillis());
        //更新一个
        return this.mongoTemplate.updateFirst(query, update, clazz).getModifiedCount();
    }

    public RollPageResult<T> rollPage(Class<T> clazz, Criteria criteria, Document fields, long pageNum, long pageSize, Sort sort, boolean needCount) {
        //初始化响应对象
        RollPageResult<T> result = new RollPageResult();
        Criteria companyCriteria = new Criteria();
        companyCriteria.andOperator(new Criteria[]{criteria});
        Document idFiled = new Document();
        idFiled.put("_id", 1);
        BasicQuery idQuery = new BasicQuery(new Document(), idFiled);
        idQuery.addCriteria(companyCriteria);
        if (sort != null) {
            idQuery.with(sort);
        }

        idQuery.limit((int) pageSize).skip((pageNum - 1L) * pageSize);
        LOG.info("QueryCondition:{}", idQuery.getQueryObject().toJson());
        List<T> list = this.mongoTemplate.find(idQuery, clazz);
        if (CollectionUtils.isNotEmpty(list)) {
            Criteria dataCriteria = new Criteria();
            List<String> idList = new ArrayList();
            Iterator var18 = list.iterator();

            while (var18.hasNext()) {
                Object t = var18.next();
                idList.add(((BaseDocument) t).getId());
            }

            dataCriteria.andOperator(new Criteria[]{Criteria.where("_id").in(idList), criteria});
            BasicQuery dataQuery = new BasicQuery(new Document(), fields);
            dataQuery.addCriteria(dataCriteria);
            if (sort != null) {
                dataQuery.with(sort);
            }

            list = this.mongoTemplate.find(dataQuery, clazz);
        }
        //组装数据
        result.setList(list);
        //如果需要count
        if (needCount) {
            //查询count
            long total = this.mongoTemplate.count(new Query(companyCriteria), clazz);
            //组装
            result.setTotal(total);
        } else {
            //默认
            result.setTotal(-1L);
        }
        //返回
        return result;
    }

}

