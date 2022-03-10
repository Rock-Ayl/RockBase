package org.rock.main.util;

import java.util.UUID;

/**
 * @Author ayl
 * @Date 2022-03-10
 */
public class IdUtils {

    /**
     * 生成一个GUID
     *
     * @return
     */
    public static String genGUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
