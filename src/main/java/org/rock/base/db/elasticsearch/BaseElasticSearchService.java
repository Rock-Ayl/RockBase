package org.rock.base.db.elasticsearch;

import lombok.Getter;
import lombok.Setter;
import org.rock.base.pojo.base.BaseIndex;
import org.springframework.data.elasticsearch.core.query.Query;

import java.util.Collection;
import java.util.List;

/**
 * elastic search 服务基底
 *
 * @Author ayl
 * @Date 2022-3-9
 */
public interface BaseElasticSearchService<T extends BaseIndex> {

    /**
     * 单个创建
     *
     * @param index
     * @return
     */
    T create(T index);

    /**
     * 批量创建
     *
     * @param indexList
     * @return
     */
    Collection<T> create(List<T> indexList);

    /**
     * 根据id查询单个
     *
     * @param clazz
     * @param id
     * @return
     */
    T get(Class<T> clazz, String id);

    /**
     * 根据id列表查询多个
     *
     * @param clazz
     * @param idList id列表
     * @return
     */
    List<T> list(Class<T> clazz, List<String> idList);

    /**
     * 根据条件查询多个
     *
     * @param clazz
     * @param query 限制条件
     * @return
     */
    List<T> list(Class<T> clazz, Query query);

    /**
     * 根据id真实删除
     *
     * @param id
     * @param clazz
     * @return
     */
    void delete(Class<T> clazz, String id);

    /**
     * 根据实体更新单个实体,跳过NULL的字段
     *
     * @param index
     */
    void updateSkipNull(T index);

    /**
     * 根据多个实体批量更新实体,跳过NULL的字段
     *
     * @param indexList
     */
    void batchUpdateSkipNull(List<T> indexList);

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
     * @param clazz    类
     * @param query    条件
     * @param pageNum  分页-页码(可以为空)
     * @param pageSize 分页-数量(可以为空)
     * @return
     */
    RollPageResult<T> rollPage(Class<T> clazz, Query query, Integer pageNum, Integer pageSize);

}
