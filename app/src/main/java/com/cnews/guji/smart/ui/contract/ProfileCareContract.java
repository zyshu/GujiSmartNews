package com.cnews.guji.smart.ui.contract;
import com.cnews.guji.smart.ui.model.ProfileCareModel;


/**
 * @author dingcl
 * 个人
 */

public interface ProfileCareContract {
    interface View {
        void setData(ProfileCareModel data);
        void setDataWares(ProfileCareModel data);
        void setDataMoreWares(ProfileCareModel data);
    }
    interface Presenter {
        void getData();
        void getDataWares();
        void getDataMoreWares();
    }

}