package com.example.admin.accessibilityservicetest.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liukan on 2018/3/19.
 */

public class RequestManager {
    /*超时时间*/
    public static final int TIME_OUT_MS = 30 * 1000;
    private static volatile RequestManager mRequestManager;
    private Retrofit mRetrofit;
    private OkHttpClient mClient;

    private RequestManager() {
        mRetrofit = new Retrofit.Builder()
                .client(getClient())
                .baseUrl("http://openapi.tuling123.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static RequestManager getInstance() {
        if (mRequestManager == null) {
            synchronized (RequestManager.class) {
                if (mRequestManager == null) {
                    mRequestManager = new RequestManager();
                }
            }
        }
        return mRequestManager;
    }

    public <T> T create(Class<T> service){
        return mRetrofit.create(service);
    }

    /**
     * 初始化HttpClient
     */
    private void creatClient () {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
                .readTimeout(TIME_OUT_MS, TimeUnit.MILLISECONDS)
                .connectTimeout(TIME_OUT_MS, TimeUnit.MILLISECONDS)
                .writeTimeout(TIME_OUT_MS, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(false)
                ;//失败重连
        mClient = httpClientBuilder.build();
    }

    public OkHttpClient getClient () {
        if (mClient == null) {
            creatClient();
        }
        return mClient;
    }

}
