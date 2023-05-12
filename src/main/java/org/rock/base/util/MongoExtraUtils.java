package org.rock.base.util;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.rock.base.pojo.base.BaseDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Date;
import java.util.List;

/**
 * mongo 扩展工具包
 *
 * @Author ayl
 * @Date 2021-12-24
 */
public class MongoExtraUtils {

    private static final Logger LOG = LoggerFactory.getLogger(MongoExtraUtils.class);

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
     * 为 mongo Query 操作 初始化一个关于基类的 {@link Query}
     *
     * @param id 主键id
     * @return
     */
    public static Query initQueryAndBase(String id) {
        //限制条件
        Query query = new Query(Criteria
                .where("_id").is(id)
        );
        //返回
        return query;
    }

    /**
     * 为 mongo update 操作 初始化一个关于基类的 {@link Update}
     *
     * @return
     */
    public static Update initUpDateAndBase() {
        //初始化更新实体
        Update update = new Update();

        //固定更新最后更新时间
        update.set("updateDate", new Date());

        //返回实体
        return update;
    }

    /**
     * 根据实体,为 mongo {@link Update} set 该实体所有不为空的字段
     * 注意:不包含继承对象的参数
     *
     * @param update   要update的对象
     * @param document 实体
     * @return
     */
    public static <T extends BaseDocument> void updateSkipNullByDocumentNoExtends(Update update, T document) {
        //判空
        if (update == null || document == null) {
            //过
            return;
        }
        //获取本类的Field数组,继承无效
        java.lang.reflect.Field[] fields = document.getClass().getDeclaredFields();
        //判空
        if (fields == null || fields.length < 1) {
            //过
            return;
        }
        //循环
        for (java.lang.reflect.Field field : fields) {
            try {
                //字段名
                String fieldName = field.getName();
                //判空
                if (StringUtils.isBlank(fieldName)) {
                    //本轮过
                    continue;
                }
                //过滤掉一些特殊的
                switch (fieldName) {
                    //要被过滤的
                    case "id":
                    case "serialVersionUID":
                        continue;
                        //其他过
                    default:
                        break;
                }
                //限强制访问私有字段
                field.setAccessible(true);
                //获取内容
                Object value = field.get(document);
                //判空
                if (value != null) {
                    //组装
                    update.set(fieldName, value);
                }
            } catch (Exception e) {
                LOG.error("updateSkipNullByDocumentNoExtends error", e);
            }
        }
    }

    /**
     * 为 mongo upsert 操作 初始化一个关于基类的 {@link Update}
     *
     * @param id 主键id
     * @return
     */
    public static Update initUpsertAndBase(String id) {
        //初始化更新实体
        Update update = new Update();

        //仅创建唯一id
        update.setOnInsert("_id", id);
        //仅创建创建时间
        update.setOnInsert("createDate", new Date());
        //仅创建是否删除
        update.setOnInsert("del", false);

        //固定创建或更新最后更新时间
        update.set("updateDate", new Date());

        //返回实体
        return update;
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
        //判空
        if (StringUtils.isNotBlank(keyword)) {
            //过
            return "";
        }
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
