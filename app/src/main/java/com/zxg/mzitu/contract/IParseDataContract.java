package com.zxg.mzitu.contract;

import com.zxg.suiteset.base.BasePresenter;
import com.zxg.suiteset.base.BaseView;

/**
 * Created by Administrator on 2017/6/14.
 */

public interface IParseDataContract {

    interface Model {
    }

    interface View extends BaseView<Presenter> {
    }

    interface Presenter extends BasePresenter<View> {


    }
}
