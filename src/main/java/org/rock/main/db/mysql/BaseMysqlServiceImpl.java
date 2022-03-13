package org.rock.main.db.mysql;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class BaseMysqlServiceImpl<T> implements BaseMysqlService<T> {

    @Resource
    private BaseMapper<T> baseMapper;

    @Override
    public int insert(T entity) {
        return 0;
    }

    @Override
    public int deleteById(Serializable id) {
        return baseMapper.deleteById(id);
    }

    @Override
    public int deleteByMap(Map<String, Object> columnMap) {
        return baseMapper.deleteByMap(columnMap);
    }

    @Override
    public int delete(Wrapper<T> queryWrapper) {
        return baseMapper.delete(queryWrapper);
    }

    @Override
    public int deleteBatchIds(Collection<? extends Serializable> idList) {
        return baseMapper.deleteBatchIds(idList);
    }

    @Override
    public int updateById(T entity) {
        return 0;
    }

    @Override
    public int update(T entity, Wrapper<T> updateWrapper) {
        return 0;
    }

    @Override
    public T selectById(Serializable id) {
        return baseMapper.selectById(id);
    }

    @Override
    public List<T> selectBatchIds(Collection<? extends Serializable> idList) {
        return baseMapper.selectBatchIds(idList);
    }

    @Override
    public List<T> selectByMap(Map<String, Object> columnMap) {
        return baseMapper.selectByMap(columnMap);
    }

    @Override
    public T selectOne(Wrapper<T> queryWrapper) {
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public Integer selectCount(Wrapper<T> queryWrapper) {
        return baseMapper.selectCount(queryWrapper);
    }

    @Override
    public List<T> selectList(Wrapper<T> queryWrapper) {
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<Map<String, Object>> selectMaps(Wrapper<T> queryWrapper) {
        return baseMapper.selectMaps(queryWrapper);
    }

    @Override
    public List<Object> selectObjs(Wrapper<T> queryWrapper) {
        return baseMapper.selectObjs(queryWrapper);
    }

    @Override
    public <E extends IPage<T>> E selectPage(E page, Wrapper<T> queryWrapper) {
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public <E extends IPage<Map<String, Object>>> E selectMapsPage(E page, Wrapper<T> queryWrapper) {
        return selectMapsPage(page, queryWrapper);
    }

}