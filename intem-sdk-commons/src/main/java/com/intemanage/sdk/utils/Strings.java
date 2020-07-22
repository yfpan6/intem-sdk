package com.intemanage.sdk.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author panyunfeng04
 * @Date 2019-11-01
 */
public class Strings extends StringUtils {
    public static final String QUOTE = "'";
    public static final String DOUBLE_QUOTE = "\"";
    public static final String PERIOD = ".";
    public static final String DOT = ".";
    public static final String QUESTION = "?";
    public static final String COMMA = ",";
    public static final String SEMICOLON = ";";
    public static final String COLON = ":";
    public static final String SLASH = "/";
    public static final String BACKSLASH = "\\";
    public static final String BAR = "|";
    public static final String TILDE = "~";
    public static final String EXCLAM = "!";
    public static final String AT = "@";
    // hash? pound
    public static final String SHARP = "#";
    public static final String DOLLAR = "$";
    public static final String PERCENT = "%";
    public static final String CARET = "^";
    public static final String AMPERSAND = "&";
    // star
    public static final String ASTERISK = "*";
    public static final String HYPHEN = "-";
    public static final String UNDERSCORE = "_";
    public static final String PLUS = "+";
    public static final String EQUAL = "=";

    public static <T extends CharSequence> T ifNull(T value, T elseValue) {
        if (value == null) {
            return elseValue;
        }

        return value;
    }

    /**
     * 连接成串
     * 从集合对象中取出字段，并用分隔符连接
     *
     * @param collection    bean对象集合
     * @param mapFunction   取值函数
     * @param delimiter
     * @return
     */
    public static <T> String joinWith(Collection<T> collection, Function<T, String> mapFunction, String delimiter) {
        return String.valueOf(collection.stream().map(mapFunction)
                .collect(Collectors.joining(delimiter)));
    }

    /**
     * 蛇型转驼峰
     * snake_to_camel -> SnakeToCamel
     * @param text
     * @return
     */
    public static String snakeToCamel(String text) {
        if (isBlank(text)) {
            return text;
        }

        return null;
    }

    /**
     * 驼峰转蛇形
     * SnakeToCamel -> snake_to_camel
     * snakeToCamel -> snake_to_camel
     * @param text
     * @return
     */
    public static String camelToSnake(String text) {
        if (isBlank(text)) {
            return text;
        }

        return null;
    }

    /**
     * 首字母大写
     */
    public static String captainToUpperCase(String text) {
        if (isBlank(text)) {
            return text;
        }

        return null;
    }

    /**
     * 首字母小写
     */
    public static String captainToLowerCase(String text) {
        if (isBlank(text)) {
            return text.toLowerCase();
        }

        return null;
    }

    public static boolean isNum(String text) {
        return isIntegers(text) || isDecimal(text);
    }

    /**
     * 是整数： -n ~ +n
     * @param text
     * @return
     */
    public static boolean isIntegers(String text) {
        if (isBlank(text)) {
            return false;
        }

        for (char c : text.toCharArray()) {
            if (!Chars.isNum(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 小数： +xxx.xxx or -xxx.xxx
     * @param text
     * @return
     */
    public static boolean isDecimal(String text) {
        if (isBlank(text)) {
            return false;
        }

        String[] parts = text.split(DOT);
        if (parts.length != 2) {
            return false;
        }

        return isIntegers(parts[0]) && isIntegers(parts[1]);
    }

    public static boolean equal(String value, String compareValue) {
        if (value == null) {
            return compareValue == null;
        }

        return value.equals(compareValue);
    }

    public static boolean notEqual(String value, String compareValue) {
        return !equal(value, compareValue);
    }
}
