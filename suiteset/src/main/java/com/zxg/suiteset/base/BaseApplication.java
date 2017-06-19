package com.zxg.suiteset.base;

import android.app.Application;

import com.yanzhenjie.nohttp.BuildConfig;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;

/**
 * Created by Administrator on 2017/5/31.
 */

public class BaseApplication extends Application {
    private static BaseApplication _instance;

    @Override
    public void onCreate() {
        super.onCreate();
        _instance = this;
        Logger.setDebug(BuildConfig.DEBUG);// 开启NoHttp的调试模式, 配置后可看到请求过程、日志和错误信息。
        Logger.setTag("NoHttpSample");// 设置NoHttp打印Log的tag。
        // 一般情况下你只需要这样初始化：
        NoHttp.initialize(this);
    }

    public static BaseApplication getInstance() {
        return _instance;
    }
}
