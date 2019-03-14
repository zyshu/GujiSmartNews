package com.cnews.guji.smart.ui.contract;
import com.cnews.guji.smart.ui.model.VideoNewsModel;


/**
 * @author dingcl
 * 视频业务
 */

public interface VideoNewsContract {
    interface View {
        void setVideoData(VideoNewsModel data);
        void setVideoWares(VideoNewsModel data);
        void setVideoMoreWares(VideoNewsModel data);
    }
    interface Presenter {
        void getVideoData(int flag,int videoCurrentType);
        void getVideoWares(int videoCurrentType);
        void getVideoMoreWares(int videoCurrentType);
    }

}