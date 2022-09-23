package org.rock.base.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * JSON 扩展工具包
 *
 * @Author ayl
 * @Date 2022-08-25
 */
public class JSONExtraUtils {

    /**
     * 深克隆,也可以同结构对象互相转化
     *
     * @param object       源对象,比如父对象
     * @param toJavaObject 目标对象
     * @return
     */
    public static <T> T deepClone(Object object, Class<T> toJavaObject) {
        //判空
        if (object == null) {
            //过
            return null;
        }
        //先转化为json
        JSONObject json = (JSONObject) JSON.toJSON(object);
        //判空
        if (json == null) {
            //过
            return null;
        }
        //再转化为实体
        return json.toJavaObject(toJavaObject);
    }

}
