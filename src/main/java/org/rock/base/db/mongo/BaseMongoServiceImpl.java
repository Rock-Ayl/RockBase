package org.rock.base.db.mongo;

import com.google.common.base.CaseFormat;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.rock.base.pojo.base.BaseDocument;
import org.rock.base.util.ListExtraUtils;
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
import java.util.*;
import java.util.regex.Pattern;

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

    @Override
    public RollPageResult<T> rollPage(Class<T> clazz, MongoRollPageParam param) {
        //实现
        return rollPage(clazz, param, null);
    }

    @Override
    public RollPageResult<T> rollPage(Class<T> clazz, MongoRollPageParam param, List<Criteria> criteriaList) {

        /**
         * 初始化
         */

        //and条件列表
        List<Criteria> andCriteriaList = new ArrayList<>();
        //如果有额外的条件
        if (CollectionUtils.isNotEmpty(criteriaList)) {
            //组装
            andCriteriaList.addAll(criteriaList);
        }

        /**
         * 处理各种模板参数
         */

        //如果要限制时间范围
        if (StringUtils.isNotBlank(param.getTimeType()) && param.getStartTime() != null && param.getEndTime() != null) {
            //限制时间范围
            andCriteriaList.add(Criteria.where(param.getTimeType()).gte(new Date(param.getStartTime())).lte(new Date(param.getEndTime())));
        }
        //根据空格分割获取关键字列表
        List<String> keywordList = ListExtraUtils.split(param.getKeywords(), "\n");
        //获取查询模式,默认查询产品SKU
        String keywordType = Optional.ofNullable(param)
                .map(MongoRollPageParam::getKeywordType)
                .orElse("");
        //如果需要限制关键词
        if (CollectionUtils.isNotEmpty(keywordList) && StringUtils.isNotBlank(keywordType)) {
            //查询精度,默认精确
            String searchType = Optional.ofNullable(param)
                    .map(MongoRollPageParam::getSearchType)
                    .orElse("exact");
            //判断是模糊还是确定还是其他
            switch (searchType) {
                //精确
                case "exact":
                    //支持多关键词
                    andCriteriaList.add(Criteria.where(keywordType).in(keywordList));
                    break;
                //简单模糊查询(适用90%情况)
                case "dim":
                    //模糊查不支持多关键词
                    andCriteriaList.add(Criteria.where(keywordType).regex(keywordList.stream().findFirst().get()));
                    break;
                //复杂模糊查询(消耗性能但是模糊准确,忽略大小写并适配特殊字符)
                case "complexDim":
                    //模糊查不支持多关键词
                    andCriteriaList.add(Criteria.where(keywordType).regex(Pattern.compile("^.*" + MongoExtraUtils.escapeExprSpecialWord(keywordList.stream().findFirst().get()) + ".*$", Pattern.CASE_INSENSITIVE)));
                    break;
            }
        }
        //排序key,默认创建时间
        String sortKey = Optional.ofNullable(param)
                .map(MongoRollPageParam::getSortKey)
                .orElse("createDate");
        //排序方式,默认倒序
        String sortOrder = Optional.ofNullable(param)
                .map(MongoRollPageParam::getSortOrder)
                .orElse("desc");
        //是否需要count,默认false
        boolean needCount = Optional.ofNullable(param)
                .map(MongoRollPageParam::getNeedCount)
                .orElse(false);
        //查询实现
        return rollPage(
                //限制class
                clazz,
                //组装各种条件
                andCriteriaList,
                //限制返回字段
                MongoExtraUtils.initDocumentByFields(param.getFields()),
                //分页,默认20
                param.getPageNum() == null ? 1 : param.getPageNum(),
                param.getPageSize() == null ? 20 : param.getPageSize(),
                //指定排序
                Sort.by(Sort.Direction.fromString(sortOrder), sortKey),
                //是否返回count
                needCount
        );
    }

}

