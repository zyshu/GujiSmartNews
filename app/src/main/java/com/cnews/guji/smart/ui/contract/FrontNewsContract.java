package com.cnews.guji.smart.ui.contract;
import com.cnews.guji.smart.ui.model.FrontNewsModel;


/**
 * @author dingcl
 * 要闻业务
 */

public interface FrontNewsContract {
    interface View {
        void setFrontNewsData(FrontNewsModel data);
        void setFrontNewsWares(FrontNewsModel data);
        void setFrontNewsMoreWares(FrontNewsModel data);
    }
    interface Presenter {
        void getFrontNewsData(int flag);
        void getFrontNewsWares();
        void getFrontNewsMoreWares();
    }

}