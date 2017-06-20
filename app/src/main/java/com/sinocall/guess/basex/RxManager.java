package com.sinocall.guess.basex;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 用于管理单个presenter的RxBus事件和Rxjava相关代码的生命周期处理
 * Created by Administrator on 2017/6/20.
 */

public class RxManager {
    public RxBus rxBus=RxBus.getInstance();
    //管理rxbus订阅
    private Map<String,Observable<?>> mObservables=new HashMap<>();
    //管理Observables和Subscribers订阅
    private CompositeDisposable mCompositeDisposable=new CompositeDisposable();

    /**
     * Rxbus 注入监听
     * @param eventName
     * @param consumer
     * @param <T>
     */
    public <T> void on(String eventName, Consumer<T> consumer){
        Observable<T> mObservable=rxBus.register(eventName);
        mObservables.put(eventName,mObservable);
        /*订阅处理*/
        mCompositeDisposable.add(mObservable.observeOn(AndroidSchedulers.mainThread()).subscribe(consumer, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        }));
    }


    /**
     * 单纯的Observables 和 Subscribers管理
     * @param m
     */
    public void add(Disposable m) {
        /*订阅管理*/
        mCompositeDisposable.add(m);
    }


}
