package com.zxg.mzitu.ui;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zxg.mzitu.R;
import com.zxg.mzitu.contract.IParseDataContract;
import com.zxg.mzitu.model.IParseDataModel;
import com.zxg.mzitu.presenter.IParseDataPresenter;
import com.zxg.mzitu.utils.DefaultAddress;
import com.zxg.suiteset.base.BaseFragment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Administrator on 2017/6/15.
 */

public class ContentFragment extends BaseFragment implements IParseDataContract.View {

    @BindView(R.id.rv_list)
    RecyclerView rvList;
    Unbinder unbinder;
    private List<IParseDataModel> list = new ArrayList<>();
    private BaseQuickAdapter<IParseDataModel, BaseViewHolder> adapter;
    private IParseDataContract.Presenter mPresenter;
    @DefaultAddress("http://www.mzitu.com")
    String url="http://www.mzitu.com";

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_content;
    }

    @Override
    protected void onInitView() {
        unbinder = ButterKnife.bind(this, rootView);
        mPresenter = new IParseDataPresenter();
        new IParseDataPresenter();
//        new Thread(runnable).start();
        rvList.setLayoutManager(new GridLayoutManager(mContext, 4));
        rvList.setAdapter(adapter = new BaseQuickAdapter<IParseDataModel, BaseViewHolder>(R.layout.item_content, list) {
            @Override
            protected void convert(BaseViewHolder helper, IParseDataModel item) {
                helper.setText(R.id.tv_title, item.getImgTitle());
                Glide.with(mContext).load(item.getImgUrl()).into((ImageView) helper.getView(R.id.iv_img));
            }
        });
    }

    @Override
    protected void onInitData() {
        mPresenter.parseHtml("");
        Observable observable = Observable.create(new ObservableOnSubscribe<List<IParseDataModel>>() {

            @Override
            public void subscribe(ObservableEmitter<List<IParseDataModel>> e) throws Exception {
                e.onNext(parseHtml(url));
            }
        });
        observable.subscribe(observer);
    }

    @Override
    public void onResume() {
        super.onResume();
//        mPresenter.start();
        

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            adapter.notifyDataSetChanged();
            super.handleMessage(msg);
        }
    };
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            list.addAll(parseHtml(url));
            handler.sendEmptyMessage(0);
        }
    };
    @NonNull
    public List<IParseDataModel> parseHtml(@NonNull String url) {
        Document document;
        List<IParseDataModel> urlList = new ArrayList<>();
        String USER_AGENT = "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0";
        try {
            document = Jsoup.connect(url).userAgent(USER_AGENT).timeout(3000).post();
            Elements img = document.select("img[data-original$=.jpg]");
            for (Element element : img) {
                urlList.add(new IParseDataModel(element.absUrl("data-original"), element.attr("alt")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return urlList;
    }


    @Override
    public void setPresenter(@NonNull IParseDataContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void showResult(List<IParseDataModel> models) {

    }

    Observer<List<IParseDataModel>> observer = new Observer<List<IParseDataModel>>() {
        @Override
        public void onSubscribe(Disposable d) {
            Log.i(TAG, "onSubscribe");
        }

        @Override
        public void onNext(List<IParseDataModel> iParseDataModels) {
            Log.i(TAG, "onNext");
        }

        @Override
        public void onError(Throwable e) {
            Log.i(TAG, "onError");

        }

        @Override
        public void onComplete() {
            Log.i(TAG, "onComplete");
        }
    };

}
