package com.sinocall.guess.ui.login.contract;

import android.util.ArrayMap;

import com.sinocall.guess.base.BaseModel;
import com.sinocall.guess.base.BasePresenter;
import com.sinocall.guess.base.BaseView;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/6/28.
 */

public class GuessLoginContract {
    public interface  Model extends BaseModel{
        Observable<ResponseBody> sendCode(ArrayMap<String,String> map);
        Observable<ResponseBody> login(ArrayMap<String,String> map);
    }

    public interface  View extends BaseView{
        void receiveCodeData(RequestBody requestBody);
    }

    public static abstract class Presenter extends BasePresenter<View,Model>{
        //发送验证码
        public abstract void sendCode(String mobile,int type);
        //登录
        public abstract void login(ArrayMap<String,String> map);
    }
}
