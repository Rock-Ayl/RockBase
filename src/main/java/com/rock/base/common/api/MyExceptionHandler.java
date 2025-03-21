package com.rock.base.common.api;

import com.rock.base.common.auth.ClearLoginSessionExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 控制层全局异常捕获
 *
 * @Author ayl
 * @Date 2023-03-29
 */
@ControllerAdvice
public class MyExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(MyExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(value = Throwable.class)
    public Object exceptionHandler(Throwable e) {
        try {
            LOG.error("exceptionHandler catch error:", e);
            //返回统一异常返回
            return JSONResponse.error(e).toString();
        } finally {
            //清理session
            ClearLoginSessionExecutor.clear();
        }
    }

}
