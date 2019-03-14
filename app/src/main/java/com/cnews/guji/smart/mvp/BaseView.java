package com.cnews.guji.smart.mvp;

/**
 * baseview
 */
public interface BaseView {
    /*******内嵌加载*******/
    void showLoading(String title);
    void showLoading();
    void stopLoading();
    void showErrorTip(String msg);
}
