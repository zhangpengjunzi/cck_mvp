package com.sinocall.guess.base;

import android.content.Context;

import com.sinocall.guess.basex.RxManager;

/**
 * Created by Administrator on 2017/6/22.
 */

public abstract class BasePresenter<T,E> {
    public Context mContext;
    public E mModel;
    public T mView;
    public RxManager rxManager=new RxManager();

    public void setVM(T v,E m){
        this.mView=v;
        this.mModel=m;
        onStart();
    }

    public  void  onStart(){}

    public void onDestroy(){
        rxManager.clear();
    }
}
