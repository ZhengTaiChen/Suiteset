package com.zxg.suiteset.base;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/6/20.
 */
@Module
public final class ApplicationModule {
    public final Context mContext;

    public ApplicationModule(Context mContext) {
        this.mContext = mContext;
    }
    @Provides
    Context provideContext() {
        return mContext;
    }
}
