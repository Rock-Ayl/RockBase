package org.rock.base.common;

import com.alibaba.fastjson.JSONObject;
import org.rock.base.constant.HttpConst;
import org.rock.base.enums.HttpStatusEnum;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 请求响应封装器,一般网络请求状态通用返回
 *
 * @Author ayl
 * @Date 2022-03-22
 */
public class HttpResponse {

    /**
     * 根据http请求状态枚举,响应标准格式的错误消息
     *
     * @param response 请求响应
     * @param status   请求状态枚举
     * @throws IOException
     */
    public static void sendError(HttpServletResponse response, HttpStatusEnum status) throws IOException {
        //统一设置响应体类型为JSON
        response.setContentType(HttpConst.RESPONSE_HEADERS_CONTENT_TYPE_APPLICATION_JSON);
        //设置其状态码
        response.setStatus(status.getCode());
        //body体
        JSONObject json = new JSONObject();
        json.put("desc", status.getDesc());
        json.put("status", status.getCode());
        json.put("message", status.getMessage());
        json.put("timestamp", System.currentTimeMillis());
        //写入
        response.getWriter().write(json.toString());
    }

}
