package com.zxg.mzitu.presenter;

import com.zxg.mzitu.contract.IParseDataContract;

/**
 * Created by Administrator on 2017/6/14.
 */

public class IParseDataPresenter implements IParseDataContract.Presenter {

    private IParseDataContract.View view;
    @Override
    public void onViewAttached(IParseDataContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void onViewDetached() {

    }

    @Override
    public void onDestroyed() {

    }

    @Override
    public void start() {

    }

    @Override
    public void parseHtml(String url) {

    }
}
