package org.rock.base.serivce;

import org.rock.base.db.mysql.BaseMysqlService;
import org.rock.base.pojo.mdo.UserDO;

/**
 * 用户服务
 */
public interface UserService extends BaseMysqlService<UserDO> {

    /**
     * 创建用户
     *
     * @param userDO 用户实体
     * @return
     */
    UserDO addUser(UserDO userDO);

}
