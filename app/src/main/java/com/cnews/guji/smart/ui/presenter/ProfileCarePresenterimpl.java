package com.cnews.guji.smart.ui.presenter;


import android.content.Context;

import com.cnews.guji.smart.ui.constant.NewsConstant;
import com.cnews.guji.smart.ui.contract.ProfileCareContract;
import com.cnews.guji.smart.ui.model.ProfileCareModel;
import com.cnews.guji.smart.util.FileUtils;
import com.cnews.guji.smart.util.ILog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

/**
 * Created by dingcl
 * 关注
 */
public class ProfileCarePresenterimpl<S> implements ProfileCareContract.Presenter {
    private ProfileCareContract.View mNewsView;
    private Context context;
    private Type type;

    public ProfileCarePresenterimpl(Context context, ProfileCareContract.View view) {
        this.mNewsView = view;
        this.context = context;
        type = new TypeToken<ProfileCareModel>() {}.getType();

    }

    @Override
    public void getData() {
        ProfileCareModel multiIndexData = (ProfileCareModel) getMultiIndexJsonData(context,NewsConstant.ASSET_PROFILE_CARE,type);
        ILog.e("profile"," getData:        "+ new Gson().toJson(multiIndexData));
        mNewsView.setData(multiIndexData);
    }

    @Override
    public void getDataWares() {
    }

    @Override
    public void getDataMoreWares() {

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
