package com.sinocall.guess.api.api_service;

import android.util.ArrayMap;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2017/7/7.
 */

public interface GuessMainApi {
    @GET("sysChildTopic/pkTopicList")
    Observable<ResponseBody> getPkList(@QueryMap ArrayMap<String,String> map);
}
