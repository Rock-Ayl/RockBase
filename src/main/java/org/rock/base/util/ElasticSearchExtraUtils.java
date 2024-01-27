package org.rock.base.util;

import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.ParsedMax;
import org.elasticsearch.search.aggregations.metrics.ParsedMin;
import org.elasticsearch.search.aggregations.metrics.ParsedSum;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Elastic Search 扩展工具包
 *
 * @Author ayl
 * @Date 2022-08-25
 */
public class ElasticSearchExtraUtils {

    /**
     * 获取聚合结果列表中的某个聚合对象
     *
     * @param aggregations es搜索聚合结果集合
     * @param aggName      聚合搜索的名字
     * @return
     */
    public static Aggregation getAggregation(Aggregations aggregations, String aggName) {
        //解析并返回
        return Optional.ofNullable(aggregations)
                .map(Aggregations::asList)
                .orElse(new ArrayList<>())
                .stream()
                //只要目标名称的
                .filter(p -> p != null && aggName.equals(p.getName()))
                //获取第一个
                .findFirst()
                //默认null
                .orElse(null);
    }

    /**
     * 获取聚合结果列表中的某个Max聚合的结果
     *
     * @param aggregations es搜索聚合结果集合
     * @param aggName      聚合搜索的名字
     * @return
     */
    public static Double getAggregationMax(Aggregations aggregations, String aggName) {
        //先获取目标聚合对象
        Aggregation targetAgg = getAggregation(aggregations, aggName);
        //获取聚合结果bucket并返回
        return Optional.ofNullable(targetAgg)
                //获取里面的最大值
                .map(p -> toMaxGetValue(p))
                //默认空
                .orElse(null);
    }

    /**
     * 获取聚合结果列表中的某个Min聚合的结果
     *
     * @param aggregations es搜索聚合结果集合
     * @param aggName      聚合搜索的名字
     * @return
     */
    public static Double getAggregationMin(Aggregations aggregations, String aggName) {
        //先获取目标聚合对象
        Aggregation targetAgg = getAggregation(aggregations, aggName);
        //获取聚合结果bucket并返回
        return Optional.ofNullable(targetAgg)
                //获取里面的最小值
                .map(p -> toMinGetValue(p))
                //默认空
                .orElse(null);
    }

    /**
     * 根据聚合结果,解析里面指定[Term分组统计]名字的bucket列表
     *
     * @param aggregations es搜索结果(集合)
     * @param aggTermsName terms聚合搜索的名字
     * @return
     */
    public static List<ParsedStringTerms.ParsedBucket> parseTermsAggToBucketList(Aggregations aggregations, String aggTermsName) {
        //先获取目标聚合对象
        Aggregation targetAgg = getAggregation(aggregations, aggTermsName);
        //获取聚合结果bucket并返回
        return Optional.ofNullable(targetAgg)
                //转化为[ParsedStringTerms]实体
                .map(p -> toTerms(p))
                //解析bucket列表
                .map(ParsedStringTerms::getBuckets)
                .orElse(new ArrayList<>())
                .stream()
                //转化为[ParsedStringTerms.ParsedBucket]实体
                .map(p -> toBucket(p))
                .collect(Collectors.toList());
    }

    /**
     * 聚合结果转化为 ParsedStringTerms
     *
     * @param aggregation
     * @return
     */
    public static ParsedStringTerms toTerms(Aggregation aggregation) {
        //判空
        if (aggregation == null) {
            //过
            return null;
        }
        //如果不是目标类型
        if (aggregation instanceof ParsedStringTerms == false) {
            //过
            return null;
        }
        //强转并返回
        return (ParsedStringTerms) aggregation;
    }

    /**
     * 聚合结果转化为 ParsedStringTerms.ParsedBucket
     *
     * @param bucket
     * @return
     */
    public static ParsedStringTerms.ParsedBucket toBucket(Terms.Bucket bucket) {
        //判空
        if (bucket == null) {
            //过
            return null;
        }
        //如果不是目标类型
        if (bucket instanceof ParsedStringTerms.ParsedBucket == false) {
            //过
            return null;
        }
        //强转并返回
        return (ParsedStringTerms.ParsedBucket) bucket;
    }

    /**
     * 聚合结果转化为 ParsedStringTerms.ParsedBucket
     *
     * @param aggregation
     * @return
     */
    public static ParsedSum toSum(Aggregation aggregation) {
        //判空
        if (aggregation == null) {
            //过
            return null;
        }
        //如果不是目标类型
        if (aggregation instanceof ParsedSum == false) {
            //过
            return null;
        }
        //强转并返回
        return (ParsedSum) aggregation;
    }

    /**
     * 聚合结果获取最大值
     *
     * @param aggregation
     * @return
     */
    public static Double toMaxGetValue(Aggregation aggregation) {
        //判空
        if (aggregation == null) {
            //过
            return null;
        }
        //如果不是目标类型
        if (aggregation instanceof ParsedMax == false) {
            //过
            return null;
        }
        //强转并返回结果
        return ((ParsedMax) aggregation).getValue();
    }

    /**
     * 聚合结果获取最小值
     *
     * @param aggregation
     * @return
     */
    public static Double toMinGetValue(Aggregation aggregation) {
        //判空
        if (aggregation == null) {
            //过
            return null;
        }
        //如果不是目标类型
        if (aggregation instanceof ParsedMin == false) {
            //过
            return null;
        }
        //强转并返回结果
        return ((ParsedMin) aggregation).getValue();
    }

}
