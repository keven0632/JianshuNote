package com.keven.jianshu.netUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhengjian on 2019/5/31.
 */
public class RetrofitManager {
    private final static long DEFAULT_TIMEOUT = 30;
    private RequestApis _sApis;
    private Retrofit retrofit;
    private OkHttpClient okHttpClient;
    private static final String BASE_URL="";

    private static class SingletonHolder {
        private static final RetrofitManager INSTANCE = new RetrofitManager();

    }

    public static RetrofitManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private RetrofitManager() {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
//                .addInterceptor(new HeaderInterceptor())//添加公共请求头
//                .addInterceptor(new LoggerInterceptor())//添加日志拦截器
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//添加rxjava转换器
                .client(okHttpClient)
                .build();
        _sApis = retrofit.create(RequestApis.class);
    }

    public RequestApis api() {
        return _sApis;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
