package com.zxg.mzitu.ui;

import android.content.Context;
import android.view.View;

/**
 * Created by Administrator on 2017/6/19.
 */

public class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = realSize(100, widthMeasureSpec);
        int height = realSize(100, heightMeasureSpec);
        if (width < height) {
            height = width;
        } else {
            width = height;
        }
        setMeasuredDimension(width,height);
    }

    private int realSize(int defaultSize, int measureSpec) {
        int realSize = defaultSize;

        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        switch (mode) {
            case MeasureSpec.UNSPECIFIED: //如果没有指定大小，就设置为默认大小
                realSize = defaultSize;
                break;
            case MeasureSpec.AT_MOST://如果测量模式是最大取值为size
                realSize = size;
                break;
            case MeasureSpec.EXACTLY://如果是固定的大小，那就不要去改变它
                realSize = size;
                break;
        }
        return realSize;
    }
}
