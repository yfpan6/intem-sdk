/*
 * Copyright 2016-2017 the original author or authors.
 */
package com.intemanage.sdk.json.jackson.formatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Created by PanYunFeng on 2016/5/4.
 */
public class LongToStringSerializer extends JsonSerializer<Long> {

    @Override
    public void serialize(Long aLong, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        if (aLong != null) {
            jsonGenerator.writeString(aLong.toString());
        } else {
            jsonGenerator.writeObject(aLong);
        }
    }

}
