package com.cnews.guji.smart.ui.fragment.temp;

import android.view.View;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.base.BaseFragment;
import com.cnews.guji.smart.mvp.contract.NewsMainContract;
import com.cnews.guji.smart.mvp.model.NewsMainModel;
import com.cnews.guji.smart.mvp.presenter.NewsMainPresenter;
import com.cnews.guji.smart.util.ILog;
import com.google.gson.Gson;

import butterknife.OnClick;

/**
 * des:T
 */
public class PhotosMainFragment_temp1 extends BaseFragment implements NewsMainContract.View {
    private final String TAG1 = PhotosMainFragment_temp1.this.getClass().getSimpleName();
    private static int SIZE = 20;
    private int mStartPage = 1;
    private static final String EXTRA_IS_TRANSPARENT = "is_transparent";
    private NewsMainPresenter  mPresenter;
    private  String html;
//    @BindView(R.id.btnHtml)
//    Button btnHtml;
//    @BindView(R.id.tvHtml)
//    TextView tvHtml;
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
    }

    @Override
    public void initView() {
    }


    @Override
    protected void initData() {
        mPresenter.lodeMineChannelsRequest(getActivity());
    }

    @Override
    protected void initListener() {

    }

    @OnClick({
//            R.id.btnHtml
    })
    public void bindViewClick(View view){
        switch (view.getId()){
//            case R.id.btnHtml:
//                mPresenter.lodeMineChannelsRequest(getActivity());
//                break;
        }
    }

    @Override
    public void returnMineNewsChannels(NewsMainModel newsMainModel) {
        if (newsMainModel != null){
            NewsMainModel.T1348647909107 t1348647909107 = newsMainModel.getT1348647909107().get(3);
            ILog.e(TAG1,"头条-->> T1348647909107"+new Gson().toJson(t1348647909107));
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
        ILog.e(TAG1,"=========>> showError  : "+msg);
    }
}
