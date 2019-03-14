package com.cnews.guji.smart.ui.presenter;


import android.content.Context;

import com.cnews.guji.smart.ui.constant.NewsConstant;
import com.cnews.guji.smart.ui.contract.VideoNewsContract;
import com.cnews.guji.smart.ui.model.VideoNewsModel;
import com.cnews.guji.smart.ui.model.source.NewsSource;
import com.cnews.guji.smart.util.FileUtils;
import com.cnews.guji.smart.util.ILog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

/**
 * Created by dingcl
 * 视频数据处理
 */
public class VideoNewsPresenterimpl<S> implements VideoNewsContract.Presenter {
    private VideoNewsContract.View mVideoNewsView;
    private Context context;
    private Type type;

    public VideoNewsPresenterimpl(Context context, VideoNewsContract.View view) {
        this.mVideoNewsView = view;
        this.context = context;
        type = new TypeToken<VideoNewsModel>() {}.getType();

    }

    @Override
    public void getVideoData(int flag,int videoCurrentType) {
        ILog.e("getVideoData","getVideoData videoCurrentType:        "+videoCurrentType);
        ILog.e("getVideoData","getVideoData getVideoRefreshTypeSource:        "+NewsSource.getVideoRefreshTypeSource(videoCurrentType));
        ILog.e("getVideoData","getVideoData getVideoLoadMoreTypeSource:        "+NewsSource.getVideoLoadMoreTypeSource(videoCurrentType));
        VideoNewsModel multiIndexData = (VideoNewsModel) getMultiIndexJsonData
                (context, flag == 1 ? NewsSource.getVideoRefreshTypeSource(videoCurrentType): NewsSource.getVideoLoadMoreTypeSource(videoCurrentType), type);
        mVideoNewsView.setVideoData(multiIndexData);
    }

    @Override
    public void getVideoWares(int videoCurrentType) {
        VideoNewsModel multiIndexData = (VideoNewsModel)getMultiIndexJsonData(context,NewsConstant.ASSET_VIDEO_NEWS_TUIJIAN_MORE,type);
        mVideoNewsView.setVideoWares(multiIndexData);
    }

    @Override
    public void getVideoMoreWares(int videoCurrentType) {
        VideoNewsModel multiIndexData = (VideoNewsModel)getMultiIndexJsonData(context,NewsSource.getVideoLoadMoreTypeSource(videoCurrentType),type);
        mVideoNewsView.setVideoMoreWares(multiIndexData);
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


    /**
     * 解析txt文本
     * @param clazz
     * @param fileName
     * @return
     */
    public S getMultiIndexData(Context context,final Class<S> clazz , final String fileName) {
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
