package com.sinocall.guess.ui.login.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinocall.guess.R;
import com.sinocall.guess.base.BaseActivity;
import com.sinocall.guess.base.BaseBean;
import com.sinocall.guess.ui.login.contract.GuessLoginContract;
import com.sinocall.guess.ui.login.model.GuessLoginModel;
import com.sinocall.guess.ui.login.presenter.GuessLoginPresenter;
import com.sinocall.guess.widget.MyEditText;
import com.zhy.android.percent.support.PercentLinearLayout;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 登录页面
 * Created by Administrator on 2017/2/10.
 */

public class GuessLoginActivity extends BaseActivity<GuessLoginPresenter,GuessLoginModel> implements GuessLoginContract.View {


    @BindView(R.id.user_phone)
    MyEditText userPhone;
    @BindView(R.id.user_code)
    MyEditText userCode;
    @BindView(R.id.send_code)
    TextView sendCode;
    @BindView(R.id.login_btn)
    TextView loginBtn;
    @BindView(R.id.sino_login)
    ImageView sinoLogin;
    @BindView(R.id.weachat_login)
    ImageView weachatLogin;
    @BindView(R.id.qq_login)
    ImageView qqLogin;
    @BindView(R.id.login_linear)
    PercentLinearLayout loginLinear;
    @BindView(R.id.login_user_agree)
    TextView loginUserAgree;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.guess_activity_login;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    public void initView() {

    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    @OnClick({R.id.login_btn, R.id.sino_login, R.id.weachat_login, R.id.qq_login, R.id.login_user_agree,R.id.send_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                break;
            case R.id.sino_login:
                break;
            case R.id.weachat_login:
                break;
            case R.id.qq_login:
                break;
            case R.id.login_user_agree:
                break;
            case R.id.send_code:
                mPresenter.sendCode(userPhone.getText().toString(),1);
                break;
        }
    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showTip(String msg) {
    }

    @Override
    public void receiveCodeData(BaseBean baseBean) {
        if(){}
    }
}
