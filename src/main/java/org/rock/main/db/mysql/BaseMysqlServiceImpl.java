package org.rock.main.db.mysql;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BaseMysqlServiceImpl<T> implements BaseMysqlService<T> {

    @Resource
    private BaseMapper<T> baseMapper;

    @Override
    public List<T> selectList(Wrapper<T> queryWrapper) {
        return baseMapper.selectList(queryWrapper);
    }

}