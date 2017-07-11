package com.sinocall.guess.ui.login.presenter;


import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.ArrayMap;

import com.sinocall.guess.api.RetrofitClient;
import com.sinocall.guess.base.BaseBean;
import com.sinocall.guess.basex.RxSubscriber;
import com.sinocall.guess.ui.login.bean.GuessLoginBean;
import com.sinocall.guess.ui.login.contract.GuessLoginContract;
import com.sinocall.guess.utils.RegexUtil;
import com.sinocall.guess.utils.SnackbarUtils;

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
            mView.showTip("手机格式不正确",SnackbarUtils.SNACK_TYPE_ERROR);
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
                        mView.showTip(message,SnackbarUtils.SNACK_TYPE_ERROR);
                    }

                    @Override
                    protected void _onSubscribe(Disposable disposable) {
                        rxManager.add(disposable);
                    }
                });
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void login(String mobile, String code) {
        //判断手机是否可用
        if(!RegexUtil.checkPhone(mobile)){
            mView.showTip("手机格式不正确", SnackbarUtils.SNACK_TYPE_ERROR);
            return;
        }
        //判断手机是否可用
        if(TextUtils.isEmpty(code)){
            mView.showTip("验证码不能为空",SnackbarUtils.SNACK_TYPE_ERROR);
            return;
        }
        ArrayMap<String,String> map=new ArrayMap<>();
        map.put("mobile",mobile);
        map.put("validateCode",code);
        map.put("getuiToken","6f4c6527e7bde99e26d503d37c5d7592");
        map.put("jpushTocken", "");
        mModel.login(RetrofitClient.getParamMap(map))
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mView.showLoading("正在登录...");
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<GuessLoginBean>(mContext) {
                    @Override
                    protected void _onNext(GuessLoginBean responseBody) {
                        mView.login(responseBody);
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.showTip(message,SnackbarUtils.SNACK_TYPE_ERROR);
                    }

                    @Override
                    protected void _onSubscribe(Disposable disposable) {
                        rxManager.add(disposable);
                    }
                });
    }


}
