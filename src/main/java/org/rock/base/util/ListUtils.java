package org.rock.base.util;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * List 额外工具包
 *
 * @Author ayl
 * @Date 2022-3-11
 */
public class ListUtils {

    /**
     * 根据逗号拆分str并过滤掉里面的空值,返回列表
     * 注意：为了可以做add操作,采用了初始化arrayList的结果
     * eg:
     * 1,2,3,,,,,,9,,,, => ["1","2","3","9"]
     *
     * @param str 1,2,3,4,5
     * @return ["1","2","3"]
     */
    public static List<String> toList(String str) {
        //初始化一个
        List<String> result = new ArrayList<>();
        //判空
        if (StringUtils.isNotBlank(str)) {
            //拆分
            String[] arr = str.split(",");
            //循环
            for (String s : arr) {
                //判空
                if (StringUtils.isNotBlank(s)) {
                    //组装
                    result.add(s);
                }
            }
        }
        //默认
        return result;
    }

    /**
     * 将list 转化为 string
     * eg: ["test", "test2"] - > test,test2
     *
     * @param list
     * @return
     */
    public static String toString(List<String> list) {
        //判空
        if (CollectionUtils.isNotEmpty(list)) {
            //str
            StringBuffer str = new StringBuffer();
            //循环
            for (String s : list) {
                //判空
                if (StringUtils.isNotBlank(s)) {
                    //组装
                    str.append(s + ",");
                }
            }
            //删除最后一个,并返回
            return str.deleteCharAt(str.length() - 1).toString();
        }
        //默认
        return "";
    }

}
