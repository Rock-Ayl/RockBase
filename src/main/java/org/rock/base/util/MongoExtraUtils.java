package org.rock.base.util;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * mongo 扩展工具包
 *
 * @Author ayl
 * @Date 2021-12-24
 */
public class MongoExtraUtils {

    /**
     * 为 mongo query 对象组装限制返回参数
     *
     * @param query  mongo query 对象
     * @param fields 限制参数 eg:   "id,state,sellerSku"
     */
    public static void setFields(Query query, String fields) {
        //根据,分割
        List<String> fieldList = ListExtraUtils.split(fields);
        //判空
        if (CollectionUtils.isEmpty(fieldList)) {
            //过
            return;
        }
        //组装
        query.fields().include(fieldList.toArray(new String[]{}));
    }

    /**
     * 根据要限制的字段,初始化一个 fieLdsArr
     *
     * @param fields eg id,productSku,developerList
     * @return
     */
    public static String[] initDocumentByFields(String fields) {
        //实现
        return ListExtraUtils.split(fields).toArray(new String[]{});
    }

    /**
     * 关键词搜索时,转义正则特殊字符 （$()*+.[]?\^{},|）
     *
     * @param keyword
     * @return
     */
    public static String escapeExprSpecialWord(String keyword) {
        //一般特殊
        String[] fbsArr = {"\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|"};
        //循环
        for (String key : fbsArr) {
            //一般的特殊字符
            if (keyword.contains(key)) {
                //替换
                keyword = keyword.replace(key, "\\" + key);
            }
        }
        //返回
        return keyword;
    }

    /**
     * 为query设置常用分页
     *
     * @param query
     * @param pageNum  分页,可为空
     * @param pageSize 分页,可为空
     * @return
     */
    public static Query setPage(Query query, Integer pageNum, Integer pageSize) {
        //如果需要限制分页
        if (pageSize != null && pageNum != null && pageNum != 0 && pageSize != 0) {
            //限制分页
            query.limit(pageSize).skip((pageNum - 1L) * pageSize);
        }
        //返回
        return query;
    }

}
