package com.rock.base.controller;

import io.swagger.annotations.Api;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 登录控制层
 *
 * @Author ayl
 * @Date 2025-03-13
 */
@Api(tags = "登录模块")
@RestController
@RequestMapping(value = "/login")
public class LoginController {

    /**
     * 统一的 OAuth2 回调地址
     *
     * @param user
     * @return
     */
    @GetMapping(value = "/commonOAuth2")
    public String commonOAuth2(@AuthenticationPrincipal OAuth2User user) {
        //获取属性
        Map<String, Object> attributes = user.getAttributes();

        //todo 判断多平台

        //返回名称
        return String.format("欢迎你:[%s]", attributes.get("name"));
    }

}