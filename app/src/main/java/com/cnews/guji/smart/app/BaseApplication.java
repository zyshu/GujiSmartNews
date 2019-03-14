package com.cnews.guji.smart.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;

import java.lang.reflect.Field;

/**
 * APPLICATION
 */
public class BaseApplication extends Application {
    private static BaseApplication sInstance;
    private Typeface mtypeface;
    protected static Context context;//上下文
    protected static Handler handler;//主线程Handler
    private static Handler mHandler;//主线程Handler
    private static Thread mMainThread;//主线程
    private static long mMainThreadId;//主线程id
    private static Looper mMainLooper;//循环队列

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        sInstance = this;
        handler = new Handler();
        mHandler = handler;
        changeFontTypaFace();
        Fresco.initialize(this);
        mMainThread = Thread.currentThread();
        mMainThreadId = android.os.Process.myTid();
    }

    public static Context getAppContext() {
        return sInstance;
    }
    public static BaseApplication getAppContext2() {
        return sInstance;
    }

    public static Resources getAppResources() {
        return sInstance.getResources();
    }

    public static BaseApplication getsInstance() {
        return sInstance;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    /**
     * 分包
     *
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(this);
    }

    /**
     * 获取上下文对象
     *
     * @return context
     */
    public static Context getContext() {
        return context;
    }

    /**
     * 获取全局handler
     *
     * @return 全局handler
     */
    public static Handler getHandler() {
        return handler;
    }

    public static void setMainHandler(Handler mHandler) {
        BaseApplication.mHandler = mHandler;
    }

    public static long getMainThreadId() {
        return mMainThreadId;
    }

    public static void setMainThreadId(long mMainThreadId) {
        BaseApplication.mMainThreadId = mMainThreadId;
    }

    public static Thread getMainThread() {
        return mMainThread;
    }

    public static void setMainThread(Thread mMainThread) {
        BaseApplication.mMainThread = mMainThread;
    }

    public static Looper getMainThreadLooper() {
        return mMainLooper;
    }

    public static void setMainThreadLooper(Looper mMainLooper) {
        BaseApplication.mMainLooper = mMainLooper;
    }


    /**
     * 方正宋体
     * @return
     */
    public Typeface getFangZhengSong3() {
        if (mtypeface == null) {
            mtypeface = Typeface.createFromAsset(getAssets(), "fangzhengsongsan.ttf");
        }
        return mtypeface;
    }

    /**
     * 修改字体设置
     */
    public void changeFontTypaFace() {
        Typeface fangZhengSong3 = Typeface.createFromAsset(getAssets(), "fangzhengsongsan.ttf");
        try {
            Field field = Typeface.class.getDeclaredField("SERIF");
            field.setAccessible(true);
            field.set(null, fangZhengSong3);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 加入
     * @param mContext
     * @param key
     * @return
     */
    public static boolean joinQQGroup(Context mContext, String key) {

        Intent intent = new Intent();
        intent.setData(Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D" + key));
        try {
            mContext.startActivity(intent);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 清除缓存
     */
    public static void frescoClearMem(){
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        imagePipeline.clearCaches();
    }



}
