package org.rock.base.auth;

import org.rock.base.common.HttpResponse;
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
import java.lang.reflect.Method;

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
                HttpResponse.sendError(response, HttpStatusEnum.NOT_FOUND);
                //返回
                return false;
            }
            //尝试获取方法
            Method method = handlerMethod.getMethod();
            //todo 继续验证
            return true;
        } else if (handler instanceof ResourceHttpRequestHandler) {
            //静态资源请求默认过
            return true;
        } else {
            //未知请求直接过滤
            HttpResponse.sendError(response, HttpStatusEnum.NOT_FOUND);
            //返回
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }

}
