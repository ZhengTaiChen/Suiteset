package com.zxg.suiteset.mvp.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;

import com.zxg.suiteset.base.BaseActivity;
import com.zxg.suiteset.mvp.presenter.Presenter;
import com.zxg.suiteset.mvp.presenter.PresenterFactory;
import com.zxg.suiteset.mvp.presenter.PresenterLoader;

/**
 * Created by Administrator on 2017/6/13.
 */

public abstract class BasePresenterActivity<P extends Presenter<V>,V> extends BaseActivity {
    private static final int LOADER_ID = 101;
    protected P presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Loader<P> loader = getSupportLoaderManager().getLoader(loaderId());
        if (loader == null) {
            initLoader();
        } else {
            this.presenter = ((PresenterLoader<P>) loader).getPresenter();
        }
    }

    private void initLoader() {
        getSupportLoaderManager().initLoader(loaderId(), null, new LoaderManager.LoaderCallbacks<P>() {
            @Override
            public Loader<P> onCreateLoader(int i, Bundle bundle) {
                Log.i(TAG, "onCreateLoader");

                return new PresenterLoader<>(mContext,getPresenterFactory(),tag());
            }

            @Override
            public void onLoadFinished(Loader<P> loader, P p) {
                Log.i(TAG, "onLoadFinished");
                BasePresenterActivity.this.presenter = presenter;
                onPresenterCreatedOrRestored(p);


            }

            @Override
            public void onLoaderReset(Loader<P> loader) {
                Log.i(TAG, "onLoaderReset");
                BasePresenterActivity.this.presenter = null;

            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart-" + tag());
        presenter.onViewAttached(getPresenterView());

    }

    @Override
    protected void onStop() {
        presenter.onViewDetached();
        super.onStop();
        Log.i(TAG, "onStop-" + tag());
    }

    /**
     * String tag use for log purposes.
     */
    @NonNull
    protected String tag() {
        return TAG;
    }

    /**
     * Instance of {@link PresenterFactory} use to create a Presenter when needed. This instance should
     * not contain {@link android.app.Activity} context reference since it will be keep on rotations.
     */
    @NonNull
    protected abstract PresenterFactory<P> getPresenterFactory();

    /**
     * Hook for subclasses that deliver the {@link Presenter} before its View is attached.
     * Can be use to initialize the Presenter or simple hold a reference to it.
     */
    protected abstract void onPresenterCreatedOrRestored(@NonNull P presenter);

    /**
     * Override in case of fragment not implementing Presenter<View> interface
     */
    @NonNull
    protected V getPresenterView() {
        return (V) this;
    }

    protected int loaderId() {
        return LOADER_ID;
    }
}
