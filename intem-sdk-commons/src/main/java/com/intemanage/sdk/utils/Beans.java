package com.intemanage.sdk.utils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Beans {

    private static final Map<Class<?>, Class<?>> baseTypeMapping = new HashMap<>();
    static {
        baseTypeMapping.put(byte.class, Byte.class);
        baseTypeMapping.put(Byte.class, Byte.class);
        baseTypeMapping.put(short.class, Short.class);
        baseTypeMapping.put(Short.class, Short.class);
        baseTypeMapping.put(int.class, Integer.class);
        baseTypeMapping.put(Integer.class, Integer.class);
        baseTypeMapping.put(long.class, Long.class);
        baseTypeMapping.put(Long.class, Long.class);
        baseTypeMapping.put(float.class, Float.class);
        baseTypeMapping.put(Float.class, Float.class);
        baseTypeMapping.put(double.class, Double.class);
        baseTypeMapping.put(Double.class, Double.class);
        baseTypeMapping.put(char.class, Character.class);
        baseTypeMapping.put(Character.class, Character.class);
        baseTypeMapping.put(boolean.class, Boolean.class);
        baseTypeMapping.put(Boolean.class, Boolean.class);
    }

    public static boolean isSameType(Class<?> one, Class<?> two) {
        if (one == null || two == null) {
            return false;
        }

        if (one == two) {
            return true;
        }

        Class<?> baseOneType = baseTypeMapping.get(one);
        if (baseOneType != null) {
            Class<?> baseTwoType = baseTypeMapping.get(two);
            return baseTwoType != null && baseOneType == baseTwoType;
        }
        return false;
    }

    public static boolean isAAssignableFromB(Class<?> classA, Class<?> classB) {
        if (classA == null || classB == null) {
            return false;
        }

        Class<?> baseOneType = baseTypeMapping.get(classA);
        if (baseOneType != null) {
            Class<?> baseTwoType = baseTypeMapping.get(classB);
            return baseTwoType != null && baseOneType == baseTwoType;
        }

        return classA.isAssignableFrom(classB);
    }

    public static <F, T> T copyProps(F fromObject, T toObject) {
        if (fromObject == null || toObject == null) {
            return toObject;
        }

        return toObject;
    }

    public static <F, T> T mergeProps(F fromObject, T toObject) {
        if (fromObject == null || toObject == null) {
            return toObject;
        }

        return toObject;
    }

    public static Map<String, Method> listGets(Class<?> clazz) {
        clazz.getMethods();
        return null;
    }
}
