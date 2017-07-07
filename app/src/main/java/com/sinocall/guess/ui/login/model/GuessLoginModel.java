package com.sinocall.guess.ui.login.model;

import android.util.ArrayMap;

import com.sinocall.guess.api.GuessHostType;
import com.sinocall.guess.api.RetrofitClient;
import com.sinocall.guess.api.api_service.GuessLoginApi;
import com.sinocall.guess.base.BaseBean;
import com.sinocall.guess.basex.RxSchedulers;
import com.sinocall.guess.ui.login.bean.GuessLoginBean;
import com.sinocall.guess.ui.login.contract.GuessLoginContract;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/6/28.
 */

public class GuessLoginModel implements GuessLoginContract.Model {
    @Override
    public Observable<BaseBean> sendCode(ArrayMap<String,String> map) {
        return RetrofitClient.create(GuessLoginApi.class, GuessHostType.Guess_Base).getMessage(map).compose(RxSchedulers.<BaseBean>io_main());
    }

    @Override
    public Observable<GuessLoginBean> login(ArrayMap<String,String> map) {
        return RetrofitClient.create(GuessLoginApi.class,GuessHostType.Guess_Base).login(map)
                .compose(RxSchedulers.<GuessLoginBean>io_main());
    }
}
