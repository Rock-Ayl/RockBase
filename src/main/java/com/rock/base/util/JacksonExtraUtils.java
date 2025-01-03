package com.rock.base.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.rock.base.pojo.mdo.UserDO;
import org.springframework.boot.json.JsonParseException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Jackson 扩展工具包
 *
 * @Author ayl
 * @Date 2024-01-25
 */
public class JacksonExtraUtils {

    //默认 ObjectMapper 单例
    private final static ObjectMapper DEFAULT_OBJECT_MAPPER;

    static {

        //初始化 默认 ObjectMapper
        DEFAULT_OBJECT_MAPPER = new ObjectMapper();

        /**
         * {@link String} -> {@link Object} 配置
         */

        //如果string有某个key但实体没有,正常会抛异常,但这么配置会忽略
        DEFAULT_OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //如果没有任何属性,正常会抛出,但这么配置会忽略
        DEFAULT_OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        /**
         * {@link Object} -> {@link String} 配置
         */

        //如果对象key的值为空,正常会输出null,但这么配置会忽略
        DEFAULT_OBJECT_MAPPER.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);

    }

    /**
     * 获取 默认 ObjectMapper
     *
     * @return
     */
    private static ObjectMapper defaultMapperCreator() {
        //直接返回
        return DEFAULT_OBJECT_MAPPER;
    }

    /**
     * jackson 统一实现
     *
     * @param parser
     * @param <T>
     * @return
     */
    public static <T> T tryParse(Callable<T> parser) {
        try {
            return parser.call();
        } catch (Exception e) {
            //如果是 jackson 的异常
            if (JacksonException.class.isAssignableFrom(e.getClass())) {
                //抛出spring的
                throw new JsonParseException(e);
            }
            //默认
            throw new IllegalStateException(e);
        }
    }

    /**
     * 对象转String {@link Object} -> {@link String}
     *
     * @param object 对象
     * @return
     */
    public static String toJSONString(Object object) {
        //使用默认 mapper 实现
        return toJSONString(defaultMapperCreator(), object);
    }

    /**
     * 对象转String {@link Object} -> {@link String}
     *
     * @param objectMapper 对应 mapper
     * @param object       对象
     * @return
     */
    public static String toJSONString(ObjectMapper objectMapper, Object object) {
        //判空
        if (object == null || objectMapper == null) {
            //过
            return null;
        }
        //如果就是string
        if (object instanceof String) {
            //直接返回,无需再转
            return object.toString();
        }
        //实现
        return JacksonExtraUtils.tryParse(() -> objectMapper.writeValueAsString(object));
    }

    /**
     * 深克隆单个对象,也可以将一个对象转化为另一个对象(当然,结构得基本一致或继承关系)
     *
     * @param object       源对象,比如父对象,不能是数组等结构
     * @param toJavaObject 目标对象
     * @return
     */
    public static <T> T deepClone(Object object, Class<T> toJavaObject) {
        //判空
        if (object == null) {
            //过
            return null;
        }
        //先转为string,再转为对应实体
        return JacksonExtraUtils.tryParse(() -> JacksonExtraUtils.defaultMapperCreator().readValue(toJSONString(object), toJavaObject));
    }

    /**
     * 深克隆单个对象,也可以将一个对象转化为另一个对象(当然,结构得基本一致或继承关系)
     *
     * @param object       源对象,比如父对象,不能是数组等结构
     * @param toJavaObject 目标对象
     * @return
     */
    public static <T> List<T> deepCloneList(Object object, Class<T> toJavaObject) {
        //判空
        if (object == null) {
            //过
            return null;
        }
        //先转为string,再转为对应实体
        return JacksonExtraUtils.tryParse(() -> JacksonExtraUtils.defaultMapperCreator().readValue(toJSONString(object), defaultMapperCreator().getTypeFactory().constructCollectionType(ArrayList.class, toJavaObject)));
    }

    /**
     * 使用demo
     *
     * @param args
     */
    public static void main(String[] args) {

        //初始化一个实体
        UserDO userDO = new UserDO();
        userDO.setName("123");
        userDO.setEmail("19482131@qq.com");
        userDO.setPhone("1238791791279");
        userDO.setRemark("我是备注");
        userDO.setCreateDate(new Date());

        /**
         * 实体 -> string
         */

        //情况1. 对象转string
        String jsonStr = toJSONString(userDO);
        //情况2. string转实体
        String jsonListStr = toJSONString(Collections.singletonList(userDO));
        //清空3. 特殊要求的情况转string(时间格式)
        String jsonStr2 = toJSONString(
                new ObjectMapper().setDateFormat(new SimpleDateFormat("yyyy-MM-dd")),
                userDO);

        /**
         * string/object -> 实体
         */

        //转化为实体
        UserDO user1 = deepClone(userDO, UserDO.class);
        UserDO user2 = deepClone(jsonStr, UserDO.class);

        //转化为实体列表
        List<UserDO> userList1 = deepCloneList(jsonListStr, UserDO.class);
        List<UserDO> userList2 = deepCloneList(Collections.singletonList(userDO), UserDO.class);

        System.out.println();

    }

}