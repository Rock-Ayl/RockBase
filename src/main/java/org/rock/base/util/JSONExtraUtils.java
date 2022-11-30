package org.rock.base.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * JSON 扩展工具包
 *
 * @Author ayl
 * @Date 2022-08-25
 */
public class JSONExtraUtils {

    /**
     * 深克隆单个对象,也可以将一个对象转化为另一个对象(当然,结构得基本一致或继承关系)
     *
     * @param object       源对象,比如父对象,不能是数组等结构
     * @param toJavaObject 目标对象
     * @return
     */
    public static <T> T deepClone(Object object, Class<T> toJavaObject) {
        //解析ob
        Object ob = JSON.toJSON(object);
        //判空
        if (ob == null) {
            //过
            return null;
        }
        //如果不是Json(集合、特殊情况不做考虑,用steam一个个来)
        if (ob instanceof JSONObject == false) {
            //过
            return null;
        }
        //强转一下
        JSONObject json = (JSONObject) ob;
        //再转化为实体
        return json.toJavaObject(toJavaObject);
    }

    /**
     * 深克隆为数组对象,也可以将一个对象数组转化为另一个对象数组(当然,结构得基本一致或继承关系)
     *
     * @param listOrArrObject 源对象,比如父对象,必须是数组、集合等结构
     * @param toJavaObject    目标对象
     * @return
     */
    public static <T> List<T> deepCloneList(Object listOrArrObject, Class<T> toJavaObject) {
        //解析ob
        Object ob = JSON.toJSON(listOrArrObject);
        //判空
        if (ob == null) {
            //过
            return new ArrayList<>();
        }
        //如果不是JsonArray(JSON的用上面的)
        if (ob instanceof JSONArray == false) {
            //过
            return new ArrayList<>();
        }
        //强转一下
        JSONArray JsonArray = (JSONArray) ob;
        //再转化为实体
        return JsonArray.toJavaList(toJavaObject);
    }

}
