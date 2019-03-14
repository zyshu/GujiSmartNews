package com.cnews.guji.smart.ui.fragment.home;

import android.content.Context;
import android.os.Build;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.base.BaseFragment;
import com.cnews.guji.smart.common.bean.basebean.HomeTophotIndex;
import com.cnews.guji.smart.helper.headerview.HeaderView;
import com.cnews.guji.smart.mvp.presenter.HomeHotTopContract;
import com.cnews.guji.smart.mvp.presenter.impl.HomeHotTopPresenterimpl;
import com.cnews.guji.smart.ui.adapter.HomeHottopMultipleRecycleAdapter;
import com.cnews.guji.smart.ui.constant.NewsConstant;
import com.cnews.guji.smart.util.ILog;
import com.cnews.guji.smart.util.statusbar.StatusBarCompatUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 头条推荐
 */
public class HomeOtherPublicFragment_temp extends BaseFragment implements HeaderView.RefreshDistanceListener,HomeHotTopContract.View {
    private static int SIZE = 20;
    private int mStartPage = 1;
    private Context mContext;
    private static final String TAG = HomeOtherPublicFragment_temp.class.getSimpleName();
    private HomeHottopMultipleRecycleAdapter adapter;
    private HomeHotTopPresenterimpl mPresenter;
    private int pageFrType = 0;
    @BindView(R.id.topbar)
    LinearLayout topbar;
    @BindView(R.id.view2)
    TextView view2;
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.ivTopAd)
    ImageView ivTopAd;
//    @BindView(R.id.rotate_header_list_view_frame)
//    HeaderView mPtrFrame;

    private List<HomeTophotIndex.Articles>  topDatasList;


    public static HomeOtherPublicFragment_temp getInstance(String title, int homeHotType) {
        HomeOtherPublicFragment_temp homeHotTopFragment = new HomeOtherPublicFragment_temp();
        return homeHotTopFragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home_other_public;
    }

    @Override
    public void intBase() {
        if (topDatasList != null) topDatasList.clear();
        else topDatasList = new ArrayList<>();
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void initView() {
        mContext = getActivity();
        //设置滑动沉浸式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//4.4到5.0
            StatusBarCompatUtils.setTintStatusBg(getActivity(), topbar, scrollView);
        }else {
            StatusBarCompatUtils.setTintStatusBg(getActivity(), topbar, scrollView, view2);
        }

        //即可获取焦点。
        ivTopAd.setFocusable(true);
        ivTopAd.setFocusableInTouchMode(true);
        ivTopAd.requestFocus();
        mPresenter = new HomeHotTopPresenterimpl(mContext,this);
        //初始化头部刷新
        initPtrFrame();
        //初始化列表
        initRecyclerView();

    }

    private void initRecyclerView() {
        //取消焦点滑动，解决卡顿
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(10);
//        mRecyclerView.addItemDecoration(new SpaceItemDecoration1(DensityUtil.dip2px(mContext,3)));
        adapter = new HomeHottopMultipleRecycleAdapter(mContext,topDatasList);
        //防止数据错乱
        adapter.setHasStableIds(true);
//        adapter.setOnLoadMoreListener(this);
//        adapter.setEnableLoadMore(true);
        mRecyclerView.setAdapter(adapter);
        mPresenter.getHomeHotTopData(NewsConstant.FLAG_NEWS,pageFrType);
//        flag = 0;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }


    /**
     * 初始化下拉刷新
     */
    private void initPtrFrame() {
//        mPtrFrame.setOnRefreshDistanceListener(this);
//        mPtrFrame.setPtrHandler(new PtrHandler() {
//            @Override
//            public void onRefreshBegin(PtrFrameLayout frame) {
//                updateData();
//            }
//        });

        // 是否进入页面就开始显示刷新动作
        /*mPtrFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPtrFrame.autoRefresh();
            }
        }, 100);*/
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            ILog.e(TAG,"setUserVisibleHint22:"+isVisibleToUser);
            // 相当于onResume()方法--获取焦点
        } else {
            ILog.e(TAG,"else setUserVisibleHint22:"+isVisibleToUser);
            // 相当于onpause()方法---失去焦点
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden) {
            ILog.e(TAG,"hidden22:"+hidden);
            //相当于Fragment的onPause()
        } else {
            ILog.e(TAG,"else hidden22:"+hidden);
            // 相当于Fragment的onResume()
        }
    }

    /**
     * 下拉后刷新数据
     */
    private void updateData() {
//        mPtrFrame.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mPresenter.getHomeHotTopData();
//            }
//        }, 1000);
    }


    @Override
    public void onPositionChange(int currentPosY) {

    }

//    @Override
//    public void onLoadMoreRequested() {
//        mRecyclerView.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (adapter.getData().size() >= 90){
//                    adapter.loadMoreEnd(false);
//                }
//                else{
//                    mPresenter.getHomeHotTopWares();
//                }
//            }
//        },1000);
//    }


    @Override
    public void setHomeHotTopData(HomeTophotIndex tophotBean) {
        if(tophotBean == null){
//            mPtrFrame.refreshComplete();
            return;
        }
        ILog.e("HomeHottop","[setHomeHotTopData] ===>> :"+new Gson().toJson(tophotBean));
        adapter.getData().clear();
        adapter.setNewData(tophotBean.getArticles());
//        mPtrFrame.refreshComplete();

        adapter.notifyDataSetChanged();

//        if (tophotBean != null){
//            List<HomeTophotIndex.Articles> articles = tophotBean.getArticles();
//            topDatasList.addAll(articles);
//            adapter.notifyDataSetChanged();
//        }
    }

    @Override
    public void setHomeHotTopWares(HomeTophotIndex tophotBean) {
        adapter.getData().addAll(tophotBean.getArticles());
        adapter.loadMoreComplete();
    }

    @Override
    public void setMoreHomeHotTopWares(HomeTophotIndex tophotBean) {
        adapter.getData().addAll(tophotBean.getArticles());
        adapter.loadMoreComplete();
    }

    public void setCurrentPageType(int videoType){
        pageFrType = videoType;
//        mSwipeToLoadLayout.setRefreshing(true);
        ILog.e("home", "-------------[pageFrType] ===>>222222 :" + pageFrType);
    }
}
