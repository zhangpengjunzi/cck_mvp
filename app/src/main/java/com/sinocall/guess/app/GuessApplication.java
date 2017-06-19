package com.sinocall.guess.app;

import android.app.Application;
import android.content.res.Resources;

/**
 * Created by Administrator on 2017/6/19.
 */

public class GuessApplication extends Application {

    private static GuessApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application=this;
    }

    public static GuessApplication getApplication() {
        return application;
    }

    public static Resources getAppResources(){
        return application.getResources();
    }
}
