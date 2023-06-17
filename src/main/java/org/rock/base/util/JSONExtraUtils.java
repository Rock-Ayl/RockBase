package org.rock.base.util;

import com.alibaba.fastjson.JSON;
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
        //判空
        if (object == null) {
            //过
            return null;
        }
        //先转为string再转为对应实体,如果转为json对象再转实体某些特殊情况会报错
        return JSON.parseObject(JSON.toJSONString(object), toJavaObject);
    }

    /**
     * 深克隆为数组对象,也可以将一个对象数组转化为另一个对象数组(当然,结构得基本一致或继承关系)
     *
     * @param listOrArrObject 源对象,比如父对象,必须是数组、集合等结构
     * @param toJavaObject    目标对象
     * @return
     */
    public static <T> List<T> deepCloneList(Object listOrArrObject, Class<T> toJavaObject) {
        //判空
        if (listOrArrObject == null) {
            //过
            return new ArrayList<>();
        }
        //先转为string再转为对应实体,如果转为json对象再转实体某些特殊情况会报错
        return JSON.parseArray(JSON.toJSONString(listOrArrObject), toJavaObject);
    }

    /**
     * 给json某个key该名称,注意,如果新名称存在,会被覆盖
     *
     * @param json    json
     * @param oldName 旧名称
     * @param newName 新名称
     */
    public static void rename(JSONObject json, String oldName, String newName) {
        //判空
        if (json == null || oldName == null || newName == null) {
            //过
            return;
        }
        //如果不存在旧名称
        if (json.containsKey(oldName) == false) {
            //过
            return;
        }
        //获取值
        Object value = json.get(oldName);
        //删除旧key
        json.remove(oldName);
        //覆盖新名称
        json.put(newName, value);
    }

}