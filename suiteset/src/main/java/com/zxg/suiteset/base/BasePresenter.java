package com.zxg.suiteset.base;

import com.zxg.suiteset.mvp.presenter.Presenter;

/**
 * Created by Administrator on 2017/6/12.
 */

public interface BasePresenter<V>  extends Presenter<V>{
    void start();
}
