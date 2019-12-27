/*
 * Copyright 2016-2017 the original author or authors.
 */
package com.intemanagement.sdk.json.jackson;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

/**
 * Created by PanYunFeng on 2016/5/5.
 */
public class FilterProviderFactory {

    private static final String commonFilter = "commonMixInFilter";

    public static final FilterProvider createWithIncludedFields(String... includedFields) {
        return new SimpleFilterProvider().addFilter(commonFilter,
                SimpleBeanPropertyFilter.filterOutAllExcept(includedFields));
    }

    public static final FilterProvider createWithExceptedFields(String... exceptedFields) {
        return new SimpleFilterProvider().addFilter(commonFilter,
                SimpleBeanPropertyFilter.serializeAllExcept(exceptedFields));
    }

}
