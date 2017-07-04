package com.zxg.suiteset.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/5/31.
 */

public abstract class BaseFragment extends Fragment {
    protected final String TAG = getClass().getSimpleName();
    protected BaseActivity mContext;
    protected View rootView;
    protected DisplayMetrics metrics = new DisplayMetrics();
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = (BaseActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutResource(), container, false);
        }
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        onInitView();
        onInitData();
        return rootView;
    }
    /**
     * 初始化界面
     * @return
     */
    protected abstract int getLayoutResource();


    protected abstract void onInitView();

    /**
     * 初始化数据
     */
    protected abstract void onInitData();
}
