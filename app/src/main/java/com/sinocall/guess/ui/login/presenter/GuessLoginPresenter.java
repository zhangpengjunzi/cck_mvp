package com.sinocall.guess.ui.login.presenter;


import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.ArrayMap;
import android.widget.Toast;

import com.sinocall.guess.api.RetrofitClient;
import com.sinocall.guess.ui.login.contract.GuessLoginContract;
import com.sinocall.guess.utils.RegexUtil;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;


/**
 * Created by Administrator on 2017/6/28.
 */

public class GuessLoginPresenter extends GuessLoginContract.Presenter {

    /**
     * 发送验证码
     * @param mobile
     * @param type
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void sendCode(String mobile,int type) {
        //判断手机是否可用
        if(!RegexUtil.checkPhone(mobile)){
            mView.showErrorTip("手机格式不正确");
            return;
        }
        //发送验证码
        ArrayMap<String,String> map=new ArrayMap<>();
        map.put("mobile",mobile);
        map.put("type",String.valueOf(type));
        mModel.sendCode(RetrofitClient.getParamMap(map))
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mView.showLoading("正在加载");//准备工作
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody value) {
                        try {
                            String body=value.string();
                            body="123";
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        String error=e.getLocalizedMessage();
                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(mContext,"完成",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void login(ArrayMap<String, String> map) {

    }
}
