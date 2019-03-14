package com.cnews.guji.smart.ui.presenter;


import android.content.Context;

import com.cnews.guji.smart.ui.constant.NewsConstant;
import com.cnews.guji.smart.ui.contract.HotNewsBestContract;
import com.cnews.guji.smart.ui.model.HotNewsBestModel;
import com.cnews.guji.smart.util.FileUtils;
import com.cnews.guji.smart.util.ILog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

/**
 * Created by dingcl
 * 头条业务处理
 */

public class HotNewsBestPresenterimpl<S> implements HotNewsBestContract.Presenter {
    private HotNewsBestContract.View mHotNewsBestView;
    private Context context;

    public HotNewsBestPresenterimpl(Context context, HotNewsBestContract.View view) {
        this.mHotNewsBestView = view;
        this.context = context;

    }

    @Override
    public void getHotNewsBestData() {
        HotNewsBestModel multiIndexData = (HotNewsBestModel) getMultiIndexJsonData(NewsConstant.ASSET_HOT_NEWS_BEST);
        ILog.e("HomeHotTopPresenterimpl","[multiIndexData] ===>> :"+new Gson().toJson(multiIndexData));
        mHotNewsBestView.setHotNewsBestData(multiIndexData);
    }

    @Override
    public void getHotNewsBestWares() {
        HotNewsBestModel multiIndexData = (HotNewsBestModel) getMultiIndexJsonData(NewsConstant.ASSET_HOT_NEWS_BEST);
        mHotNewsBestView.setHotNewsBestWares(multiIndexData);
    }

    @Override
    public void getHotNewsBestMoreWares() {
        HotNewsBestModel multiIndexData = (HotNewsBestModel)getMultiIndexData((Class<S>) HotNewsBestModel.class, NewsConstant.ASSET_HOT_NEWS_BEST_MORE);
        mHotNewsBestView.setHotNewsBestMoreWares(multiIndexData);
    }



    public S getMultiIndexJsonData(final String fileName) {
        String json = FileUtils.getJson(context, fileName);
        Gson gson = new Gson();
        Type type = new TypeToken<HotNewsBestModel>() {
        }.getType();
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
