package org.rock.main.mongo;

import lombok.Getter;
import lombok.Setter;
import org.bson.Document;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Collection;
import java.util.List;

/**
 * 封装的mongo服务
 *
 * @Author ayl
 * @Date 2022-03-09
 */
public interface BaseMongoService<T> {

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
     * @param document
     * @return
     */
    Collection<T> create(List<T> document);

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
    boolean delete(String id, Class<T> clazz);

    /**
     * 覆盖更新
     *
     * @param clazz
     * @param id
     * @param update
     * @param ver
     * @return
     */
    long update(Class<T> clazz, String id, Update update, long ver);

    /**
     * 根据实体更新单个实体,跳过NULL的字段
     *
     * @param document
     * @param ver
     * @return
     */
    boolean updateSkipNull(T document, long ver);

    /**
     * 翻页查询
     *
     * @param clazz     实体
     * @param criteria  条件
     * @param fields    字段
     * @param pageNum   分页,页码
     * @param pageSize  分页,每页数量
     * @param sort      排序
     * @param needCount 是否需要count(如果不需要,减少一次查询消耗)
     * @return
     */
    BaseMongoService.RollPageResult<T> rollPage(Class<T> clazz, Criteria criteria, Document fields, long pageNum, long pageSize, Sort sort, boolean needCount);

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

}