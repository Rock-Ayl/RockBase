package org.rock.base.db;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author ayl
 * @Date 2024-10-21
 */
public class TestJSON {

    /**
     * 登录参数
     */
    @Setter
    @Getter
    //反序列化过程中忽略JSON中存在但Java类中不存在的属性 and 忽略名为"pwd"的属性
    @JsonIgnoreProperties(ignoreUnknown = true, value = {"pwd"})
    public static class LoginParam {

        @ApiModelProperty("手机号")
        private String phone;

        @ApiModelProperty("密码")
        private String pwd;

    }

}
