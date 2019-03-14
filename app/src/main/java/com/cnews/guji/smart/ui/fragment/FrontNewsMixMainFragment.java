package com.cnews.guji.smart.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.support.annotation.ColorInt;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.base.BaseFragment;
import com.cnews.guji.smart.helper.loadmore.CustomLoadMoreView;
import com.cnews.guji.smart.helper.refresh.ypx_refreshlayout.YPXQQRefreshView;
import com.cnews.guji.smart.mvp.contract.NewsMainContract;
import com.cnews.guji.smart.mvp.model.NewsMainModel;
import com.cnews.guji.smart.mvp.presenter.NewsMainPresenter;
import com.cnews.guji.smart.ui.adapter.FrontNewsAdapter;
import com.cnews.guji.smart.ui.contract.FrontNewsContract;
import com.cnews.guji.smart.ui.model.FrontNewsModel;
import com.cnews.guji.smart.ui.presenter.FrontNewsPresenterimpl;
import com.cnews.guji.smart.util.ILog;
import com.cnews.guji.smart.util.statusbar.StatusBarCompatUtils;
import com.github.library.BaseQuickAdapter;
import com.google.gson.Gson;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * des:T
 * 要闻页-要闻界面
 */
public class FrontNewsMixMainFragment extends BaseFragment implements NewsMainContract.View, FrontNewsContract.View, BaseQuickAdapter.RequestLoadMoreListener {
    private final String TAG1 = FrontNewsMixMainFragment.this.getClass().getSimpleName();
    private static int SIZE = 20;
    private int mStartPage = 1;
    private static final String EXTRA_IS_TRANSPARENT = "is_transparent";
    private NewsMainPresenter mPresenter;
    private List<FrontNewsModel.Articles> frontNewsData = new ArrayList<>();
    private FrontNewsAdapter adapter;
    private FrontNewsPresenterimpl _frontNewsPresenterimpl;
    private final int SUCCESS = 1;
    private final int FAILED = 0;
    /**
     * 加载要闻样式标记
     */
    private int flag = 1;
    @BindView(R.id.fake_status_bar)
    View mFakeStatusBar;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.refreshableView1)
    YPXQQRefreshView refreshableView;
    @BindView(R.id.recyclerview1)
    RecyclerView recyclerView;


    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case SUCCESS:
                    refreshableView.finishRefresh(true);
                    break;
                case FAILED:
                    refreshableView.finishRefresh(false);
                    break;
                default:
                    break;
            }
        }

        ;
    };

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_front_news_mix_main;
    }

    @Override
    public void intBase() {
    }

    @Override
    public void initPresenter() {
        mPresenter = new NewsMainPresenter(this);
        _frontNewsPresenterimpl = new FrontNewsPresenterimpl(getActivity(), this);
    }

    @Override
    public void initView() {
        iniRecyclerView();
    }


    @Override
    protected void initData() {
        mPresenter.lodeMineChannelsRequest(getActivity());
        _frontNewsPresenterimpl.getFrontNewsData(flag);
        flag = 0;
    }

    @Override
    protected void initListener() {
        StatusBarCompatUtils.setLightMode(getActivity());
        refreshableView.setRefreshListener(new YPXQQRefreshView.RefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
                        _frontNewsPresenterimpl.getFrontNewsData(flag);
                        if (flag == 0) {
                            flag = 1;
                        } else {
                            flag = 0;
                        }
                    }
                }, 1000);
            }
        });

    }

    @OnClick({
    })
    public void bindViewClick(View view) {
        switch (view.getId()) {
        }
    }

    private void iniRecyclerView() {
        adapter = new FrontNewsAdapter(getActivity(), R.layout.item_fragment_front_news, frontNewsData);
        adapter.setOnLoadMoreListener(this);
        adapter.setEnableLoadMore(true);
        adapter.openLoadAnimation();
        adapter.setLoadMoreView(new CustomLoadMoreView());
//        adapter.closeLoadAnimation();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    private void initNewsData() {
//        if (frontNewsData != null)frontNewsData.clear();
//        for (int i = 0; i < 50; i++) {
//            frontNewsData.add(new FrontNewsModel(AppConstant.FRONT_TOP_IMAGE_URL,i+"【竞猜】NBA常规赛：火箭VS森林狼，分差是奇数还是偶数？"));
//        }
//        adapter.notifyDataSetChanged();
    }


    @Override
    public void returnMineNewsChannels(NewsMainModel newsMainModel) {
        if (newsMainModel != null) {
            NewsMainModel.T1348647909107 t1348647909107 = newsMainModel.getT1348647909107().get(3);
            ILog.e(TAG1, "头条-->> T1348647909107" + new Gson().toJson(t1348647909107));
//            tvHtml.setText("  "+new Gson().toJson(t1348647909107));
        }
    }


    @Override
    public void showLoading(String title) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {
        ILog.e(TAG1, "=========>> showError  : " + msg);
    }

    public void setTvTitleBackgroundColor(@ColorInt int color) {
        mTvTitle.setBackgroundColor(color);
        mFakeStatusBar.setBackgroundColor(color);
    }

    /**
     * 要闻初始化数据
     *
     * @param data
     */
    @Override
    public void setFrontNewsData(FrontNewsModel data) {
        if (data == null) {
            handler.sendEmptyMessage(FAILED);
            return;
        }
        ILog.e("front", "[setFrontNewsData] ===>> :" + new Gson().toJson(data));
        adapter.getData().clear();
        adapter.setNewData(data.getArticles());
        adapter.notifyDataSetChanged();
        intSize = data.getArticles().size();
        handler.sendEmptyMessage(SUCCESS);
    }

    @Override
    public void setFrontNewsWares(FrontNewsModel data) {

    }

    /**
     * 填充列表数据
     *
     * @param find
     */
    private int intSize = 0;
    @Override
    public void setFrontNewsMoreWares(FrontNewsModel data) {
        if (data != null) {
            ILog.e(TAG1,"loadMore:"+new Gson().toJson(data));
            if (data.getArticles() != null) {
                adapter.getData().addAll(data.getArticles());
                adapter.loadMoreComplete();
            }
        }
    }

    /**
     * 加载更多
     */
    @Override
    public void onLoadMoreRequested() {
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                ILog.e(TAG1, "mAdapter.getData().size():" + adapter.getData().size());
                ILog.e(TAG1, "intSize:" + intSize);
                if (adapter.getData().size() >= intSize * 2) {
                    adapter.loadMoreEnd(false);
                } else {
                    _frontNewsPresenterimpl.getFrontNewsMoreWares();
                }
            }
        }, 1000);
    }

}
