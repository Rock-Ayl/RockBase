package org.rock.base.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.rock.base.pojo.mdo.UserDO;
import org.springframework.boot.json.JsonParseException;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.Callable;

/**
 * jackson 额外工具包
 *
 * @Author ayl
 * @Date 2024-01-25
 */
public class JacksonExtraUtils {

    //单例
    private final static ObjectMapper OBJECT_MAPPER;

    static {
        //初始化 mapper
        OBJECT_MAPPER = new ObjectMapper();
    }

    /**
     * 获取基本mapper
     *
     * @return
     */
    private static ObjectMapper getObjectMapper() {
        //直接返回mapper
        return OBJECT_MAPPER;
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
        } catch (Exception ex) {
            //如果是 jackson 的异常
            if (JacksonException.class.isAssignableFrom(ex.getClass())) {
                //抛出spring的
                throw new JsonParseException(ex);
            }
            //默认
            throw new IllegalStateException(ex);
        }
    }

    /**
     * 对象转String {@link Object} -> {@link String}
     *
     * @param object 对象
     * @return
     */
    public static String toJSONString(Object object) {
        //判空
        if (object == null) {
            //过
            return null;
        }
        //如果就是string
        if (object instanceof String) {
            //直接返回,无需再转
            return object.toString();
        }
        //实现
        return JacksonExtraUtils.tryParse(() -> JacksonExtraUtils.getObjectMapper().writeValueAsString(object));
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
        return JacksonExtraUtils.tryParse(() -> JacksonExtraUtils.getObjectMapper().readValue(toJSONString(object), toJavaObject));
    }

    public static void main(String[] args) {

        UserDO userDO = new UserDO();
        userDO.setName("123");
        userDO.setPwd("cojdoiiaosjo");
        userDO.setEmail("19482131@qq.com");
        userDO.setPhone("1238791791279");
        userDO.setRemark("我是备注");
        userDO.setCreateDate(new Date());
        userDO.setUpdateDate(new Date());

        /**
         * 实体 -> string
         */

        String jsonStr = toJSONString(userDO);
        String jsonListStr = toJSONString(Arrays.asList(userDO));
        System.out.println();

        /**
         * string -> 实体
         */

        //转化为实体
        UserDO user2 = deepClone(userDO, UserDO.class);
        UserDO user3 = deepClone(jsonStr, UserDO.class);

        System.out.println();

        /**
         * 特殊处理
         */

        // 2.有特殊要求的json
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        String json = JacksonExtraUtils.tryParse(() -> objectMapper.writeValueAsString(new UserDO()));
        System.out.println();

    }

}