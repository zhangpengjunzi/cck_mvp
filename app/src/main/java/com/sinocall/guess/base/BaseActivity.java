package com.sinocall.guess.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.gyf.barlibrary.ImmersionBar;
import com.sinocall.guess.app.GuessAppManager;
import com.sinocall.guess.basex.RxManager;
import com.sinocall.guess.utils.TUtils;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/20.
 */

public abstract class BaseActivity<T extends BasePresenter,E extends BaseModel> extends AppCompatActivity {
    public T mPresenter;
    public E mModel;
    public Context context;
    public RxManager mRxManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doBeforeSetContentView();
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initBase();
    }

    /**
     * 设置布局之前运行
     */
    private void doBeforeSetContentView(){
        mRxManager=new RxManager();
        //把activity放到栈中管理
        GuessAppManager.getInstance().addActivity(this);
        /*强制竖屏*/
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //设置状态栏颜色
        ImmersionBar.with(this).init();
    }

    /*初始化present View*/
    private void initBase(){
        context=this;
        mPresenter= TUtils.getT(this,0);
        mModel=TUtils.getT(this,1);
        if(mPresenter!=null){
            mPresenter.mContext=this;
        }
        this.initPresenter();
        this.initView();
    }

    /**********************子类实现**************************/
    //获取布局文件
    public abstract  int getLayoutId();
    //简单页面无需mvp就不用管此方法即可，完美兼容各种实际场景的变通
    public abstract void initPresenter();
    //初始化view
    public abstract void initView();


}
