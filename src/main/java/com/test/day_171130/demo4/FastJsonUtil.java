package com.test.day_171130.demo4;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class FastJsonUtil {
    private static final SerializerFeature[] FEATURES;

    public FastJsonUtil() {
    }

    public static String toJSONString(Object object) {
        return JSON.toJSONString(object, FEATURES);
    }

    static {
        FEATURES = new SerializerFeature[]{
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.DisableCircularReferenceDetect
        };
    }
}