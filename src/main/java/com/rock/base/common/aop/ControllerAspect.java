package com.rock.base.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 控制层 aop
 *
 * @Author ayl
 * @Date 2024-12-29
 */
@Component
//切面注解,指定改类为切面类
@Aspect
public class ControllerAspect {

    private static final Logger LOG = LoggerFactory.getLogger(ControllerAspect.class);

    /**
     * 控制层指针
     */
    @Pointcut("execution(* com.rock.base.controller.*.*(..))")
    public void controllerPointcut() {
    }

    //前置通知
    @Before("controllerPointcut()")
    public void doBefore() {
        LOG.info("ControllerAspect do before...");
    }

    //后置通知
    @After("controllerPointcut()")
    public void doAfter() {
        LOG.info("ControllerAspect do after...");
    }

    //环绕通知
    @Around("controllerPointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        LOG.info("ControllerAspect do around before...");
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Exception e) {
            LOG.error("do around 执行目标函数, 内部发生异常");
        }
        LOG.info("ControllerAspect do around after...");
        return result;
    }

    //返回后通知
    @AfterReturning("controllerPointcut()")
    public void doAfterReturning() {
        LOG.info("ControllerAspect do AfterReturning...");
    }

    //抛出异常后通知
    @AfterThrowing("controllerPointcut()")
    public void doAfterThrowing() {
        LOG.info("ControllerAspect do AfterThrowing...");
    }

}