package com.cnews.guji.smart.mvp.contract;

import android.content.Context;

import com.cnews.guji.smart.mvp.BasePresenter;
import com.cnews.guji.smart.mvp.BaseView;
import com.cnews.guji.smart.mvp.model.NewsMainModel;

/**
 * 首页-头条
 */
public interface NewsMainContract {
    interface View extends BaseView {
        void returnMineNewsChannels(NewsMainModel newsMainModel);
    }
    abstract static class Presenter extends BasePresenter<View, NewsMainModel> {
        public abstract void lodeMineChannelsRequest(Context context);
    }
}
