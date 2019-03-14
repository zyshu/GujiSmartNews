package com.cnews.guji.smart.mvp.view;

/**
 * @author JSYL-DCL
 * @date 2018/5/24 14:18
 */
public interface IBaseView {
    /**
     * 显示进度
     */
    void showProgress(String content);

    /**
     * 取消显示进度
     */
    void cancelProgress();

    /**
     * 显示异常信息
     * @param errorMsg
     */
    void showError(String errorMsg);
}
