package com.zxg.suiteset.nohttp;


import com.yanzhenjie.nohttp.Headers;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.RestRequest;
import com.yanzhenjie.nohttp.rest.StringRequest;

import java.lang.reflect.Type;

/**
 * Created by ZhengXiaoGuo on 2016/10/20.
 */

public class JavaBeanRequest<T> extends RestRequest<T> {

    private Class<T> tClass;

    private Type type;

    public JavaBeanRequest(String url, Type type) {
        this(url, RequestMethod.GET, type);
    }

    public JavaBeanRequest(String url, RequestMethod requestMethod, Type type) {
        super(url, requestMethod);
        this.type = type;
    }


    public JavaBeanRequest(String url, Class<T> tClass) {
        this(url, RequestMethod.GET, tClass);
    }

    public JavaBeanRequest(String url, RequestMethod requestMethod, Class<T> tClass) {
        super(url, requestMethod);
        this.tClass = tClass;
    }


    @Override
    public T parseResponse(Headers responseHeaders, byte[] responseBody) {
        String response = StringRequest.parseResponseString(responseHeaders, responseBody);
        // 这里如果数据格式错误，或者解析失败，会在失败的回调方法中返回 ParseError 异常。
        return JsonGenericsSerializer.getInstance().transform(response, type);

    }
}
