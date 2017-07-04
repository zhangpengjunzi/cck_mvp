package com.sinocall.guess.api.api_service;

import android.util.ArrayMap;

import com.sinocall.guess.base.BaseBean;

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
    Observable<BaseBean> getMessage(@QueryMap ArrayMap<String,String> maps);


    @GET("user/login")
    Observable<ResponseBody> login(@QueryMap ArrayMap<String,String> map);
}
