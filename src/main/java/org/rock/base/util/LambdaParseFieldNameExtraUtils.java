package org.rock.base.util;

import org.apache.ibatis.reflection.property.PropertyNamer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Function;

/**
 * 根据 Lambda表达式 获取名称 扩展工具包
 *
 * @Author ayl
 * @Date 2024-01-30
 */
public class LambdaParseFieldNameExtraUtils {

    private static final Logger LOG = LoggerFactory.getLogger(LambdaParseFieldNameExtraUtils.class);

    @FunctionalInterface
    public interface MFunction<T, R> extends Function<T, R>, Serializable {

    }

    /**
     * 获取字段名称实现
     *
     * @param serializedLambda 函数式接口的Lambda表达式
     * @return 字段名
     */
    private static String getColumn(SerializedLambda serializedLambda) {
        //获取字段名称实现
        return PropertyNamer.methodToProperty(serializedLambda.getImplMethodName());
    }

    /**
     * 获取到序列化SerializedLambda
     *
     * @param func 方法
     * @param <T>  泛型T
     * @param <R>  泛型R
     * @return SerializedLambda
     */
    private static <T, R> SerializedLambda getSerializedLambda(Function<T, R> func) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 直接调用writeReplace
        Method writeReplace = func.getClass().getDeclaredMethod("writeReplace");
        writeReplace.setAccessible(true);
        //反射调用
        Object sl = writeReplace.invoke(func);
        return (java.lang.invoke.SerializedLambda) sl;
    }

    /**
     * 根据Lambda表达式,获取 字段 名称
     *
     * @param func 函数式接口，例：User::getId()
     * @param <T>  泛型T
     * @param <R>  泛型R
     * @return 字段名
     */
    public static <T, R> String getColumn(MFunction<T, R> func) {
        try {
            //实现
            return getColumn(getSerializedLambda(func));
        } catch (Exception e) {
            LOG.error("LambdaParseFieldNameExtraUtils getColumn error", e);
            throw new RuntimeException("解析实体字段名称报错");
        }
    }

    /**
     * 根据Lambda表达式,获取 方法 名称
     *
     * @param func 函数式接口
     * @param <T>  泛型T
     * @param <T>  泛型R
     * @return 方法名称
     */
    public static <T, R> String getMethodName(MFunction<T, R> func) {
        try {
            //实现
            return getSerializedLambda(func).getImplMethodName();
        } catch (Exception e) {
            LOG.error("LambdaParseFieldNameExtraUtils getMethodName error", e);
            throw new RuntimeException("解析实体方法名称报错");
        }
    }

}