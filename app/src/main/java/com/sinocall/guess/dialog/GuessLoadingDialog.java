package com.sinocall.guess.dialog;

import android.content.Context;

import com.sinocall.guess.R;
import com.sinocall.guess.utils.ScreenUtils;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**加载dialog
 * Created by Administrator on 2017/7/6.
 */

public class GuessLoadingDialog {

    private static SweetAlertDialog pDialog;
    public static void showLoadingDialog(String message, Context context){
        if(pDialog==null){
            pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
            pDialog.getProgressHelper().setBarColor(R.color.colorAccent);
            pDialog.getProgressHelper().setRimWidth(ScreenUtils.getScreenWidth(context)/2);
            pDialog.setCancelable(false);
        }
        pDialog.setTitleText(message);
        pDialog.show();
    }

    public static void dismissLoadingDialog(){
        if(pDialog!=null&&pDialog.isShowing()){
            pDialog.dismiss();
        }
    }
}
