package org.rock.base.common;

import com.alibaba.fastjson.JSONObject;

/**
 * Json封装器,方便控制层组装返回
 *
 * @Author ayl
 * @Date 2022-03-09
 */
public class JsonResponse {

    //该class本质是一个FastJson
    private JSONObject fastJson;

    //常量
    private static final String CODE = "code";
    private static final String SUCCESS = "success";
    private static final String ERROR = "error";
    private static final String ERROR_MSG = "errorMsg";

    //私有化
    private JsonResponse() {
        //同时生成对应json
        this.fastJson = new JSONObject();
    }

    //成功
    public static JsonResponse success() {
        //初始化
        JsonResponse response = new JsonResponse();
        //组装success
        response.fastJson.put(CODE, SUCCESS);
        //返回
        return response;
    }

    //失败
    public static JsonResponse error() {
        //初始化
        JsonResponse response = new JsonResponse();
        //组装error
        response.fastJson.put(CODE, ERROR);
        //返回
        return response;
    }

    //失败+msg
    public static JsonResponse error(String errorMsg) {
        //初始化
        JsonResponse response = new JsonResponse();
        //组装error
        response.fastJson.put(CODE, ERROR);
        response.fastJson.put(ERROR_MSG, errorMsg);
        //返回
        return response;
    }

    //组装一般key
    public JsonResponse put(String property, Object value) {
        this.fastJson.put(property, value);
        return this;
    }

    //重写toString
    public String toString() {
        return this.fastJson.toString();
    }

    //转变为FastJson
    public JSONObject toFastJson() {
        return this.fastJson;
    }

}