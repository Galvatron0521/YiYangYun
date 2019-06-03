package com.wd.yiyangyun.app;

import android.app.Application;
import android.content.Context;

import com.wd.yiyangyun.utils.CrashHandler;
import com.wd.yiyangyun.utils.SPUtil;
import com.youngfeng.snake.Snake;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

public class DoctorBaseAppliction extends Application{
    public static boolean isTestState = true;
    private static DoctorBaseAppliction mInstance;
    static final int DISK_CACHE_SIZE = 50 * 1024 * 1024; // 50MB
    public static boolean isUpdate = true;
    public static  boolean isAlertInternet=true;
    public static  boolean isAlertWifi=true;
    public static SPUtil spUtil;
    public static OkHttpClient sOkHttpClient;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        spUtil = new SPUtil(this, "fileName");
//        Snake.init(this);

        File cacheDir = new File(DoctorBaseAppliction.mInstance.getCacheDir(), "http");
        Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);
        sOkHttpClient = new OkHttpClient()
                .newBuilder()
                .connectTimeout(1, TimeUnit.MINUTES) //设置连接超时
                .readTimeout(1, TimeUnit.MINUTES) //设置读取超时
                .writeTimeout(1, TimeUnit.MINUTES) //设置写入超时
                //缓存
//                .addInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        int maxAge = 60 * 60; // 有网络时 设置缓存超时时间1个小时
//                        int maxStale = 60 * 60 * 24 * 28; // 无网络时，设置超时为4周
//                        Request request = chain.request();
//                        if (HttpUtils.isNetworkReachable(mInstance)) {
//                            request = request.newBuilder()
//                                    .cacheControl(CacheControl.FORCE_NETWORK)//有网络时只从网络获取
//                                    .build();
//                        } else {
//                            request = request.newBuilder()
//                                    .cacheControl(CacheControl.FORCE_CACHE)//无网络时只从缓存中读取
//                                    .build();
//                        }
//                        Response response = chain.proceed(request);
//                        if (HttpUtils.isNetworkReachable(mInstance)) {
//                            response = response.newBuilder()
//                                    .removeHeader("Pragma")
//                                    .header("Cache-Control", "public, max-age=" + maxAge)
//                                    .build();
//                        } else {
//                            response = response.newBuilder()
//                                    .removeHeader("Pragma")
//                                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
//                                    .build();
//                        }
//                        return response;
//                    }
//
//                })
                .cache(cache)
                .build();

        // 初始化CrashHandler
        Thread.setDefaultUncaughtExceptionHandler(new CrashHandler());


    }

}
