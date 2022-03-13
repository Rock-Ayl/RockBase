package org.rock.main.db.mysql;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * mysql服务基底
 *
 * @Author ayl
 * @Date 2022-03-13
 */
public interface BaseMysqlService<T> {

    List<T> selectList(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

}
