package org.rock.base.util;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.MapUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Map 补充工具包
 *
 * @Author ayl
 * @Date 2023-06-13
 */
public class MapExtraUtils {

    /**
     * 将 map 按照 size 分组,性能一般,凑合用,总比没有强
     *
     * @param map  要分组的map
     * @param size 分数的数量
     * @return
     */
    public static <K, V> List<Map<K, V>> partition(Map<K, V> map, int size) {
        //判空 或 数字太小
        if (MapUtils.isEmpty(map) || size < 1) {
            //过
            return new ArrayList<>();
        }
        //用list的分组工具包分组,并转化为map发N会
        return ListUtils.partition(new ArrayList<>(map.entrySet()), size)
                .stream()
                .map(p -> p
                        .stream()
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v2))
                ).collect(Collectors.toList());
    }

}
