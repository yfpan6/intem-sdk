package com.intemanage.sdk.utils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Beans {

    // 缓存基础类型转换关系
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

    /**
     * 判断两个类是否是相同类型（平级比较，非父亲结构）
     *
     * @param one
     * @param two
     * @return
     */
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

    /**
     * 判断 parentType 是否是 subType 的子类
     *
     * @param parentType
     * @param subType
     * @return
     */
    public static boolean isAssignableFrom(Class<?> parentType, Class<?> subType) {
        if (parentType == null || subType == null) {
            return false;
        }

        Class<?> baseParentType = baseTypeMapping.get(parentType);
        if (baseParentType != null) {
            Class<?> baseTwoType = baseTypeMapping.get(subType);
            return baseTwoType != null && subType == baseTwoType;
        }

        return parentType.isAssignableFrom(subType);
    }

    /**
     * 将 fromObject 中与 toObject 中同名字段的值复制到 toObject 对象中
     *
     * @param fromObject
     * @param toObject
     * @param <F>
     * @param <T>
     * @return
     */
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
