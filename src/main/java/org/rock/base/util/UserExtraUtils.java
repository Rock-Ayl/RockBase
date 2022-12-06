package org.rock.base.util;

import org.rock.base.pojo.mdo.UserDO;

/**
 * 用户 扩展工具包
 *
 * @Author ayl
 * @Date 2022-12-06
 */
public class UserExtraUtils {

    /**
     * 给用户实体脱敏
     *
     * @param userDO 店铺
     * @return
     */
    public static UserDO desensitization(UserDO userDO) {
        //判空
        if (userDO == null) {
            //过
            return null;
        }
        //脱敏
        userDO.setPwd(null);
        //返回实体
        return userDO;
    }

}
