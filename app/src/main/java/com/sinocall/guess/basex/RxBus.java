package com.sinocall.guess.basex;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;


/**
 * 使用Rxjava实现的EventBus
 * Created by Administrator on 2017/6/20.
 */

public class RxBus {
    private static RxBus instance;

    public static synchronized RxBus getInstance(){
        if(null==instance){
            instance=new RxBus();
        }
        return instance;
    }

    //ConcurrentHashMap 在线程安全的基础上提供了更好的写并发能力，但同时降低了对读一致性的要求
    private ConcurrentHashMap<Object,List<Subject>> subjectManager=new ConcurrentHashMap<>();

    /**
     * 订阅事件源
     * @param mObservable
     * @param consumer
     * @return
     */
    public RxBus OnEvent(Observable<?> mObservable, Consumer<Object> consumer){
        mObservable.observeOn(AndroidSchedulers.mainThread()).subscribe(consumer, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        });
        return instance;
    }


    /**
     * 注册事件源
     * @param tag
     * @param <T>
     * @return
     */
    public <T> Observable<T> register(Object  tag){
        List<Subject> subjectList=subjectManager.get(tag);
        if(null==subjectList){
            subjectList=new ArrayList<>();
            subjectManager.put(tag,subjectList);
        }
        Subject<T> subject;
        subjectList.add(subject = PublishSubject.create());
        return subject;
    }


    /**
     * 取消监听
     * @param tag
     * @param observable
     * @return
     */
    public RxBus unregister(Object tag,Observable<?> observable){
        if(null==observable)
            return getInstance();
        List<Subject> subjects=subjectManager.get(tag);
        if(null!=subjects){
            subjects.remove(observable);
            if(isEmpty(subjects)){
                subjectManager.remove(tag);
            }
        }
        return getInstance();
    }

    /**
     * 触发事件
     * @param tag
     * @param content
     */
    public void post(Object tag,Object content){
        List<Subject> subjects=subjectManager.get(tag);
        if(!isEmpty(subjects)){
            for(Subject subject:subjects){
                subject.onNext(content);
            }
        }
    }

    private boolean isEmpty(Collection<Subject> collection){
        return null==collection||collection.isEmpty();
    }

}
