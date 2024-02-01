package org.rock.base.common.mongo.query;

import org.rock.base.util.LambdaParseFieldNameExtraUtils;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * 使用 Lambda表达式 构建 {@link Criteria}
 *
 * @Author ayl
 * @Date 2024-01-30
 */
public class MongoLambdaCriteria {

    //被封装的
    private Criteria criteria;

    /**
     * 重写初始化
     */
    public MongoLambdaCriteria() {
        //默认初始化一个
        this.criteria = new Criteria();
    }

    /**
     * 实现 where
     *
     * @param key key
     * @return
     */
    public static <T, R> MongoLambdaCriteria where(LambdaParseFieldNameExtraUtils.MFunction<T, R> key) {
        //初始化
        MongoLambdaCriteria mongoLambdaCriteria = new MongoLambdaCriteria();
        //默认一个and
        mongoLambdaCriteria.and(key);
        //返回
        return mongoLambdaCriteria;
    }

    /**
     * 实现 and
     *
     * @param key key
     * @return
     */
    public <T, R> MongoLambdaCriteria and(LambdaParseFieldNameExtraUtils.MFunction<T, R> key) {
        //解析key、并实现
        this.criteria.and(LambdaParseFieldNameExtraUtils.getMongoColumn(key));
        //返回
        return this;
    }

    /**
     * 实现 andOperator
     *
     * @param mongoLambdaCriteria
     * @return
     */
    public MongoLambdaCriteria andOperator(MongoLambdaCriteria... mongoLambdaCriteria) {
        //实现
        this.criteria.andOperator(Arrays.stream(mongoLambdaCriteria).map(MongoLambdaCriteria::toCriteria).collect(Collectors.toList()).toArray(new Criteria[]{}));
        //返回
        return this;
    }

    /**
     * 实现 is
     *
     * @param value value
     * @return
     */
    public MongoLambdaCriteria is(Object value) {
        //实现
        this.criteria.is(value);
        //返回
        return this;
    }

    /**
     * 实现 in
     *
     * @param values values
     * @return
     */
    public MongoLambdaCriteria in(Collection<?> values) {
        //实现
        this.criteria.in(values);
        //返回
        return this;
    }

    /**
     * 实现 in
     *
     * @param values values
     * @return
     */
    public MongoLambdaCriteria in(Object... values) {
        //实现
        this.criteria.in(values);
        //返回
        return this;
    }

    /**
     * 返回内部 {@link Criteria}
     *
     * @return
     */
    public Criteria toCriteria() {
        //返回
        return this.criteria;
    }

    @Override
    public String toString() {
        //直接使用
        return this.criteria.toString();
    }

}