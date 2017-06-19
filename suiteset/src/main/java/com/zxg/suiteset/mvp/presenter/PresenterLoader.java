package com.zxg.suiteset.mvp.presenter;

import android.content.Context;
import android.support.v4.content.Loader;
import android.util.Log;

/**
 * Created by Administrator on 2017/6/13.
 */

public class PresenterLoader<T extends Presenter> extends Loader<T> {
    private final PresenterFactory<T> factory;
    private final String tag;
    private T presenter;

    public PresenterLoader(Context context, PresenterFactory<T> factory, String tag) {
        super(context);
        this.factory = factory;
        this.tag = tag;
    }


    @Override
    protected void onStartLoading() {
        Log.i("loader", "onStartLoading" + tag);
        if (presenter != null) {
            deliverResult(presenter);
        }
        forceLoad();
    }

    @Override
    protected void onForceLoad() {
        Log.i("loader", "onForceLoad" + tag);
        presenter = factory.create();
        deliverResult(presenter);
    }

    @Override
    public void deliverResult(T data) {
        super.deliverResult(data);
        Log.i("loader", "deliverResult" + tag);
    }

    @Override
    protected void onStopLoading() {
        Log.i("loader", "onStopLoading" + tag);
    }

    @Override
    protected void onReset() {
        Log.i("loader", "onReset" + tag);
        if (presenter != null) {
            presenter.onDestroyed();
            presenter = null;
        }

    }

    public T getPresenter() {
        return presenter;
    }
}
