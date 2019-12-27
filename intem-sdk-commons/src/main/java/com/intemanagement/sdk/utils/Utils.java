package com.intemanagement.sdk.utils;

/**
 * @Author panyunfeng04
 * @Date 2019-11-05
 */
public class Utils {

    public static <T> T ifNull(T value, T elseValue) {
        if (value == null) {
            return elseValue;
        }
        return value;
    }

    public static <T> T firstNotNull(T... ts) {
        if (ts.length == 0) {
            return null;
        }

        for (T t : ts) {
            if (t != null) {
                return t;
            }
        }

        return null;
    }

}
