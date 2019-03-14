package com.cnews.guji.smart.common.net;

import android.content.Context;

import com.cnews.guji.smart.api.ApiConstants;
import com.cnews.guji.smart.api.ApiService;
import com.google.gson.Gson;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求框架Retrofit的封装工具类
 */
public class ApiRetrofit {
    private static Context _context;
    private volatile static ApiRetrofit apiRetrofit;
    private static ApiService apiservice;

    public ApiRetrofit(int hostType) {
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(BaseUrl.HTTP_TIME, TimeUnit.MILLISECONDS)
                .readTimeout(BaseUrl.HTTP_TIME, TimeUnit.MILLISECONDS)
                .writeTimeout(BaseUrl.HTTP_TIME, TimeUnit.MILLISECONDS)
                .addInterceptor(new AddCookiesInterceptor(_context))
                .addInterceptor(new SaveCookiesInterceptor(_context))
                .build();
        //retrofit适配器
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.getHost(hostType))
                .addConverterFactory(GsonConverterFactory.create(new Gson()))//添加gson转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//添加rxjava转换器
                .client(mOkHttpClient)
                .build();
        apiservice = mRetrofit.create(ApiService.class);
    }

    /**
     * @param hostType NETEASE_NEWS_VIDEO：1 （新闻，视频），GANK_GIRL_PHOTO：2（图片新闻）;
     *                 EWS_DETAIL_HTML_PHOTO:3新闻详情html图片)
     */
    public static ApiRetrofit getInstence(Context context,int hostType) {
        _context = context;
        apiRetrofit = new ApiRetrofit(hostType);
        if (apiRetrofit == null) {
            synchronized (ApiRetrofit.class) {
            }
        }
        return apiRetrofit;
    }


    public ApiService getApIservice() {
        return apiservice;
    }
}
