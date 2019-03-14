package com.cnews.guji.smart.mvp.presenter.impl;


import android.content.Context;

import com.cnews.guji.smart.common.bean.basebean.HomeTophotIndex;
import com.cnews.guji.smart.mvp.presenter.HomeHotTopContract;
import com.cnews.guji.smart.ui.constant.NewsConstant;
import com.cnews.guji.smart.ui.model.source.NewsSource;
import com.cnews.guji.smart.util.FileUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

/**
 * Created by dingcl
 * 头条业务处理
 */

public class HomeHotTopPresenterimpl<S> implements HomeHotTopContract.Presenter{
    private HomeHotTopContract.View mHomeHotTopView;
    private Context context;
    private Type type;

    public HomeHotTopPresenterimpl(Context context, HomeHotTopContract.View view) {
        this.mHomeHotTopView = view;
        this.context = context;
        type = new TypeToken<HomeTophotIndex>() {}.getType();

    }

    @Override
    public void getHomeHotTopData(int flag,int videoCurrentType) {
//        HomeTophotIndex multiIndexData = (HomeTophotIndex) getMultiIndexJsonData(NewsConstant.ASSET_HOME_HOT_TOP);
//        ILog.e("HomeHotTopPresenterimpl","[multiIndexData] ===>> :"+new Gson().toJson(multiIndexData));
//        mHomeHotTopView.setHomeHotTopData(multiIndexData);
        HomeTophotIndex multiIndexData = (HomeTophotIndex) getMultiIndexJsonData
                (context, flag == 1 ? NewsSource.getHomeRefreshTypeSource(videoCurrentType): NewsSource.getHomeRefreshTypeSource(videoCurrentType), type);
        mHomeHotTopView.setHomeHotTopData(multiIndexData);
    }


    @Override
    public void getHomeHotTopWares(int videoCurrentType) {
        HomeTophotIndex multiIndexData = (HomeTophotIndex) getMultiIndexJsonData(NewsConstant.ASSET_HOME_HOT_TOP);
        mHomeHotTopView.setHomeHotTopWares(multiIndexData);
    }

    @Override
    public void getMoreHomeHotTopWares(int videoCurrentType) {
//        HomeTophotIndex multiIndexData = (HomeTophotIndex)getMultiIndexData((Class<S>) HomeTophotIndex.class, NewsConstant.ASSET_HOME_HOT_TOP_MORE);
//        mHomeHotTopView.setMoreHomeHotTopWares(multiIndexData);
        HomeTophotIndex multiIndexData = (HomeTophotIndex)getMultiIndexJsonData(context,NewsSource.getHomeLoadMoreTypeSource(videoCurrentType),type);
        mHomeHotTopView.setMoreHomeHotTopWares(multiIndexData);
    }


    public S getMultiIndexJsonData(final String fileName) {
        String json = FileUtils.getJson(context, fileName);
        Gson gson = new Gson();
        Type type = new TypeToken<HomeTophotIndex>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    /**
     * 解析json
     */
    public S getMultiIndexJsonData(Context context, final String fileName, Type type) {
        String json = FileUtils.getJson(context, fileName);
        Gson gson = new Gson();
//        Type type = new TypeToken<classa>() {
//        }.getType();
        return gson.fromJson(json, type);
    }


    public S getMultiIndexData(final Class<S> clazz , final String fillName) {
        InputStream is = null;
        S s = null;
        try {
            is = context.getAssets().open(fillName);
            String text = FileUtils.readTextFromFile(is);
            Gson gson = new Gson();
            s = gson.fromJson(text, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return s;

    }

}
