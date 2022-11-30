package org.rock.base.util;

import java.util.UUID;

/**
 * ID 扩展工具包
 *
 * @Author ayl
 * @Date 2022-03-10
 */
public class IdExtraUtils {

    /**
     * 生成一个GUID
     *
     * @return
     */
    public static String genGUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 生成一个用户简单的token
     *
     * @return
     */
    public static String creatUserToken(String userId) {
        return genGUID() + userId + System.currentTimeMillis();
    }

}
