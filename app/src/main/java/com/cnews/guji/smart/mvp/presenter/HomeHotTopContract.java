package com.cnews.guji.smart.mvp.presenter;

import com.cnews.guji.smart.common.bean.basebean.HomeTophotIndex;


/**
 * @author dingcl
 */

public interface HomeHotTopContract {
    interface View {
        void setHomeHotTopData(HomeTophotIndex tophotBean);
        void setHomeHotTopWares(HomeTophotIndex tophotBean);
        void setMoreHomeHotTopWares(HomeTophotIndex tophotBean);
    }
    interface Presenter {
//        void getHomeHotTopData(int flag);
        void getHomeHotTopData(int flag,int videoCurrentType);
        void getHomeHotTopWares(int videoCurrentType);
        void getMoreHomeHotTopWares(int videoCurrentType);
    }

}