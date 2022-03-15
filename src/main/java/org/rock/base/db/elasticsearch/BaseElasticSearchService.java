package org.rock.base.db.elasticsearch;

import org.rock.base.pojo.base.BaseIndex;

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
     * 根据id查询单个实体
     *
     * @param clazz
     * @param id
     * @return
     */
    T get(Class<T> clazz, String id);

    /**
     * 根据id查询多个
     *
     * @param clazz
     * @param idList
     * @return
     */
    List<T> list(Class<T> clazz, List<String> idList);

    /**
     * 根据id真实删除
     *
     * @param id
     * @param clazz
     * @return
     */
    void delete(String id, Class<T> clazz);

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


}
