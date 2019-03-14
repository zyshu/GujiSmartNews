package com.cnews.guji.smart.ui.presenter;

import android.content.Context;

import com.cnews.guji.smart.base.baserx.RxManager;

/**
 * des:基类presenter
 * Created by xsf
 * on 2016.07.11:55
 */
public abstract class BaseRxPresenter<T,E>{
    public Context mContext;
    public E mModel;
    public T mView;
    public RxManager mRxManage = new RxManager();

    public void setVM(T v, E m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }
    public void onStart(){
    };
    public void onDestroy() {
        mRxManage.clear();
    }
}
