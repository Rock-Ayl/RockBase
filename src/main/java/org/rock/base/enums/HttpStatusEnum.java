package org.rock.base.enums;

import lombok.Getter;

/**
 * 常用http状态码枚举(需要就慢慢维护,没必要维护太多不常用的)
 * 参考地址:https://www.runoob.com/servlet/servlet-http-status-codes.html
 *
 * @Author ayl
 * @Date 2022-03-22
 */
@Getter
public enum HttpStatusEnum {

    NONE(0, "未知", "不存在或不识别的状态码"),
    OK(200, "OK", "请求成功"),
    UNAUTHORIZED(401, "Unauthorized", "请求需要用户信息或授权信息"),
    NOT_FOUND(404, "Not Found", "无法找到该请求的内容"),

    ;

    //代码
    private Integer code;
    //消息
    private String message;
    //描述
    private String desc;

    HttpStatusEnum(Integer code, String message, String desc) {
        this.code = code;
        this.message = message;
        this.desc = desc;
    }

    /**
     * 根据 code 解析出对应枚举
     *
     * @param code
     * @return
     */
    public static HttpStatusEnum parse(Integer code) {
        //判空
        if (code != null) {
            //循环
            for (HttpStatusEnum object : HttpStatusEnum.values()) {
                //如果一致
                if (object.getCode().equals(code)) {
                    //返回
                    return object;
                }
            }
        }
        //默认
        return NONE;
    }

}
