package org.rock.base.auth;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 登录认证注解,控制层方法加上该注解,需要登录验证
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginAuth {
}
