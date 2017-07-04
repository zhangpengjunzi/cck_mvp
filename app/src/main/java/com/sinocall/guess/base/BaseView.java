package com.sinocall.guess.base;

/**
 * Created by Administrator on 2017/6/27.
 */

public interface BaseView {
    /*内嵌加载*/
    void showLoading(String title);
    void stopLoading();
    void showTip(String msg);
}
