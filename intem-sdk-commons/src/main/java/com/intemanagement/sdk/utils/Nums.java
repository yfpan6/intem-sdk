package com.intemanagement.sdk.utils;


import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Random;

/**
 * @Author panyunfeng04
 * @Date 2019-11-05
 */
@Slf4j
public class Nums {

    public static <T extends Number> T ifNull(T value, T elseValue) {
        if (value == null) {
            return elseValue;
        }
        return value;
    }

    public static <T extends Number> T min(T... values) {
        if (values.length == 0) {
            return null;
        }

        T min = null;
        T t;
        for (int i = 0; i < values.length; i++) {
            t = values[i];
            if (t == null) {
                continue;
            }

            if (min == null || t.doubleValue() < min.doubleValue()) {
                min = t;
            }
        }
        return min;
    }

    public static <T extends Number> T max(T... values) {
        if (values.length == 0) {
            return null;
        }

        T max = null;
        T t;
        for (int i = 1; i < values.length; i++) {
            t = values[i];
           if (t == null) {
               continue;
           }

           if (max == null || t.doubleValue() > max.doubleValue()) {
               max = t;
           }
        }
        return max;
    }

    public static Integer parseInt(String text, Integer defaultValue) {
        if (Strings.isBlank(text)) {
            return defaultValue;
        }

        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            log.error("parseInt error for {}", text, e);
        }

        return defaultValue;
    }

    public static Long parseLong(String text, Long defaultValue) {
        if (Strings.isBlank(text)) {
            return defaultValue;
        }

        try {
            return Long.parseLong(text);
        } catch (NumberFormatException e) {
            log.error("parseLong error for {}", text, e);
        }

        return defaultValue;
    }

    public static Byte parseByte(String text, Byte defaultValue) {
        if (Strings.isBlank(text)) {
            return defaultValue;
        }

        try {
            return Byte.parseByte(text);
        } catch (NumberFormatException e) {
            log.error("parseByte error for {}", text, e);
        }

        return defaultValue;
    }

    public static Double parseDouble(String text, Double defaultValue) {
        if (Strings.isBlank(text)) {
            return defaultValue;
        }

        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            log.error("parseDouble error for {}", text, e);
        }

        return defaultValue;
    }

    public static boolean gt(Number value, Number compareValue) {
        return value != null && value.doubleValue() > compareValue.doubleValue();
    }

    public static boolean gte(Number value, Number compareValue) {
        return value != null && value.doubleValue() >= compareValue.doubleValue();
    }

    public static boolean lt(Number value, Number compareValue) {
        return value != null && value.doubleValue() < compareValue.doubleValue();
    }

    public static boolean lte(Number value, Number compareValue) {
        return value != null && value.doubleValue() <= compareValue.doubleValue();
    }

    public static boolean between(Number value, Number from, Number to) {
        return value != null && lte(from, value) && gte(to, value);
    }

    public static boolean equal(Number value, Number compareValue) {
        return value != null && value.doubleValue() == compareValue.doubleValue();
    }

    public static boolean notEqual(Number value, Number compareValue) {
        return !equal(value, compareValue);
    }

    public static boolean in(Number value, Collection<Number> inList) {
        if (value == null || Collections.isEmpty(inList)) {
            return false;
        }
        for (Number number : inList) {
           if (equal(number, value)) {
               return true;
           }
        }
        return false;
    }

    public static boolean notIn(Number value, Collection<Number> notInList) {
        return !in(value, notInList);
    }

    /**
     * 随机N位数字
     * @param n
     * @return
     */
    public static int randomNDigitNum(int n) {
        Random random = new Random();
        return (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;
    }

}
