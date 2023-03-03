package org.rock.base.util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 数组额外扩展包
 *
 * @Author ayl
 * @Date 2023-03-03
 */
public class ArrayExtraUtils {

    //分组
    public Map<Integer, List<Integer>> groupList(int[] nums) {
        //返回
        return Arrays.stream(nums)
                //装箱
                .boxed()
                //分组统计数量
                .collect(Collectors.groupingBy(p -> p));
    }

    //分组并统计数量
    public Map<Integer, Long> groupCount(int[] nums) {
        //返回
        return Arrays.stream(nums)
                //装箱
                .boxed()
                //分组统计数量
                .collect(Collectors.groupingBy(p -> p, Collectors.counting()));
    }

}
