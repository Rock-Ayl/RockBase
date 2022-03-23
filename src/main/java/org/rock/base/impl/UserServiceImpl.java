package org.rock.base.impl;

import org.rock.base.db.mysql.BaseMysqlServiceImpl;
import org.rock.base.pojo.mdo.UserDO;
import org.rock.base.serivce.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户服务实现
 */
@Service
public class UserServiceImpl extends BaseMysqlServiceImpl<UserDO> implements UserService {

    @Override
    public UserDO addUser(UserDO userDO) {
        //实现
        return this.create(userDO);
    }

}
