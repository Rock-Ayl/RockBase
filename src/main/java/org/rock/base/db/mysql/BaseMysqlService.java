package org.rock.base.db.mysql;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.rock.base.pojo.base.BaseDO;

import java.util.List;

/**
 * mysql服务基底
 *
 * @Author ayl
 * @Date 2022-03-13
 */
public interface BaseMysqlService<T extends BaseDO> {

    /**
     * 插入一条记录
     *
     * @param entity 实体对象
     */
    T create(T entity);

    /**
     * todo 批量插入记录(未实现真正批量操作,本质还是单个)
     *
     * @param entities 实体对象列表
     */
    List<T> create(List<T> entities);

    /**
     * 根据id查询单个
     *
     * @param id 主键ID
     */
    T get(String id);

    /**
     * 根据id列表查询多个
     *
     * @param idList 主键ID列表(不能为 null 以及 empty)
     */
    List<T> list(List<String> idList);

    /**
     * 根据id列表查询所有
     */
    List<T> listAll();

    /**
     * 根据id删除单个
     *
     * @param id 主键ID
     */
    int delete(String id);

    /**
     * 根据id列表批量删除
     *
     * @param idList 主键ID列表(不能为 null 以及 empty)
     */
    int delete(List<String> idList);

    /**
     * 根据实体修改,跳过NULL的字段
     *
     * @param entity 实体对象
     */
    int updateSkipNull(T entity);

    /**
     * 根据条件更新,跳过NULL的字段
     *
     * @param entity        实体对象 (set 条件值,可以为 null)
     * @param updateWrapper 实体对象封装操作类（可以为 null,里面的 entity 用于生成 where 语句）
     */
    int updateSkipByWrapper(T entity, Wrapper<T> updateWrapper);

    /**
     * 根据条件，查询一条记录
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    T getByWrapper(Wrapper<T> queryWrapper);

    /**
     * 根据条件，查询总记录数
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    Integer getCountByWrapper(Wrapper<T> queryWrapper);

    /**
     * 根据条件，查询列表
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    List<T> listByWrapper(Wrapper<T> queryWrapper);

    /**
     * 根据条件，分页查询
     *
     * @param page         分页查询条件（可以为 RowBounds.DEFAULT）
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    <E extends IPage<T>> E rollPage(E page, Wrapper<T> queryWrapper);

}
