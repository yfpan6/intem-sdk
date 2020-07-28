package com.intemanage.sdk.invoking;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * @Author panyunfeng
 * @Date 2019/12/15
 */
public final class Request {

    private Map<String, Object> params;

    private Request(Map<String, Object> params) {
        this.params = params;
    }

    public static Request of() {
        return new Request(new HashMap<>());
    }

    public static Request of(Map<String, Object> params) {
        return new Request(params);
    }

    public Request putParam(String paramName, Object value) {
        params.put(paramName, value);
        return this;
    }

    public void forEachParams(BiConsumer<String, Object> consumer) {
        params.forEach(consumer);
    }
}
