package com.rock.base.common.auth;

import com.rock.base.constant.HttpConst;
import com.rock.base.constant.RedisKey;
import com.rock.base.db.redis.BaseRedisService;
import com.rock.base.enums.HttpStatusEnum;
import com.rock.base.pojo.mdo.UserDO;
import com.rock.base.util.JacksonExtraUtils;
import com.rock.base.util.UserExtraUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * 请求拦截器实现
 *
 * @Author ayl
 * @Date 2022-03-21
 */
@Component
public class ControllerInterceptor implements HandlerInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(ControllerInterceptor.class);

    @Autowired
    private BaseRedisService baseRedisService;

    /**
     * 在 Controller 方法执行之前被调用
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        /**
         * 前置
         */

        //清理session
        ClearLoginSessionExecutor.clear();

        //todo 记录请求信息日志

        /**
         * 决定是否继续执行后续流程
         */

        //如果是 静态资源请求
        if (handler instanceof ResourceHttpRequestHandler) {
            //静态资源请求默认过
            return true;
        }
        //如果请求不是 标准请求
        if (handler instanceof HandlerMethod == false) {
            //未知请求直接过滤
            sendError(response, HttpStatusEnum.NOT_FOUND);
            //不过
            return false;
        }
        //强转请求信息
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        //如果未找到对应控制层(即bean是BasicErrorController)
        if (handlerMethod.getBean() instanceof BasicErrorController) {
            //当做未知请求直接过滤
            sendError(response, HttpStatusEnum.NOT_FOUND);
            //返回
            return false;
        }

        /**
         * 登录信息
         */

        //获取该请求上的登录注解
        LoginAuth loginAuth = handlerMethod.getMethod().getAnnotation(LoginAuth.class);
        //如果不存在注解,可直接登录
        if (loginAuth == null) {
            //通过
            return true;
        }
        //获取token
        String token = request.getHeader(HttpConst.REQUEST_HEADERS_TOKEN);
        //获取用户信息
        UserDO userDO = getUserFromCache(token);
        //如果拿不到用户信息
        if (userDO == null) {
            //未知请求直接过滤
            sendError(response, HttpStatusEnum.UNAUTHORIZED);
            //不过
            return false;
        }
        //记录用户信息
        LoginAuth.USER.set(userDO);

        /**
         * 默认通过请求
         */

        //通过
        return true;
    }

    /**
     * 从 Redis 缓存 拿 用户信息
     *
     * @param token Token
     * @return
     */
    private UserDO getUserFromCache(String token) {
        //判空
        if (StringUtils.isBlank(token)) {
            //过
            return null;
        }
        //组装redis key
        String redisUserTokenKey = RedisKey.USER_LOGIN_AUTH_SET + token;
        //获取用户信息
        String userInfo = baseRedisService.getString(redisUserTokenKey);
        //如果存在
        if (StringUtils.isBlank(userInfo)) {
            //过
            return null;
        }
        //获取用户信息
        UserDO userDO = JacksonExtraUtils.deepClone(userInfo, UserDO.class);
        //用户实体脱敏
        UserExtraUtils.desensitization(userDO);
        //返回
        return userDO;
    }

    /**
     * 在 Controller 方法执行之后，视图（View）渲染之前被调用
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

        //todo 记录 Controller 的执行结果日志

        /**
         * 清理登录信息
         */

        //清理session
        ClearLoginSessionExecutor.clear();

    }

    /**
     * 在整个请求处理完成之后被调用（视图渲染完成后）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        //todo 清理资源,记录日志

    }

    /**
     * 错误请求响应实体
     */

    @Setter
    @Getter
    public static class ErrorResponseBody {

        @ApiModelProperty("描述")
        private String desc;

        @ApiModelProperty("状态")
        private Integer status;

        @ApiModelProperty("消息")
        private String message;

        @ApiModelProperty("时间")
        private Date timestamp;

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
        //初始化错误消息体
        ErrorResponseBody errorResponseBody = new ErrorResponseBody();
        //组装信息
        errorResponseBody.setDesc(status.getDesc());
        errorResponseBody.setStatus(status.getCode());
        errorResponseBody.setMessage(status.getMessage());
        errorResponseBody.setTimestamp(new Date());
        //写入
        response.getWriter().write(JacksonExtraUtils.toJSONString(errorResponseBody));
    }

}
