package com.course.commons.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ArrayUtil;
import com.course.commons.exception.IgnoreException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * @date 2020-05-09
 * @Description: 校验
 */
public class Assert {

    private Assert() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 判断实体不能为空
     *
     * @param object  实体
     * @param message 错误消息
     */
    public static void notNull(@Nullable Object object, String message, Object... params) {
        if (object == null) {
            throw new IgnoreException(CharSequenceUtil.format(message, params));
        }
    }

    public static void isNull(Object object, String message, Object... params) {
        if (object != null) {
            throw new IgnoreException(CharSequenceUtil.format(message, params));
        }
    }

    public static void isFalse(boolean expression, String errorMsgTemplate, Object... params) throws IllegalArgumentException {
        if (expression) {
            throw new IgnoreException(CharSequenceUtil.format(errorMsgTemplate, params));
        }
    }

    public static <T extends CharSequence> T notBlank(T text, String errorMsgTemplate, Object... params) throws IllegalArgumentException {
        if (CharSequenceUtil.isBlank(text)) {
            throw new IgnoreException(CharSequenceUtil.format(errorMsgTemplate, params));
        }
        return text;
    }

    public static <T extends CharSequence> T notEmpty(T text, String errorMsgTemplate, Object... params) throws IllegalArgumentException {
        if (StringUtils.isEmpty(text)) {
            throw new IgnoreException(CharSequenceUtil.format(errorMsgTemplate, params));
        }
        return text;
    }

    public static Object[] notEmpty(Object[] array, String errorMsgTemplate, Object... params) throws IllegalArgumentException {
        if (ArrayUtil.isEmpty(array)) {
            throw new IgnoreException(CharSequenceUtil.format(errorMsgTemplate, params));
        }
        return array;
    }

    public static <T> Collection<T> notEmpty(Collection<T> collection, String errorMsgTemplate, Object... params) throws IllegalArgumentException {
        if (CollUtil.isEmpty(collection)) {
            throw new IgnoreException(CharSequenceUtil.format(errorMsgTemplate, params));
        }
        return collection;
    }

    public static <K, V> Map<K, V> notEmpty(Map<K, V> map, String errorMsgTemplate, Object... params) throws IllegalArgumentException {
        if (CollUtil.isEmpty(map)) {
            throw new IgnoreException(CharSequenceUtil.format(errorMsgTemplate, params));
        }
        return map;
    }


    public static void isTrue(boolean expression, String errorMsgTemplate, Object... params) {
        if (!expression) {
            throw new IgnoreException(CharSequenceUtil.format(errorMsgTemplate, params));
        }
    }

    public static void equals(Object o1, Object o2, String errorMsgTemplate, Object... params) {
        if (!Objects.equals(o1, o2)) {
            throw new IgnoreException(CharSequenceUtil.format(errorMsgTemplate, params));
        }
    }

    public static void notEquals(Object o1, Object o2, String errorMsgTemplate, Object... params) {
        if (Objects.equals(o1, o2)) {
            throw new IgnoreException(CharSequenceUtil.format(errorMsgTemplate, params));
        }
    }
}
