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
public class LambdaCriteria {

    //被封装的,链路中最新的,每一个操作后,和之前的都不会是一个对象
    private Criteria criteria;

    /**
     * 重写初始化
     */
    public LambdaCriteria() {
        //初始化默认
        this.criteria = new Criteria();
    }

    /**
     * 重写初始化
     */
    public <T, R> LambdaCriteria(LambdaParseFieldNameExtraUtils.MFunction<T, R> key) {
        //初始化默认
        this.criteria = Criteria.where(LambdaParseFieldNameExtraUtils.getMongoColumn(key));
    }

    /**
     * 实现 where
     *
     * @param key key
     * @return
     */
    public static <T, R> LambdaCriteria where(LambdaParseFieldNameExtraUtils.MFunction<T, R> key) {
        //实现
        return new LambdaCriteria(key);
    }

    /**
     * 实现 and
     *
     * @param key key
     * @return
     */
    public <T, R> LambdaCriteria and(LambdaParseFieldNameExtraUtils.MFunction<T, R> key) {
        //解析key、并实现
        this.criteria = this.criteria.and(LambdaParseFieldNameExtraUtils.getMongoColumn(key));
        //返回
        return this;
    }

    /**
     * 实现 andOperator
     *
     * @param mongoLambdaCriteria
     * @return
     */
    public LambdaCriteria andOperator(LambdaCriteria... mongoLambdaCriteria) {
        //实现
        this.criteria = this.criteria.andOperator(Arrays
                //装箱
                .stream(mongoLambdaCriteria)
                //拆出对应Criteria
                .map(LambdaCriteria::getCriteria)
                //转化为列表
                .collect(Collectors.toList())
                //然后转数组
                .toArray(new Criteria[]{}));
        //返回
        return this;
    }

    /**
     * 实现 is
     *
     * @param value value
     * @return
     */
    public LambdaCriteria is(Object value) {
        //实现
        this.criteria = this.criteria.is(value);
        //返回
        return this;
    }

    /**
     * 实现 in
     *
     * @param values values
     * @return
     */
    public LambdaCriteria in(Collection<?> values) {
        //实现
        this.criteria = this.criteria.in(values);
        //返回
        return this;
    }

    /**
     * 实现 in
     *
     * @param values values
     * @return
     */
    public LambdaCriteria in(Object... values) {
        //实现
        this.criteria = this.criteria.in(values);
        //返回
        return this;
    }

    /**
     * 实现 ne
     *
     * @param value value
     * @return
     */
    public LambdaCriteria ne(Object value) {
        //实现
        this.criteria = this.criteria.ne(value);
        //返回
        return this;
    }

    /**
     * 实现 nin
     *
     * @param values values
     * @return
     */
    public LambdaCriteria nin(Collection<?> values) {
        //实现
        this.criteria = this.criteria.nin(values);
        //返回
        return this;
    }

    /**
     * 实现 nin
     *
     * @param values values
     * @return
     */
    public LambdaCriteria nin(Object... values) {
        //实现
        this.criteria = this.criteria.nin(values);
        //返回
        return this;
    }

    /**
     * 返回内部 {@link Criteria}
     *
     * @return
     */
    public Criteria getCriteria() {
        //返回
        return this.criteria;
    }

    @Override
    public String toString() {
        //直接使用
        return this.criteria.toString();
    }

}