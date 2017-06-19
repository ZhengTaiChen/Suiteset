package com.zxg.mzitu.model;

import com.zxg.mzitu.contract.IParseDataContract;

/**
 * Created by Administrator on 2017/6/14.
 */

public class IParseDataModel implements IParseDataContract.Model {
    private String imgUrl;
    private String imgTitle;

    public IParseDataModel(String imgUrl, String imgTitle) {
        this.imgUrl = imgUrl;
        this.imgTitle = imgTitle;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgTitle() {
        return imgTitle;
    }

    public void setImgTitle(String imgTitle) {
        this.imgTitle = imgTitle;
    }
}
