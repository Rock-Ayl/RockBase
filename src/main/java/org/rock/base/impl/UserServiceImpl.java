package org.rock.base.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.rock.base.constant.RedisKey;
import org.rock.base.db.mysql.BaseMysqlServiceImpl;
import org.rock.base.db.redis.BaseRedisService;
import org.rock.base.pojo.mdo.UserDO;
import org.rock.base.serivce.UserService;
import org.rock.base.util.IdExtraUtils;
import org.rock.base.util.UserExtraUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户服务实现
 */
@Service
public class UserServiceImpl extends BaseMysqlServiceImpl<UserDO> implements UserService {

    @Autowired
    private BaseRedisService baseRedisService;

    @Override
    public UserDO addUser(UserDO userDO) {
        //实现
        return this.create(userDO);
    }

    @Override
    public UserDO getByPhone(String phone) {
        //判空
        if (StringUtils.isBlank(phone)) {
            //过
            return null;
        }
        //初始化查询
        QueryWrapper<UserDO> query = new QueryWrapper<>();
        //限制受教
        query.eq("phone", phone);
        //查询并返回
        return this.getByWrapper(query);
    }

    @Override
    public String loginByPhone(String phone, String pwd) {
        //判空
        if (StringUtils.isAnyBlank(phone, pwd)) {
            //过
            return null;
        }
        //查询是否存在当前账号
        UserDO userDO = getByPhone(phone);
        //判空
        if (userDO == null) {
            //过
            return null;
        }
        //获取账号密码
        String userPwd = userDO.getPwd();
        //如果密码错误
        if (pwd.equals(userPwd) == false) {
            //过
            return null;
        }
        //用户id
        String userId = userDO.getId();
        //生成对用token
        String token = IdExtraUtils.creatUserToken(userId);
        //用户实体脱敏
        UserExtraUtils.desensitization(userDO);
        //写入缓存
        baseRedisService.setAndTime(RedisKey.USER_LOGIN_AUTH_SET + token, JSON.toJSONString(userDO), 7200);
        //返回
        return token;
    }

}
