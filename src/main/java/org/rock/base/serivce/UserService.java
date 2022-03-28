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

    /**
     * 根据手机号获取用户
     *
     * @param phone 手机号
     * @return
     */
    UserDO getByPhone(String phone);

    /**
     * 根据手机号,密码登录
     *
     * @param phone 手机号
     * @param pwd   密码
     * @return
     */
    String loginByPhone(String phone, String pwd);

}
