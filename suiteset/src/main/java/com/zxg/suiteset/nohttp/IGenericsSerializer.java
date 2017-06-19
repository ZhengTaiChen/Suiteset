package com.zxg.suiteset.nohttp;

import java.lang.reflect.Type;

/**
 * Created by ZhengXiaoGuo on 2016/6/23.
 */
public interface IGenericsSerializer {
    <T> T transform(String response, Class<T> classOfT);
    <T> T transform(String response, Type type);
}
