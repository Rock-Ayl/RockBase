package com.rock.base.common.api;

import com.rock.base.db.mongo.BaseMongoServiceImpl;
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
public class GlobalExHandler {

    private static final Logger LOG = LoggerFactory.getLogger(BaseMongoServiceImpl.class);

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Object exceptionHandler(Exception e) {
        LOG.error("exceptionHandler catch error:", e);
        //返回统一异常返回
        return JSONResponse.error(e);
    }

}
