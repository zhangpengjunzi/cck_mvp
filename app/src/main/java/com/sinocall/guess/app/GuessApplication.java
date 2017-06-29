package com.sinocall.guess.app;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;

import com.sinocall.guess.api.DeviceUtils;
import com.sinocall.guess.utils.ACache;
import com.sinocall.guess.utils.ChannelUtil;
import com.sinocall.guess.utils.NetWorkUtils;
import com.sinocall.guess.utils.ScreenUtils;

/**
 * Created by Administrator on 2017/6/19.
 */

public class GuessApplication extends Application {

    private static GuessApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application=this;
        initHeadParameters();
    }


    public static Context getApplication() {
        return application;
    }

    public static Resources getAppResources(){
        return application.getResources();
    }

    public static ACache getCache(){
        return ACache.get(application);
    }

    private void initHeadParameters(){
        DeviceUtils.did = ChannelUtil.getImei(this) + "_" + Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        DeviceUtils.deviceId = ChannelUtil.getImei(this);
        DeviceUtils.channelId="1";
        DeviceUtils.os=2;
        DeviceUtils.osv= Build.VERSION.RELEASE;
        DeviceUtils.ver= ChannelUtil.getVersionName(this);
        @SuppressLint("WifiManagerLeak") WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        DeviceUtils.mac=info.getMacAddress();
        DeviceUtils.apn=info.getSSID();
        DeviceUtils.lat=0;
        DeviceUtils.lon=0;
        DeviceUtils.net= NetWorkUtils.getProvider(this)+"-"+NetWorkUtils.getCurrentNetworkType(this);
        DeviceUtils.r="w"+ ScreenUtils.getScreenWidth(this)+"h"+ScreenUtils.getScreenHeight(this);
        DeviceUtils.brand= Build.BRAND;
        DeviceUtils.ua=Build.MANUFACTURER+"-"+Build.MODEL;
        DeviceUtils.platform=0;
    }
}
