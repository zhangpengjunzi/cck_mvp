package com.sinocall.guess.ui.login.contract;

import android.util.ArrayMap;

import com.sinocall.guess.base.BaseBean;
import com.sinocall.guess.base.BaseModel;
import com.sinocall.guess.base.BasePresenter;
import com.sinocall.guess.base.BaseView;
import com.sinocall.guess.ui.login.bean.GuessLoginBean;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/6/28.
 */

public class GuessLoginContract {
    public interface  Model extends BaseModel{
        Observable<BaseBean> sendCode(ArrayMap<String,String> map);
        Observable<GuessLoginBean> login(ArrayMap<String,String> map);
    }

    public interface  View extends BaseView{
        void receiveCodeData(BaseBean baseBean);
        void login(GuessLoginBean loginBean);
    }

    public static abstract class Presenter extends BasePresenter<View,Model>{
        //发送验证码
        public abstract void sendCode(String mobile,int type);
        //登录
        public abstract void login(String mobile,String code);
    }
}
