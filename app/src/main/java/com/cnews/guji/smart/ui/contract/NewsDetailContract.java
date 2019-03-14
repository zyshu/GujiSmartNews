package com.cnews.guji.smart.ui.contract;

import android.content.Context;

import com.cnews.guji.smart.mvp.BaseModel;
import com.cnews.guji.smart.mvp.BaseView;
import com.cnews.guji.smart.ui.model.NewsDetailModel;

/**
 * 新闻详情
 */
public interface NewsDetailContract {
    interface Model extends BaseModel {
    }

    interface View extends BaseView {
        void detailItemData(NewsDetailModel data);
    }
    abstract static class Presenter{
        public abstract void deatailItemRequest(Context context,String articleUrl);
    }
}
