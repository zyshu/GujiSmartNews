package com.cnews.guji.smart.ui.presenter;


import android.content.Context;

import com.cnews.guji.smart.ui.constant.NewsConstant;
import com.cnews.guji.smart.ui.contract.FrontNewsContract;
import com.cnews.guji.smart.ui.model.FrontNewsModel;
import com.cnews.guji.smart.util.FileUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

/**
 * Created by dingcl
 * 要闻数据处理
 */
public class FrontNewsPresenterimpl<S> implements FrontNewsContract.Presenter {
    private FrontNewsContract.View mFrontNewsView;
    private Context context;

    public FrontNewsPresenterimpl(Context context, FrontNewsContract.View view) {
        this.mFrontNewsView = view;
        this.context = context;

    }

    @Override
    public void getFrontNewsData(int flag) {
        FrontNewsModel multiIndexData = (FrontNewsModel) getMultiIndexJsonData
                (flag == 1 ? NewsConstant.ASSET_FRONT_NEWS : NewsConstant.ASSET_FRONT_NEWS_MORE);
        mFrontNewsView.setFrontNewsData(multiIndexData);
    }

    @Override
    public void getFrontNewsWares() {
        FrontNewsModel multiIndexData = (FrontNewsModel) getMultiIndexJsonData(NewsConstant.ASSET_FRONT_NEWS_MORE);
        mFrontNewsView.setFrontNewsWares(multiIndexData);
    }

    @Override
    public void getFrontNewsMoreWares() {
        FrontNewsModel multiIndexData = (FrontNewsModel)getMultiIndexJsonData(NewsConstant.ASSET_FRONT_NEWS_MORE);
        mFrontNewsView.setFrontNewsMoreWares(multiIndexData);
    }


    /**
     * 解析json
     */
    public S getMultiIndexJsonData(final String fileName) {
        String json = FileUtils.getJson(context, fileName);
        Gson gson = new Gson();
        Type type = new TypeToken<FrontNewsModel>() {
        }.getType();
        return gson.fromJson(json, type);
    }


    /**
     * 解析txt文本
     * @param clazz
     * @param fileName
     * @return
     */
    public S getMultiIndexData(final Class<S> clazz , final String fileName) {
        InputStream is = null;
        S s = null;
        try {
            is = context.getAssets().open(fileName);
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
