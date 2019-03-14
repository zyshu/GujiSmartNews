package com.cnews.guji.smart.ui.presenter;

import android.content.Context;

import com.cnews.guji.smart.api.HostType;
import com.cnews.guji.smart.common.net.ApiRetrofit;
import com.cnews.guji.smart.ui.contract.NewsDetailContract;
import com.cnews.guji.smart.ui.model.NewsDetailModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * des:
 */
public class NewsDetailPresenter extends NewsDetailContract.Presenter{
    NewsDetailContract.View mView;

    public NewsDetailPresenter(NewsDetailContract.View mView) {
        this.mView = mView;
    }
    /**
     * 新闻详情
     * @param context
     */
    @Override
    public void deatailItemRequest(Context context,String articleUrl) {
        if (mView != null) mView.showLoading();
        ApiRetrofit.getInstence(context,HostType.NETEASE_NEWS_VIDEO)
                .getApIservice()
                .getNewsDetail(articleUrl)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsDetailModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(NewsDetailModel data) {
                        if (mView != null) {
                            if (data != null) {
                                mView.detailItemData(data);
                            }
                        }
                    }

                    @Override
                    public void onComplete() {
                        if (mView != null) {
                            mView.stopLoading();
                        }
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







}
