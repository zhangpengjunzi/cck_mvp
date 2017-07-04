package com.sinocall.guess.ui.login.presenter;


import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.ArrayMap;

import com.sinocall.guess.api.RetrofitClient;
import com.sinocall.guess.base.BaseBean;
import com.sinocall.guess.basex.RxSubscriber;
import com.sinocall.guess.ui.login.contract.GuessLoginContract;
import com.sinocall.guess.utils.RegexUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


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
            mView.showTip("手机格式不正确");
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
                        mView.showLoading("正在加载");
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseBean>(mContext) {
                    @Override
                    protected void _onNext(BaseBean baseBean) {
                        mView.receiveCodeData(baseBean);
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.showTip(message);
                    }
                });
    }

    @Override
    public void login(ArrayMap<String, String> map) {

    }
}
