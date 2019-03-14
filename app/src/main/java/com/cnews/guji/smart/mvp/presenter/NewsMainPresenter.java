package com.cnews.guji.smart.mvp.presenter;

import android.content.Context;

import com.cnews.guji.smart.api.HostType;
import com.cnews.guji.smart.base.AppConstant;
import com.cnews.guji.smart.common.net.ApiRetrofit;
import com.cnews.guji.smart.mvp.contract.NewsMainContract;
import com.cnews.guji.smart.mvp.model.NewsMainModel;
import com.cnews.guji.smart.util.ILog;
import com.google.gson.Gson;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * des:
 */
public class NewsMainPresenter extends NewsMainContract.Presenter{
    NewsMainContract.View mView;

    public NewsMainPresenter(NewsMainContract.View mView) {
        this.mView = mView;
    }

    /**
     * 头条
     */
    @Override
    public void lodeMineChannelsRequest(Context context) {
        if (mView != null) mView.showLoading();
        ApiRetrofit.getInstence(context,HostType.NETEASE_NEWS_VIDEO)
                .getApIservice()
                .getHeaderNews(AppConstant.HEADLINE_ID, 0, 20)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsMainModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NewsMainModel newsMainModel) {
                        if (mView != null) {
                            if (newsMainModel != null) {
                                mView.returnMineNewsChannels(newsMainModel);
//                                ToastUitl.showShort("news"+new Gson().toJson(newsMainModel));
                                ILog.e("news","news"+new Gson().toJson(newsMainModel));
                                ILog.e("news","news"+newsMainModel);
                            }
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                    @Override
                    public void onError(Throwable e) {
                        if (mView != null) {
                            mView.showErrorTip(e.toString());
                            mView.stopLoading();
                        }
                    }
                });
    }



  /*  *//**
     * 头条
     *//*
    @Override
    public void lodeMineChannelsRequest(Context context) {
        if (mView != null)mView.showLoading();
        ApiRetrofit.getInstence(context,HostType.NETEASE_NEWS_VIDEO)
                .getApIservice()
                .getHeaderNews(AppConstant.HEADLINE_ID,0,20)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsMainModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(NewsMainModel newsMainModel) {
                        if (mView != null) {
                            if (newsMainModel != null) {
                                mView.returnMineNewsChannels(newsMainModel);
                                ToastUitl.showShort("news"+new Gson().toJson(newsMainModel));
                                ILog.e("news","news"+new Gson().toJson(newsMainModel));
                                ILog.e("news","news"+newsMainModel);
                            }
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        if (mView != null) {
                            mView.showErrorTip(e.toString());
                            mView.stopLoading();
                        }
                    }

                    @Override
                    public void onComplete() {
                        if (mView != null) {
                            mView.stopLoading();
                        }
                    }
                });

    }*/
}
