package com.cnews.guji.smart.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.DisplayMetrics;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.cnews.guji.smart.R;
import com.cnews.guji.smart.base.BaseRxActivity;
import com.cnews.guji.smart.helper.glide.GlideRoundTransform;
import com.cnews.guji.smart.helper.texthtml.HtmlImageLoader;
import com.cnews.guji.smart.helper.texthtml.HtmlText;
import com.cnews.guji.smart.helper.texthtml.OnTagClickListener;
import com.cnews.guji.smart.ui.contract.NewsDetailContract;
import com.cnews.guji.smart.ui.model.NewsDetailModel;
import com.cnews.guji.smart.ui.presenter.NewsDetailPresenter;
import com.cnews.guji.smart.util.DateTimeUtils;
import com.cnews.guji.smart.util.ILog;
import com.cnews.guji.smart.util.ToastUitl;
import com.cnews.guji.smart.util.statusbar.StatusBarCompatUtils;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

/**
 * author：JSYL-DCL on 2019/1/29
 * 新闻详情界面
 */
public class HotNewsDetailActivity extends BaseRxActivity implements NewsDetailContract.View {
    private static final String TAG1 = HotNewsDetailActivity.class.getSimpleName();
    private NewsDetailPresenter _presenter;
    private static String dataUrl = "";
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvHtml)
    TextView tvHtml;
    @BindView(R.id.tvDatesSource)
    TextView tvDatesSource;

    public static void newInstance(Context context, String data) {
        context.startActivity(new Intent(context,HotNewsDetailActivity.class));
        dataUrl = data;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void getBundleExtras(Intent intent) {

    }



    @Override
    public int getLayoutId() {
        return R.layout.activity_hotnews_detail_normal;
    }

    @Override
    public void initPresenter() {
        mContext = HotNewsDetailActivity.this;
        String testUrl = "http://222.44.126.221:8180/standardpto-rest/api/messagePush/getmessage?userid=30010";
        _presenter = new NewsDetailPresenter(this);
        ILog.e(TAG1,"dataUrl ==>:"+dataUrl);
        if (!TextUtils.isEmpty(dataUrl)) {
            _presenter.deatailItemRequest(mContext, dataUrl);
        }else {
            ToastUitl.showShort("详情地址为空");
        }
    }

    @Override
    public void initView(Bundle savedInstanceState) {
    }

    /**
     * 加载Html
     * @param content
     */
    String contentStr = "";
    private void loadTextHtml(String content) {
        tvHtml.setMovementMethod(LinkMovementMethod.getInstance());
        if (TextUtils.isEmpty(content)) {
            contentStr = getSample(R.raw.hotnews_detail);
        }else {
            contentStr = content;
        }
        ILog.e(TAG1,"新闻详情loadTextHtml ==>:"+contentStr);
        HtmlText.from(contentStr)
                .setImageLoader(new HtmlImageLoader() {
                    @Override
                    public void loadImage(String url, final Callback callback) {
                        // Glide sample, you can also use other image loader
                        if (!url.contains("http")){
                            url = "http:"+url;
                        }
                        Glide.with(mContext)
                                .load(url)
                                .asBitmap()
                                .override(getTextWidth(),600)
//                                .centerCrop()
                                .transform(new CenterCrop(mContext), new GlideRoundTransform(mContext,5))
                                .into(new SimpleTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(Bitmap resource,
                                                                GlideAnimation<? super Bitmap> glideAnimation) {
                                        callback.onLoadComplete(resource);
                                    }

                                    @Override
                                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                                        callback.onLoadFailed();
                                    }
                                });
                    }

                    @Override
                    public Drawable getDefaultDrawable() {
                        return ContextCompat.getDrawable(mContext, R.drawable.default_img_guji);
                    }

                    @Override
                    public Drawable getErrorDrawable() {
                        return ContextCompat.getDrawable(mContext, R.drawable.default_img_guji);
                    }

                    @Override
                    public int getMaxWidth() {
                        return getTextWidth();
                    }

                    @Override
                    public boolean fitWidth() {
                        return false;
                    }
                })
                .setOnTagClickListener(new OnTagClickListener() {
                    @Override
                    public void onImageClick(Context context, List<String> imageUrlList, int position) {
                        Toast.makeText(context, "image click, position: " + position + ", url: " + imageUrlList.get(position), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onLinkClick(Context context, String url) {
                        Toast.makeText(context, "url click: " + url, Toast.LENGTH_SHORT).show();
                        try {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(url));
                            context.startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                })
                .into(tvHtml);
    }

    @Override
    public void initListener() {

    }


    @Override
    protected void setStatusBar() {
        int mStatusBarColor = getResources().getColor(R.color.white);
        StatusBarUtil.setColor(HotNewsDetailActivity.this, mStatusBarColor, 0);
//        mToolbar.setBackgroundColor(mStatusBarColor);
        StatusBarCompatUtils.setLightMode(HotNewsDetailActivity.this);
    }

    private String getSample(int res) {
        try {
            InputStream is = getResources().openRawResource(res);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private int getTextWidth() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        return dm.widthPixels - tvHtml.getPaddingLeft() - tvHtml.getPaddingRight();
    }


    /**
     * 新闻详情
     * @param data
     */
    @Override
    public void detailItemData(NewsDetailModel data) {
        ILog.e(TAG1,"新闻详情detailItemData ==>:"+new Gson().toJson(data));
        if (data != null){
            String content = data.content;
            loadTextHtml(content);
            tvTitle.setText(data.title == null ? "" : data.title);
            tvDatesSource.setText(String.format(Locale.getDefault(),data.media.name+" | %s",DateTimeUtils.stampToTime(data.publish_time)));
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

    }
}
