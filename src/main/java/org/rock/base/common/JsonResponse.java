package org.rock.base.common;

import com.alibaba.fastjson.JSONObject;

import static org.rock.base.constant.JSONConst.*;

/**
 * Json封装器,方便控制层组装返回
 *
 * @Author ayl
 * @Date 2022-03-09
 */
public class JsonResponse {

    //该class本质是一个FastJson
    private JSONObject fastJson;

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
        response.fastJson.put(KEY_CODE, VALUE_SUCCESS);
        //返回
        return response;
    }

    //失败
    public static JsonResponse error() {
        //初始化
        JsonResponse response = new JsonResponse();
        //组装error
        response.fastJson.put(KEY_CODE, VALUE_ERROR);
        //返回
        return response;
    }

    //失败+msg
    public static JsonResponse error(String errorMsg) {
        //初始化
        JsonResponse response = new JsonResponse();
        //组装error
        response.fastJson.put(KEY_CODE, VALUE_ERROR);
        response.fastJson.put(KEY_ERROR_MSG, errorMsg);
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
    public JSONObject toJSONObject() {
        return this.fastJson;
    }

}
