package com.sinocall.guess.api.api_service;

import android.util.ArrayMap;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2017/6/28.
 */

public interface GuessLoginApi {
    /**
     *获取验证码
     * @return
     */
    @GET("common/sendSms")
    Observable<ResponseBody> getMessage(@QueryMap ArrayMap<String,String> map);

    @GET("user/login")
    Observable<ResponseBody> login(@QueryMap ArrayMap<String,String> map);
}
