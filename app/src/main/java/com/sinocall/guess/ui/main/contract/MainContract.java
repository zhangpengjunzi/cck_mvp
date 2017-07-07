package com.sinocall.guess.ui.main.contract;

import com.sinocall.guess.base.BaseModel;
import com.sinocall.guess.base.BasePresenter;
import com.sinocall.guess.base.BaseView;

/**
 * Created by Administrator on 2017/7/7.
 */

public class MainContract {
    public interface View extends BaseView{

    }
    public interface Model extends BaseModel{

    }
    public abstract class Presenter extends BasePresenter<View,Model>{

    }
}
