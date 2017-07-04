package com.zxg.mzitu.contract;

import com.zxg.mzitu.model.IParseDataModel;
import com.zxg.suiteset.base.BasePresenter;
import com.zxg.suiteset.base.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2017/6/14.
 */

public interface IParseDataContract {

    interface Model {
    }

    interface View extends BaseView<Presenter> {

        void showResult(List<IParseDataModel> models);
    }

    interface Presenter extends BasePresenter<View> {

        void parseHtml(String url);
    }
}
