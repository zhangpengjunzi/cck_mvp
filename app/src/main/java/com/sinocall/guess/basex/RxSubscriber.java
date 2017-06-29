package com.sinocall.guess.basex;

import android.content.Context;
import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017/6/28.
 */

public abstract class RxSubscriber<T> implements Observer<T> {

    private Disposable disposable;
    private Context mContext;

    public RxSubscriber(Context context){
        this.mContext=context;
    }


    @Override
    public void onSubscribe(Disposable d) {
        disposable=d;
    }

    @Override
    public void onNext(T value) {
        _onNext(value);
    }

    @Override
    public void onError(Throwable e) {
        String info=e.getLocalizedMessage();
        Log.e("Error_CCKMVP","Error="+info);
        if(info.toLowerCase().contains("connecttimeoutexception") || info.toLowerCase().contains("sockettimeoutexception")||info.contains("after 10000ms")){
            _onError("网络请求超时！请检查您的网络设置");
        }else if(info.contains("HttpHostConnectException")||info.contains("No address associated")||info.contains("Not Found")){
            _onError("连接服务器出错！请检查您的网络设置");
        }else if(info.contains("Server Error")){
            _onError("服务器出错！请稍后重试");
        }else{
            _onError("请检查您的网络设置");
        }
    }

    @Override
    public void onComplete() {
        disposable.dispose();
    }

    protected abstract void _onNext(T t);

    protected abstract void _onError(String message);
}
