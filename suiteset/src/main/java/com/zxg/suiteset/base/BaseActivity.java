package com.zxg.suiteset.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by Administrator on 2017/5/31.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;
    protected String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        if (getLayoutResource() != 0) {
            setContentView(getLayoutResource());
        }
        mContext = this;
        onInitView();
        onInitData();
    }

    protected abstract  int getLayoutResource();

    /**
     * 初始化界面
     * @return
     */
    protected abstract   void onInitView();

    /**
     * 初始化数据
     */
    protected abstract  void onInitData();

}
