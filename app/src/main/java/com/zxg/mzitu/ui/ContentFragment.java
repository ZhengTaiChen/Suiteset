package com.zxg.mzitu.ui;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zxg.mzitu.R;
import com.zxg.mzitu.model.IParseDataModel;
import com.zxg.suiteset.base.BaseFragment;
import com.zxg.suiteset.base.BaseModel;

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

/**
 * Created by Administrator on 2017/6/15.
 */

public class ContentFragment extends BaseFragment {

    @BindView(R.id.rv_list)
    RecyclerView rvList;
    Unbinder unbinder;
    private List<IParseDataModel> list = new ArrayList<>();
    private BaseQuickAdapter<IParseDataModel, BaseViewHolder> adapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_content;
    }

    @Override
    protected void onInitView() {
        unbinder = ButterKnife.bind(this, rootView);
        new Thread(runnable).start();
        rvList.setLayoutManager(new GridLayoutManager(mContext,4));
        rvList.setAdapter(adapter=new BaseQuickAdapter<IParseDataModel, BaseViewHolder>(R.layout.item_content,list) {
            @Override
            protected void convert(BaseViewHolder helper, IParseDataModel item) {
                helper.setText(R.id.tv_title, item.getImgTitle());
                Glide.with(mContext).load(item.getImgUrl()).into((ImageView) helper.getView(R.id.iv_img));
            }
        });
    }

    @Override
    protected void onInitData() {

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
            String url = "http://www.mzitu.com";
            if (((MainActivity) mContext).tabHost.getCurrentTab()!=null)
            switch (((MainActivity) mContext).tabHost.getCurrentTab().getPosition()) {
                case 0:
                    url = "http://www.mzitu.com";
                    break;
                case 1:
                    url = "http://www.mzitu.com/xinggan/";
                    break;
                case 2:
                    url = "http://www.mzitu.com/japan/";
                    break;
                case 3:
                    url = "http://www.mzitu.com/taiwan/";
                    break;
                case 4:
                    url = "http://www.mzitu.com/mm/";
                    break;
                case 5:
                    url = "http://www.mzitu.com/zipai/";
                    break;
            }
            String USER_AGENT = "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0";
            Document document;
            try {
                document = Jsoup.connect(url).userAgent(USER_AGENT).timeout(3000).post();
                Elements img = document.select("img[data-original$=.jpg]");
                for (Element element : img) {
                    list.add(new IParseDataModel(element.absUrl("data-original"),element.attr("alt")));
                }
                handler.sendEmptyMessage(0);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

}
