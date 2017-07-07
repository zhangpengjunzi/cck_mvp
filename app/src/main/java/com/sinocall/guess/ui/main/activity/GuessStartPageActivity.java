package com.sinocall.guess.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;

import com.sinocall.guess.R;
import com.sinocall.guess.base.BaseActivity;
import com.sinocall.guess.ui.login.activity.GuessLoginActivity;
import com.sinocall.guess.ui.login.bean.GuessLoginBean;
import com.sinocall.guess.utils.ACache;

/**
 * Created by Administrator on 2016/7/8.
 */

public class GuessStartPageActivity extends BaseActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.guess_activity_start_page;
    }

    @Override
    public void initPresenter() {

    }

    public void initView(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goMain();
            }
        },2000);
    }

    //判断是否已经登录过，若登录过，则直接进入主页面
    private void goMain(){
        GuessLoginBean bean= (GuessLoginBean) ACache.get(this).getAsObject("user_info");
        if(bean!=null){
            startActivity(new Intent(this,MainActivity.class));
        }else{
            startActivity(new Intent(this,GuessLoginActivity.class));
        }
        this.finish();
    }



    //这里需要屏蔽返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK)
        {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
