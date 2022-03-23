package org.rock.base.db.mysql;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.rock.base.pojo.base.BaseDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BaseMysqlServiceImpl<T extends BaseDO> implements BaseMysqlService<T> {

    @Resource
    private BaseMapper<T> baseMapper;

    @Override
    public T create(T entity) {
        //创建前初始化
        BaseDO.createBuild(entity);
        //插入
        baseMapper.insert(entity);
        //返回
        return entity;
    }

    @Override
    public List<T> create(List<T> entities) {
        //循环
        for (T entity : entities) {
            //创建前初始化
            BaseDO.createBuild(entity);
            //创建(未实现真正批量)
            baseMapper.insert(entity);
        }
        //返回
        return entities;
    }

    @Override
    public T get(String id) {
        return baseMapper.selectById(id);
    }

    @Override
    public List<T> list(List<String> idList) {
        return baseMapper.selectBatchIds(idList);
    }

    @Override
    public List<T> listAll() {
        return baseMapper.selectList(null);
    }

    @Override
    public int delete(String id) {
        return baseMapper.deleteById(id);
    }

    @Override
    public int delete(List<String> idList) {
        return baseMapper.deleteBatchIds(idList);
    }

    @Override
    public int updateSkipNull(T entity) {
        //修改前初始化
        BaseDO.updateBuild(entity);
        //修改
        return baseMapper.updateById(entity);
    }

    @Override
    public int updateSkipByWrapper(T entity, Wrapper<T> updateWrapper) {
        //修改前初始化
        BaseDO.updateBuild(entity);
        //修改
        return baseMapper.update(entity, updateWrapper);
    }

    @Override
    public T getByWrapper(Wrapper<T> queryWrapper) {
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public Integer getCountByWrapper(Wrapper<T> queryWrapper) {
        return baseMapper.selectCount(queryWrapper);
    }

    @Override
    public List<T> listByWrapper(Wrapper<T> queryWrapper) {
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public <E extends IPage<T>> E rollPage(E page, Wrapper<T> queryWrapper) {
        return baseMapper.selectPage(page, queryWrapper);
    }

}