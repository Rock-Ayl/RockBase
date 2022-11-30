package org.rock.base.db.mongo;

import com.google.common.base.CaseFormat;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.rock.base.pojo.base.BaseDocument;
import org.rock.base.util.MongoExtraUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * mongo 服务基底实现
 *
 * @Author ayl
 * @Date 2022-03-09
 */
@Service
public class BaseMongoServiceImpl<T extends BaseDocument> implements BaseMongoService<T> {

    private static final Logger LOG = LoggerFactory.getLogger(BaseMongoServiceImpl.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    public T create(T document) {
        //创建前初始化
        BaseDocument.createBuild(document);
        //插入
        return this.mongoTemplate.insert(document);
    }

    public Collection<T> create(List<T> documents) {
        //循环
        for (T document : documents) {
            //创建前初始化
            BaseDocument.createBuild(document);
        }
        //批量插入
        return this.mongoTemplate.insertAll(documents);
    }

    public T get(Class<T> clazz, String id) {
        return this.mongoTemplate.findById(id, clazz);
    }

    public boolean delete(Class<T> clazz, String id) {
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
        //id
        String id = document.getId();
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
        try {
            //method arr
            Method[] methodArr = document.getClass().getMethods();
            //循环
            for (int var9 = 0; var9 < methodArr.length; ++var9) {
                //当前method
                Method method = methodArr[var9];
                //名字
                String methodName = method.getName();
                //如果是getClass
                if (methodName.equals("getClass") == true) {
                    //本轮过
                    continue;
                }
                //如果不是get is 方法
                if (methodName.startsWith("get") == false && methodName.startsWith("is") == false) {
                    //本轮过
                    continue;
                }
                //初始化key
                String key = new String();
                //切割key
                if (methodName.startsWith("get")) {
                    key = methodName.substring(3);
                } else if (methodName.startsWith("is")) {
                    key = methodName.substring(2);
                }
                //大驼峰转小驼峰
                key = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, key);
                //判断是否为基础字段
                switch (key) {
                    //基础字段不参与
                    case "id":
                    case "createDate":
                    case "updateDate":
                    case "del":
                    case "ver":
                        //本次过
                        continue;
                        //其他
                    default:
                        //value
                        Object value = method.invoke(document);
                        //如果存在
                        if (value != null) {
                            //设置
                            update.set(key, value);
                        }
                        break;
                }
            }
        } catch (Exception e) {
            //日志
            LOG.error("Mongo Update Skip Null fail:[{}]", e);
            //过
            return false;
        }
        //固定更新最后更新时间、版本
        update.set("updateDate", new Date());
        update.set("ver", System.currentTimeMillis());
        //日志
        LOG.info("Mongo Update Skip Null Query :{}", query.toString());
        //只更新一个
        return this.mongoTemplate.updateFirst(query, update, document.getClass()).getModifiedCount() > 0L;
    }

    public List<T> list(Class<T> clazz, List<String> idList) {
        //查询
        Query query = new Query(Criteria.where("_id").in(idList));
        //日志
        LOG.info("Mongo list query:[{}]", query.toString());
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

    @Override
    public RollPageResult<T> rollPage(Class<T> clazz, List<Criteria> criteriaList, Integer pageNum, Integer pageSize) {
        return rollPage(clazz, criteriaList, null, pageNum, pageSize);
    }

    @Override
    public RollPageResult<T> rollPage(Class<T> clazz, List<Criteria> criteriaList, String[] fields, Integer pageNum, Integer pageSize) {
        return rollPage(clazz, criteriaList, fields, pageNum, pageSize, null);
    }

    @Override
    public RollPageResult<T> rollPage(Class<T> clazz, List<Criteria> criteriaList, String[] fields, Integer pageNum, Integer pageSize, Sort sort) {
        return rollPage(clazz, criteriaList, fields, pageNum, pageSize, sort, true);
    }

    public RollPageResult<T> rollPage(Class<T> clazz, List<Criteria> criteriaList, String[] fields, Integer pageNum, Integer pageSize, Sort sort, boolean needCount) {
        //初始化响应对象
        RollPageResult<T> result = new RollPageResult();
        //初始化条件
        Criteria criteria = new Criteria();
        //如果存在条件列表
        if (CollectionUtils.isNotEmpty(criteriaList)) {
            //组装条件列表
            criteria.andOperator(criteriaList.toArray(new Criteria[]{}));
        }
        //初始化查询
        Query query = new Query(criteria);
        //如果需要排序
        if (sort != null) {
            //按照规则
            query.with(sort);
        } else {
            //默认排序,按照创建时间倒序
            query.with(Sort.by(Sort.Order.desc("createDate")));
        }
        //如果需要限制返回字段
        if (fields != null && fields.length > 0) {
            //设置需要的字段
            query.fields().include(fields);
        }
        //如果需要count
        if (needCount) {
            //查询count
            long total = this.mongoTemplate.count(query, clazz);
            //组装
            result.setTotal(total);
        } else {
            //默认
            result.setTotal(-1L);
        }
        //设置分页
        query = MongoExtraUtils.setPage(query, pageNum, pageSize);
        //日志
        LOG.info("Mongo RollPage Query:[{}]", query.toString());
        //查询数据
        List<T> list = this.mongoTemplate.find(query, clazz);
        //组装数据
        result.setList(list);
        //返回
        return result;
    }

}

