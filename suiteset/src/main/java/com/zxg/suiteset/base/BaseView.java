package com.zxg.suiteset.base;

/**
 * Created by Administrator on 2017/6/12.
 */

public interface BaseView<T extends BasePresenter> {
    void setPresenter(T presenter);
}
