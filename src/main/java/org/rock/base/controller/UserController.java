package org.rock.base.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.Setter;
import org.rock.base.auth.LoginAuth;
import org.rock.base.common.JSONResponse;
import org.rock.base.constant.HttpConst;
import org.rock.base.pojo.mdo.UserDO;
import org.rock.base.serivce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户控制层
 *
 * @Author ayl
 * @Date 2022-03-23
 */
@Api(tags = "用户模块")
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取用户列表")
    @GetMapping(value = "/listUser", produces = HttpConst.RESPONSE_HEADERS_CONTENT_TYPE_APPLICATION_JSON)
    public String listUser() {
        //查询所有
        List<UserDO> userDOList = userService.listAll();
        //返回
        return JSONResponse.success().put("result", userDOList).toString();
    }

    /**
     * 登录参数
     */
    @Setter
    @Getter
    public static class LoginParam {

        @ApiModelProperty("手机号")
        private String phone;

        @ApiModelProperty("密码")
        private String pwd;

    }

    @ApiOperation(value = "手机号登录")
    @PostMapping(value = "/loginByPhone", produces = HttpConst.RESPONSE_HEADERS_CONTENT_TYPE_APPLICATION_JSON)
    public String loginByPhone(@RequestBody LoginParam param) {
        //实现,并返回token
        String token = userService.loginByPhone(param.getPhone(), param.getPwd());
        //返回
        return JSONResponse.success().put("token", token).toString();
    }

    @LoginAuth
    @ApiOperation(value = "新增用户")
    @PostMapping(value = "/addUser", produces = HttpConst.RESPONSE_HEADERS_CONTENT_TYPE_APPLICATION_JSON)
    public String addUser(@RequestBody UserDO userDO) {
        //实现
        userService.addUser(userDO);
        //返回
        return JSONResponse.success().put("result", userDO).toString();
    }

}
