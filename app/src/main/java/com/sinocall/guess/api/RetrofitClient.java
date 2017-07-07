package com.sinocall.guess.api;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.ArrayMap;
import android.util.SparseArray;

import com.google.gson.GsonBuilder;
import com.sinocall.guess.app.GuessApplication;
import com.sinocall.guess.security.MD5Utils;
import com.sinocall.guess.ui.login.bean.GuessLoginBean;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * Created by Administrator on 2017/6/16.
 */

public class RetrofitClient {
    //请求超时时间
    private static final int DEFAULT_SECOND=15;
    private static Retrofit retrofit;
    private  OkHttpClient okHttpClient;
    //用来切换baseUrl
    private static SparseArray<RetrofitClient> clientManager=new SparseArray<>();
    //构造
    private RetrofitClient(int baseType){
        //开启log
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //缓存
        File cacheFile=new File(GuessApplication.getApplication().getCacheDir(),"cache");
        Cache cache=new Cache(cacheFile,100*1024*1024);
        //增加头部信息
        Interceptor headerInterceptor=new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder builder=chain.request().newBuilder();
                Set<String> keySet=addHeads().keySet();
                for(String key:keySet){
                    builder.addHeader(key,addHeads().get(key)).build();
                }
                return chain.proceed(builder.build());
            }
        };

        okHttpClient=new OkHttpClient().newBuilder()
                .readTimeout(DEFAULT_SECOND, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_SECOND, TimeUnit.SECONDS)
                .connectTimeout(DEFAULT_SECOND, TimeUnit.SECONDS)
                .addInterceptor(headerInterceptor)
                .addInterceptor(loggingInterceptor)
                .cache(cache)
                .build();

        retrofit=new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(GuessHostType.getBase_Url(baseType))
                .build();
    }

    public  static  <T> T create(final Class<T> tClass,int baseType){
        RetrofitClient client=clientManager.get(baseType);
        if(client==null){
            client=new RetrofitClient(baseType);
            clientManager.put(baseType,client);
        }
        return retrofit.create(tClass);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static ArrayMap getParamMap(ArrayMap<String,String> map){
        if(map==null){
            map=new ArrayMap<>();
        }
        map.put("v", DeviceUtils.v);
        long currentTime = System.currentTimeMillis();
        map.put("t", "" + currentTime);
        map.put("s", getMD5(map));
        return map;
    }

    private static String getMD5(ArrayMap<String,String> params) {
        if (params == null || params.size() == 0) {
            return null;
        }
        List<String> keys = new ArrayList<>();
        for (String key : params.keySet()) {
            keys.add(key);
        }
        Collections.sort(keys);
        StringBuilder sb = new StringBuilder();
        int size = 0;
        for (String key : keys) {
            if (!key.equals("s")) {
                sb.append(key).append("=").append(params.get(key)).append('&');
                size++;
            }
        }
        sb.append("xxxwwwa8");
        sb.append("=");
        sb.append(200 + size);
        return MD5Utils.hexdigest(sb.toString());
    }

    //请求头添加
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private ArrayMap<String,String> addHeads(){
        ArrayMap<String,String> heads=new ArrayMap<>();
        heads.put("did", DeviceUtils.did);
        heads.put("deviceId", DeviceUtils.deviceId);
        heads.put("mac", DeviceUtils.mac);
        heads.put("apn", DeviceUtils.apn);
        heads.put("lat", "" + DeviceUtils.lat);
        heads.put("lon", "" + DeviceUtils.lon);
        heads.put("net", DeviceUtils.net);
        heads.put("ua", DeviceUtils.ua);
        heads.put("os", DeviceUtils.os + "");
        heads.put("osv", DeviceUtils.osv);
        heads.put("ver", DeviceUtils.ver);
        heads.put("r", DeviceUtils.r);
        heads.put("channelid",DeviceUtils.channelId);
        heads.put("brand", DeviceUtils.brand);
        GuessLoginBean loginBean= (GuessLoginBean) GuessApplication.getCache().getAsObject("user_info");
        if (loginBean !=null) {
            heads.put("userId",String.valueOf(loginBean.getData().getUserId()));
            heads.put("lId", loginBean.getData().getLId());
            heads.put("platform",GuessApplication.getCache().getAsString("platform"));
        } else {
            heads.put("userId",String.valueOf(0));
            heads.put("lId", "");
            heads.put("platform", String.valueOf(0));
        }
        return heads;
    }
}
