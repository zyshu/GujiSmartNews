package com.cnews.guji.smart.api;


import com.cnews.guji.smart.mvp.model.NewsMainModel;
import com.cnews.guji.smart.ui.model.NewsDetailModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * author：JSYL-DCL
 * 请求方式配置
 */
public interface ApiService {

    /*头条*/
    //http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html
    @GET("nc/article/headline/{id}/{start}-{end}.html")
    Observable<NewsMainModel> getHeaderNews(@Path("id") String id,
                                            @Path("start") int start,
                                            @Path("end") int end);

    /**
     * 新闻详情
     * @param articleUrl
     *          @Url 它允许我们直接传入一个请求的URL。这样以来我们可以将上一个请求的获得的url直接传入进来，baseUrl将被无视
     *           baseUrl 需要符合标准，为空、""、或不合法将会报错
     * @return
     */
    @GET
    Observable<NewsDetailModel> getNewsDetail(@Url String articleUrl);






//    @GET("login")
//    Observable<BaseRespose<User>> login(@Query("username") String username, @Query("password") String password);

//    @GET("nc/article/{postId}/full.html")
//    Observable<Map<String, NewsDetail>> getNewDetail(
//            @Header("Cache-Control") String cacheControl,
//            @Path("postId") String postId);
//
//    @GET("nc/article/{type}/{id}/{startPage}-20.html")
//    Observable<Map<String, List<NewsSummary>>> getNewsList(
//            @Header("Cache-Control") String cacheControl,
//            @Path("type") String type,
//            @Path("id") String id,
//            @Path("startPage") int startPage);
//
//    @GET
//    Observable<ResponseBody> getNewsBodyHtmlPhoto(
//            @Header("Cache-Control") String cacheControl,
//            @Url String photoPath);
//    //@Url，它允许我们直接传入一个请求的URL。这样以来我们可以将上一个请求的获得的url直接传入进来，baseUrl将被无视
//    // baseUrl 需要符合标准，为空、""、或不合法将会报错
//
//    @GET("data/福利/{size}/{page}")
//    Observable<GirlData> getPhotoList(
//            @Header("Cache-Control") String cacheControl,
//            @Path("size") int size,
//            @Path("page") int page);
//
//    @GET("nc/video/list/{type}/n/{startPage}-10.html")
//    Observable<Map<String, List<VideoData>>> getVideoList(
//            @Header("Cache-Control") String cacheControl,
//            @Path("type") String type,
//            @Path("startPage") int startPage);
}
