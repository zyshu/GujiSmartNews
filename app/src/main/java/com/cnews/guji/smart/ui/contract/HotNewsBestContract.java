package com.cnews.guji.smart.ui.contract;
import com.cnews.guji.smart.ui.model.HotNewsBestModel;


/**
 * @author dingcl
 * 热闻精选业务
 */

public interface HotNewsBestContract {
    interface View {
        void setHotNewsBestData(HotNewsBestModel hotNewsBestModel);
        void setHotNewsBestWares(HotNewsBestModel hotNewsBestModel);
        void setHotNewsBestMoreWares(HotNewsBestModel hotNewsBestModel);
    }
    interface Presenter {
        void getHotNewsBestData();
        void getHotNewsBestWares();
        void getHotNewsBestMoreWares();
    }

}