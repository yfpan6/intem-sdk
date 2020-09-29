package com.intemanage.sdk.utils;

import com.intemanage.sdk.commons.CodedMessage;
import com.intemanage.sdk.exception.AssertException;
import com.intemanage.sdk.exception.CodedException;

import java.util.Collection;
import java.util.Map;

/**
 * @Author panyunfeng
 * @Date 2019/12/18
 */
public class Asserts {

    public static void isTrue(Boolean bool) {
        if (bool == null || !bool) {
            throw AssertException.of("the result is not true.");
        }
    }

    public static void isTrue(Boolean bool, String message) {
        if (bool == null || !bool) {
            throw AssertException.of(message);
        }
    }

    public static void isTrue(Boolean bool, CodedMessage codedMessage) {
        if (bool == null || !bool) {
            throw AssertException.of(codedMessage);
        }
    }

    public static void isFalse(Boolean bool) {
        if (bool == null || bool) {
            throw AssertException.of("the result is not false.");
        }
    }

    public static void isFalse(Boolean bool, String message) {
        if (bool == null || bool) {
            throw AssertException.of(message);
        }
    }

    public static void isFalse(Boolean bool, CodedMessage codedMessage) {
        if (bool == null || bool) {
            throw AssertException.of(codedMessage);
        }
    }

    public static void isFalse(Boolean bool, CodedException exception) {
        if (bool == null || bool) {
            throw exception;
        }
    }

    public static void isNull(Object value) {
        if (value != null) {
            throw AssertException.of("the value is not null.");
        }
    }

    public static void isNull(Object value, String message) {
        if (value != null) {
            throw AssertException.of(message);
        }
    }

    public static void isNull(Object value, CodedMessage codedMessage) {
        if (value != null) {
            throw AssertException.of(codedMessage);
        }
    }

    public static void isNull(Object value, CodedException exception) {
        if (value != null) {
            throw exception;
        }
    }

    public static  void isNotNull(Object value) {
        if (value == null) {
            throw AssertException.of("the value is null.");
        }
    }

    public static  void isNotNull(Object value, String message) {
        if (value == null) {
            throw AssertException.of(message);
        }
    }

    public static void isNotNull(Object value, CodedMessage codedMessage) {
        if (value == null) {
            throw AssertException.of(codedMessage);
        }
    }

    public static void isNotNull(Object value, CodedException exception) {
        if (value == null) {
            throw exception;
        }
    }

    public static void isEmpty(Collection<?> collection) {
        if (collection != null && !collection.isEmpty()) {
            throw AssertException.of("the collection is not empty.");
        }
    }

    public static void isEmpty(Collection<?> collection, String message) {
        if (collection != null && !collection.isEmpty()) {
            throw AssertException.of(message);
        }
    }

    public static void isEmpty(Collection<?> collection, CodedMessage codedMessage) {
        if (collection != null && !collection.isEmpty()) {
            throw AssertException.of(codedMessage);
        }
    }

    public static void isEmpty(Collection<?> collection, CodedException exception) {
        if (collection != null && !collection.isEmpty()) {
            throw exception;
        }
    }

    public static void isNotEmpty(Collection<?> collection) {
        if (collection == null || collection.isEmpty()) {
            throw AssertException.of("the collection is empty.");
        }
    }

    public static void isNotEmpty(Collection<?> collection, String message) {
        if (collection == null || collection.isEmpty()) {
            throw AssertException.of(message);
        }
    }

    public static void isNotEmpty(Collection<?> collection, CodedMessage codedMessage) {
        if (collection == null || collection.isEmpty()) {
            throw AssertException.of(codedMessage);
        }
    }

    public static void isNotEmpty(Collection<?> collection, CodedException exception) {
        if (collection == null || collection.isEmpty()) {
            throw exception;
        }
    }

    public static void isEmpty(Map<?, ?> map) {
        if  (map != null && !map.isEmpty()) {
            throw AssertException.of("the map is not empty.");
        }
    }

    public static void isEmpty(Map<?, ?> map, String message) {
        if  (map != null && !map.isEmpty()) {
            throw AssertException.of(message);
        }
    }

    public static void isEmpty(Map<?, ?> map, CodedMessage codedMessage) {
        if (map != null && !map.isEmpty()) {
            throw AssertException.of(codedMessage);
        }
    }

    public static void isEmpty(Map<?, ?> map, CodedException exception) {
        if (map != null && !map.isEmpty()) {
            throw exception;
        }
    }

    public static void isNotEmpty(Map<?, ?> map) {
        if (map == null || map.isEmpty()) {
            throw AssertException.of("the map is empty.");
        }
    }

    public static void isNotEmpty(Map<?, ?> map, String message) {
        if (map == null || map.isEmpty()) {
            throw AssertException.of(message);
        }
    }

    public static void isNotEmpty(Map<?, ?> map, CodedMessage codedMessage) {
        if (map == null || map.isEmpty()) {
            throw AssertException.of(codedMessage);
        }
    }

    public static void isNotEmpty(Map<?, ?> map, CodedException exception) {
        if (map == null || map.isEmpty()) {
            throw exception;
        }
    }

    public static void isEmpty(Object[] array) {
        if (array != null && array.length > 0) {
            throw AssertException.of("the array is not empty.");
        }
    }

    public static void isEmpty(Object[] array, String message) {
        if (array != null && array.length > 0) {
            throw AssertException.of(message);
        }
    }

    public static void isEmpty(Object[] array, CodedMessage codedMessage) {
        if (array != null && array.length > 0) {
            throw AssertException.of(codedMessage);
        }
    }

    public static void isEmpty(Object[] array, CodedException exception) {
        if (array != null && array.length > 0) {
            throw exception;
        }
    }

    public static void isNotEmpty(Object[] array) {
        if (array == null || array.length == 0) {
            throw AssertException.of("the array is empty");
        }
    }

    public static void isNotEmpty(Object[] array, String message) {
        if (array == null || array.length == 0) {
            throw AssertException.of(message);
        }
    }

    public static void isNotEmpty(Object[] array, CodedMessage codedMessage) {
        if (array == null || array.length == 0) {
            throw AssertException.of(codedMessage);
        }
    }

    public static void isNotEmpty(Object[] array, CodedException exception) {
        if (array == null || array.length == 0) {
            throw exception;
        }
    }

}
