package com.zxg.suiteset.mvp.presenter;

/**
 * Created by Administrator on 2017/6/13.
 */

public interface Presenter<V> {
    void onViewAttached(V view);
    void onViewDetached();
    void onDestroyed();
}
