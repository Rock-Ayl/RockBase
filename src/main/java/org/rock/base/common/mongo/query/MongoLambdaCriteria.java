package org.rock.base.common.mongo.query;

import org.springframework.data.mongodb.core.query.Criteria;

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
    public MongoLambdaCriteria where(String key) {
        //重新初始化一个
        this.criteria = Criteria.where(key);
        //返回
        return this;
    }

    /**
     * 实现 and
     *
     * @param key key
     * @return
     */
    public MongoLambdaCriteria and(String key) {
        //实现
        this.criteria.and(key);
        //返回
        return this;
    }

    /**
     * 实现 and
     *
     * @param criteria Criteria 数组
     * @return
     */
    public MongoLambdaCriteria andOperator(Criteria... criteria) {
        //实现
        this.criteria.andOperator(criteria);
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