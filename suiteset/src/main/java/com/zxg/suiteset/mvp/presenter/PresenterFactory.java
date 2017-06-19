package com.zxg.suiteset.mvp.presenter;

/**
 * Created by Administrator on 2017/6/13.
 */

public interface PresenterFactory<T extends Presenter>{
    T create();
}
