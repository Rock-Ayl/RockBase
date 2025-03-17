package com.rock.base.common.auth;

/**
 * 清理 {@link LoginAuth} 的所有内容
 */
public class ClearLoginSessionExecutor {

    /**
     * 实现
     */
    public static void clear() {
        LoginAuth.USER.remove();
    }

}