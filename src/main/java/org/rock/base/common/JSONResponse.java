package org.rock.base.common;

import com.alibaba.fastjson.JSONObject;

import static org.rock.base.constant.JSONConst.*;

/**
 * FastJson封装器,方便控制层组装返回,规范json结构
 *
 * @Author ayl
 * @Date 2022-03-09
 */
public class JSONResponse {

    //该class本质是一个FastJson
    private JSONObject fastJson;

    //私有化
    private JSONResponse() {
        //同时生成对应json
        this.fastJson = new JSONObject();
    }

    /**
     * 成功
     *
     * @return
     */
    public static JSONResponse success() {
        //初始化
        JSONResponse response = new JSONResponse();
        //组装success
        response.fastJson.put(KEY_CODE, VALUE_SUCCESS);
        //返回
        return response;
    }

    //失败
    public static JSONResponse error() {
        //初始化
        JSONResponse response = new JSONResponse();
        //组装error
        response.fastJson.put(KEY_CODE, VALUE_ERROR);
        //返回
        return response;
    }

    //失败+msg
    public static JSONResponse error(String errorMsg) {
        //初始化
        JSONResponse response = new JSONResponse();
        //组装error
        response.fastJson.put(KEY_CODE, VALUE_ERROR);
        response.fastJson.put(KEY_ERROR_MSG, errorMsg);
        //返回
        return response;
    }

    //组装返回结果
    public JSONResponse putResult(Object value) {
        //固定key
        this.fastJson.put(KEY_RESULT, value);
        //返回
        return this;
    }

    //组装一般key
    public JSONResponse put(String property, Object value) {
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
