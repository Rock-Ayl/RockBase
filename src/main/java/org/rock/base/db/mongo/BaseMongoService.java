package org.rock.base.db.mongo;

import lombok.Getter;
import lombok.Setter;
import org.rock.base.pojo.base.BaseDocument;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.Collection;
import java.util.List;

/**
 * mongo服务基底
 *
 * @Author ayl
 * @Date 2022-03-09
 */
public interface BaseMongoService<T extends BaseDocument> {

    /**
     * 单个创建
     *
     * @param document
     * @return
     */
    T create(T document);

    /**
     * 批量创建
     *
     * @param documentList
     * @return
     */
    Collection<T> create(List<T> documentList);

    /**
     * 根据id,查询单个
     *
     * @param clazz
     * @param id
     * @return
     */
    T getById(Class<T> clazz, String id);

    /**
     * 根据id列表,查询多个
     *
     * @param clazz
     * @param idList
     * @return
     */
    List<T> listByIdList(Class<T> clazz, List<String> idList);

    /**
     * 根据id,真实删除
     *
     * @param id
     * @param clazz
     * @return
     */
    boolean deleteById(Class<T> clazz, String id);

    /**
     * 根据id列表,批量真实删除
     *
     * @param idList id列表
     * @param clazz
     * @return
     */
    boolean deleteByIdList(Class<T> clazz, List<String> idList);

    /**
     * 根据实体,使用id,更新单个实体,跳过NULL的字段
     *
     * @param document
     * @return
     */
    boolean updateSkipNullById(T document);

    /**
     * 根据实体列表,使用id,批量更新多个实体,跳过NULL的字段
     *
     * @param documentList
     * @return
     */
    boolean batchUpdateSkipNullById(Class<T> clazz, List<T> documentList);

    /**
     * 查询响应对象实体
     *
     * @param <T>
     */
    @Getter
    @Setter
    class RollPageResult<T> {

        //总数
        private long total;

        //数据
        private List<T> list;

    }

    /**
     * 翻页查询
     *
     * @param clazz        实体
     * @param criteriaList 条件列表
     * @param pageNum      分页,页码
     * @param pageSize     分页,每页数量
     * @return
     */
    RollPageResult<T> rollPage(Class<T> clazz, List<Criteria> criteriaList, Integer pageNum, Integer pageSize);

    /**
     * 翻页查询
     *
     * @param clazz        实体
     * @param criteriaList 条件列表
     * @param fields       限制字段
     * @param pageNum      分页,页码
     * @param pageSize     分页,每页数量
     * @return
     */
    RollPageResult<T> rollPage(Class<T> clazz, List<Criteria> criteriaList, String[] fields, Integer pageNum, Integer pageSize);

    /**
     * 翻页查询
     *
     * @param clazz        实体
     * @param criteriaList 条件列表
     * @param fields       限制字段
     * @param pageNum      分页,页码
     * @param pageSize     分页,每页数量
     * @param sort         排序
     * @return
     */
    RollPageResult<T> rollPage(Class<T> clazz, List<Criteria> criteriaList, String[] fields, Integer pageNum, Integer pageSize, Sort sort);

    /**
     * 翻页查询
     *
     * @param clazz        实体
     * @param criteriaList 条件列表
     * @param fields       限制字段
     * @param pageNum      分页,页码
     * @param pageSize     分页,每页数量
     * @param sort         排序
     * @param needCount    是否需要count(如果不需要,减少一次查询消耗)
     * @return
     */
    RollPageResult<T> rollPage(Class<T> clazz, List<Criteria> criteriaList, String[] fields, Integer pageNum, Integer pageSize, Sort sort, boolean needCount);

    /**
     * Mongo常用模板查询参数
     */

    @Getter
    @Setter
    public static class MongoRollPageParam {

        /**
         * 分页
         */

        //分页
        private Integer pageSize;
        private Integer pageNum;

        //是否需要count(如果需要,多做一次查询)
        private Boolean needCount;

        /**
         * 排序
         */

        //排序字段
        private String sortKey;
        //正序还是倒叙
        private String sortOrder;

        /**
         * 限制时间区间
         */

        //限制时间类型
        private String timeType;
        //开始时间
        private Long startTime;
        //结束时间
        private Long endTime;

        /**
         * 限制关键词
         */

        //精确/模糊搜索
        private String searchType;
        //关键字搜索类型
        private String keywordType;
        //关键字,支持批量(回车是换行)
        private String keywords;

        /**
         * 限制返回字段
         */

        //限制返回字段
        private String fields;

    }

    /**
     * 模板翻页查询
     *
     * @param clazz 实体
     * @param param 模板参数
     * @return
     */
    RollPageResult<T> rollPage(Class<T> clazz, MongoRollPageParam param);

    /**
     * 模板翻页查询
     *
     * @param clazz        实体
     * @param param        模板参数
     * @param criteriaList 除了模板参数,其他的限制条件
     * @return
     */
    RollPageResult<T> rollPage(Class<T> clazz, MongoRollPageParam param, List<Criteria> criteriaList);

}
