package com.intemanage.sdk.utils;

import com.intemanage.sdk.commons.CodedMessage;
import com.intemanage.sdk.exception.InvalidParamException;

import java.util.Collection;
import java.util.Map;

/**
 * @Author panyunfeng
 * @Date 2019/12/18
 */
public class Asserts {

    public static void isTrue(Boolean bool, String message) {
        if (bool == null || !bool) {
            throw InvalidParamException.of(message);
        }
    }

    public static void isTrue(Boolean bool, CodedMessage codedMessage) {
        if (bool == null || !bool) {
            throw InvalidParamException.of(codedMessage);
        }
    }

    public static void isFalse(Boolean bool, String message) {
        if (bool == null || bool) {
            throw InvalidParamException.of(message);
        }
    }

    public static void isFalse(Boolean bool, CodedMessage codedMessage) {
        if (bool == null || bool) {
            throw InvalidParamException.of(codedMessage);
        }
    }

    public static void isNull(Object value, String message) {
        if (value != null) {
            throw InvalidParamException.of(message);
        }
    }

    public static void isNull(Object value, CodedMessage codedMessage) {
        if (value != null) {
            throw InvalidParamException.of(codedMessage);
        }
    }

    public static  void isNotNull(Object value, String message) {
        if (value == null) {
            throw InvalidParamException.of(message);
        }
    }

    public static void isNotNull(Object value, CodedMessage codedMessage) {
        if (value == null) {
            throw InvalidParamException.of(codedMessage);
        }
    }

    public static void isEmpty(Collection<?> collection, String message) {
        if (collection != null && !collection.isEmpty()) {
            throw InvalidParamException.of(message);
        }
    }

    public static void isEmpty(Collection<?> collection, CodedMessage codedMessage) {
        if (collection != null && !collection.isEmpty()) {
            throw InvalidParamException.of(codedMessage);
        }
    }

    public static void isNotEmpty(Collection<?> collection, String message) {
        if (collection == null || collection.isEmpty()) {
            throw InvalidParamException.of(message);
        }
    }

    public static void isNotEmpty(Collection<?> collection, CodedMessage codedMessage) {
        if (collection == null || collection.isEmpty()) {
            throw InvalidParamException.of(codedMessage);
        }
    }

    public static void isEmpty(Map<?, ?> map, String message) {
        if  (map != null && !map.isEmpty()) {
            throw InvalidParamException.of(message);
        }
    }

    public static void isEmpty(Map<?, ?> map, CodedMessage codedMessage) {
        if (map != null && !map.isEmpty()) {
            throw InvalidParamException.of(codedMessage);
        }
    }

    public static void isNotEmpty(Map<?, ?> map, String message) {
        if (map == null || map.isEmpty()) {
            throw InvalidParamException.of(message);
        }
    }

    public static void isNotEmpty(Map<?, ?> map, CodedMessage codedMessage) {
        if (map == null || map.isEmpty()) {
            throw InvalidParamException.of(codedMessage);
        }
    }

    public static void isEmpty(Object[] array, String message) {
        if (array != null && array.length > 0) {
            throw InvalidParamException.of(message);
        }
    }

    public static void isEmpty(Object[] array, CodedMessage codedMessage) {
        if (array != null && array.length > 0) {
            throw InvalidParamException.of(codedMessage);
        }
    }

    public static void isNotEmpty(Object[] array, String message) {
        if (array == null || array.length == 0) {
            throw InvalidParamException.of(message);
        }
    }

    public static void isNotEmpty(Object[] array, CodedMessage codedMessage) {
        if (array == null || array.length == 0) {
            throw InvalidParamException.of(codedMessage);
        }
    }

}
