package com.course.commons.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * List转换类
 *
 * @author pguangming@163.com
 * @version 1.0
 * @since 2020/11/23 12:35
 **/
@Slf4j
public class CollectUtils {

    private CollectUtils() {
        throw new IllegalStateException("Utility class");
    }
    /**
     * List转换
     *
     * @param obj   传入对象
     * @param clazz 转换类型
     * @return java.util.List<T>
     * @since 2020/11/23 12:37
     */
    public static <T> List<T> castList(Object obj, Class<T> clazz) {
        List<T> result = new LinkedList<>();
        if (obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return Collections.emptyList();
    }
}