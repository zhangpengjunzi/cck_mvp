package com.sinocall.guess.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2017/6/16.
 */

public class RetrofitClient {
    //请求超时时间
    private static final int DEFAULT_SECOND=20;
    private static Retrofit retrofit;
    private static OkHttpClient okHttpClient;

}
