package com.intemanagement.sdk.utils;

import java.util.Collection;
import java.util.Map;

/**
 * @Author panyunfeng
 * @Date 2019/12/19
 */
public class Collections {

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return collection != null && !collection.isEmpty();
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return map != null && !map.isEmpty();
    }

    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isNotEmpty(Object[] array) {
        return array != null && array.length > 0;
    }

    public static boolean contains(Object value, Collection<?> collection) {
        return collection != null && collection.contains(value);
    }

    public static boolean contains(Object value, Object[] array) {
        if (array == null || array.length == 0) {
            return false;
        }
        int len = array.length;
        if (value == null) {
            for (int i = 0; i < len; i++) {
                if (array[i] == null) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < len; i++) {
                if (value.equals(array[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean notContains(Object value, Collection<?> collection) {
        return !contains(value, collection);
    }
}
