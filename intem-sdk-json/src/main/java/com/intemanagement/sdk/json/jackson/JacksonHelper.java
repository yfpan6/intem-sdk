/*
 * Copyright 2016-2017 the original author or authors.
 */
package com.intemanagement.sdk.json.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.type.TypeBindings;
import lombok.extern.slf4j.Slf4j;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 基于Jackson的 json工具类
 *
 * Created by PanYunFeng on 2016/5/4.
 */
@Slf4j
public final class JacksonHelper {

    private static final ConcurrentMap<CacheKey, ObjectMapper> mapperCache = new ConcurrentHashMap();

    private static final ObjectMapper mapper = new ObjectMapper();
    static {
        // 全局配置
        // 遇到未知属性不抛出异常
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 不将json中的空字符串"" 转换为 java null
        mapper.disable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        // 不要求json格式严格符合规范， 属性名可以没有双引号
        mapper.disable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES);
        //优化输出
        mapper.enable(SerializationFeature.INDENT_OUTPUT); //优化输出
        // 序列化空的pojo
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // 不将date转化成timestamp
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        //SimpleDateFormat 内部使用是线程安全的
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    public enum FieldScope {
        INCLUDED, //
        EXCEPTED
    }

    private JacksonHelper() {
    }

    /**
     * java对象转json<br/>
     * 需指定不需要转换或将要转换的属性。
     *
     * @param obj 要序列化的对象
     * @param fieldScope 包含或是过滤排除
     * @param fields 其他属性
     * @return 对象的json字符串
     */
    public static final String toJSON(Object obj, FieldScope fieldScope,
                                      String... fields) {
        try {
            FilterProvider fp;
            if (FieldScope.INCLUDED == fieldScope) {
                fp = FilterProviderFactory.createWithIncludedFields(fields);
            } else {
                fp = FilterProviderFactory.createWithExceptedFields(fields);
            }
            return getObjectMapper(obj.getClass(), CommonMixInFilter.class)
                    .writer().with(fp).writeValueAsString(obj);
        } catch (Exception e) {
            log.error("to json error，object[{}]", obj, e);
        }
        return null;
    }

    /**
     * java对象转json<br/>
     *
     * @param obj 要序列化的对象
     * @param mixInClass jackson的MixInAnnotation类的class对象
     * @return 对象的json字符串
     */
    public static final String toJSON(Object obj, Class<?> mixInClass) {
        try {
            Class<?> target = obj.getClass();
            return getObjectMapper(target, mixInClass)
                    .writeValueAsString(obj);
        } catch (Exception e) {
            log.error("to json error，object[" + obj + "]", e);
        }
        return null;
    }

    /**
     * java对象转json<br/>
     *
     * @param obj 要序列化的对象
     * @return 对象的json字符串
     */
    public static final String toJSON(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.error("to json error，object[" + obj + "]", e);
        }
        return null;
    }

    /**
     * json字符串转java对象
     *
     * @param json json字符串
     * @param clazz json对应java对象类型
     * @return clazz类型java对象
     */
    public static final <T> T toBean(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            log.error("to bean error，json => " + json + "", e);
        }
        return null;
    }

    /**
     * json转换为Map包装类
     *
     * @param json json字符串
     * @return HashMap对象
     */
    public static final Map<String, ?> toMap(String json) {
        try {
            return mapper.readValue(json, HashMap.class);
        } catch (IOException e) {
            log.error("to map error，json => " + json + "", e);
        }
        return null;
    }

    /**
     * 将Map转换成泛型, 默认Map key 为 String类型
     *
     * @param json json字符串
     * @param valueType Map value的类型
     * @return Map
     */
    public static final <T> Map<String, T> toMap(String json, Class<T> valueType) {
        return toMap(json, Map.class, String.class, valueType);
    }

    /**
     * 将Map转换成泛型, 默认Map key 为 String类型
     *
     * @param json json字符串
     * @param valueType Map value的类型
     * @return Map
     */
    public static final <K, T> Map<K, T> toMap(String json, Class<K> keyType, Class<T> valueType) {
        return toMap(json, Map.class, keyType, valueType);
    }

    /**
     * 将Map转换成泛型, 默认Map key 为 String类型
     *
     * @param json json字符串
     * @param mapType map的具体类型
     * @param keyType map key的类型
     * @param valueType Map value的类型
     * @return Map
     */
    public static final <K, T> Map<K, T> toMap(String json, Class<? extends Map> mapType,
                                               Class<K> keyType, Class<T> valueType) {
        ParameterizedType pType = ParameterizedTypeImpl.make(mapType,
                new Type[]{keyType, valueType}, null);
        JavaType javaType = mapper.getTypeFactory().constructType(pType, (TypeBindings) null);
        try {
            return mapper.readValue(json, javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json数组转java List， List内部用LinkedHashMap封装属性
     *
     * @param jsonArray json数组
     * @return List
     */
    public static final List<LinkedHashMap<String, Object>> toMapList(String jsonArray) {
        try {
            return mapper.readValue(jsonArray, List.class);
        } catch (Exception e) {
            log.error("to list error，json => " + jsonArray, e);
        }
        return null;
    }

    /**
     * json数组转java ArrayList
     *
     * @param jsonArray json数组
     * @param genericType List中对象的类型
     * @return List
     */
    public static final <T> List<T> toList(String jsonArray, Class<T> genericType) {
        return toList(jsonArray, List.class, genericType);
    }

    /**
     * json数组转java List
     *
     * @param jsonArray json数组
     * @param listType List的具体类型
     * @param genericType List中对象的类型
     * @return List
     */
    public static final <T> List<T> toList(String jsonArray, Class<? extends List> listType, Class<T> genericType) {
        try {
            ParameterizedType pType = ParameterizedTypeImpl.make(listType, new Type[]{genericType}, null);
            JavaType javaType = mapper.getTypeFactory().constructType(pType, (TypeBindings) null);
            return mapper.readValue(jsonArray, javaType);
        } catch (Exception e) {
            log.error("to list error，json => " + jsonArray, e);
        }
        return null;
    }

    /**
     * json数组转 java数组
     * @param jsonArray json数组
     * @param clazz java数组类型
     * @return 数组
     */
    public static final <T> T[] toArray(String jsonArray, Class<T[]> clazz) {
        try {
            return mapper.readValue(jsonArray, clazz);
        } catch (Exception e) {
            log.error("to array error，json => " + jsonArray, e);
        }
        return null;
    }

    /**
     * 新建ObjectMapper对象
     *
     * @return ObjectMapper
     */
    public static final ObjectMapper newObjectMapper() {
        return mapper.copy();
    }

    /**
     * 获得添加了MixInAnnotation的ObjectMapper对象，默认从缓存中取，如果没有则新建。
     *
     * @param target 转换源对象类型
     * @param mixInClass MixInAnnotation的类型
     * @return ObjectMapper
     */
    public static final ObjectMapper getObjectMapper(Class<?> target, Class<?> mixInClass) {
        CacheKey key = new CacheKey(target, mixInClass);
        ObjectMapper objectMapper = mapperCache.get(key);
        if (objectMapper == null) {
            objectMapper = newObjectMapper().addMixIn(target, mixInClass);
            mapperCache.put(key, objectMapper);
        }
        return objectMapper;
    }

    private static class CacheKey implements Comparable<CacheKey>{
        private Class<?> target;
        private Class<?> mixInClass;
        private int hashCode;

        public CacheKey(Class<?> target, Class<?> mixInClass) {
            this.target = target;
            this.mixInClass = mixInClass;
            hashCode = (target.getName() + mixInClass.getName()).hashCode();
        }

        @Override
        public int compareTo(CacheKey o) {
            if (target == o.target
                    && mixInClass == o.mixInClass) {
                return 0;
            }
            return 1;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof CacheKey)) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            CacheKey temp = (CacheKey) obj;
            return temp.target == target
                    && temp.mixInClass == mixInClass;
        }

        @Override
        public int hashCode() {
            return hashCode;
        }
    }

}
