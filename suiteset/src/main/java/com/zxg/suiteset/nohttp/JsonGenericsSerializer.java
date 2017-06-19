package com.zxg.suiteset.nohttp;


import com.google.gson.Gson;

import java.lang.reflect.Type;


/**
 * Created by ZhengXiaoGuo on 2016/8/9 0009.
 */
public class JsonGenericsSerializer implements IGenericsSerializer {
    private static JsonGenericsSerializer jsonResponseParser;
    private Gson mGson = new Gson();
    public static JsonGenericsSerializer getInstance() {
        if (jsonResponseParser == null) {
            jsonResponseParser = new JsonGenericsSerializer();
        }
        return jsonResponseParser;
    }
    @Override
    public <T> T transform(String response, Class<T> classOfT) {
        return mGson.fromJson(response, classOfT);
    }
    @Override
    public <T> T transform(String response, Type type) {
        return mGson.fromJson(response,type);
    }

}
