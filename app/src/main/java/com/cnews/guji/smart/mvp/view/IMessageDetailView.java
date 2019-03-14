package com.cnews.guji.smart.mvp.view;


import com.cnews.guji.smart.mvp.model.NewsMainModel;

/**
 * author：JSYL-DCL on 2018/9/30
 * 排班任务消息详情
 */

public interface IMessageDetailView extends IBaseView{
    /**
     * 当天排班计划详情
     * @param newsMainModel
     */
    void returnMineNewsChannels(NewsMainModel newsMainModel);
}
