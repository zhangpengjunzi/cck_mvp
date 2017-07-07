package com.sinocall.guess.ui.main.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.util.ArrayMap;

import com.sinocall.guess.R;
import com.sinocall.guess.api.GuessHostType;
import com.sinocall.guess.api.RetrofitClient;
import com.sinocall.guess.api.api_service.GuessMainApi;
import com.sinocall.guess.base.BaseActivity;
import com.sinocall.guess.basex.RxSchedulers;
import com.sinocall.guess.basex.RxSubscriber;
import com.sinocall.guess.utils.SnackbarUtils;

import java.io.IOException;

import okhttp3.ResponseBody;

public class MainActivity extends BaseActivity   {

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayMap<String,String> map=new ArrayMap<>();
        map.put("pn","1");
        map.put("ps","10");
        RetrofitClient.create(GuessMainApi.class, GuessHostType.Guess_Base).getPkList(RetrofitClient.getParamMap(map))
                .compose(RxSchedulers.io_main())
                .subscribe(new RxSubscriber<ResponseBody>(this) {
                    @Override
                    protected void _onNext(ResponseBody responseBody) {
                        try {
                            String result=responseBody.string();
                            result="ff";
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        SnackbarUtils.showMyStyle(null,message,SnackbarUtils.SNACK_TYPE_ERROR);
                    }
                });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {

    }
}
