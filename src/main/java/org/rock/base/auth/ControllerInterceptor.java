package org.rock.base.auth;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.rock.base.constant.HttpConst;
import org.rock.base.enums.HttpStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 请求拦截器实现
 *
 * @Author ayl
 * @Date 2022-03-21
 */
@Component
public class ControllerInterceptor implements HandlerInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(ControllerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        //判断请求类型,是 标准请求 or 静态资源 or 其他
        if (handler instanceof HandlerMethod) {
            //强转请求信息
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            //如果未找到对应控制层(即bean是BasicErrorController)
            if (handlerMethod.getBean() instanceof BasicErrorController) {
                //当做未知请求直接过滤
                sendError(response, HttpStatusEnum.NOT_FOUND);
                //返回
                return false;
            }
            //获取该请求上的登录注解
            LoginAuth loginAuth = handlerMethod.getMethod().getAnnotation(LoginAuth.class);
            //如果不为空,视为该接口需要登录认证
            if (loginAuth != null) {
                //获取token
                String token = request.getHeader(HttpConst.REQUEST_HEADERS_TOKEN);
                //如果不存在
                if (StringUtils.isBlank(token)) {
                    //过滤请求
                    sendError(response, HttpStatusEnum.UNAUTHORIZED);
                    //返回
                    return false;
                }
                //todo 未实现,简单设置用户信息
                LoginAuth.USER_ID.set(token);
            }
            //过
            return true;
        } else if (handler instanceof ResourceHttpRequestHandler) {
            //静态资源请求默认过
            return true;
        } else {
            //未知请求直接过滤
            sendError(response, HttpStatusEnum.NOT_FOUND);
            //返回
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //todo 清理资源,记录日志
    }

    /**
     * 根据http请求状态枚举,响应标准格式的错误消息
     *
     * @param response 请求响应
     * @param status   请求状态枚举
     * @throws IOException
     */
    private void sendError(HttpServletResponse response, HttpStatusEnum status) throws IOException {
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
