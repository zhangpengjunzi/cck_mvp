package com.sinocall.guess.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Stack;

/**
 * Created by Administrator on 2017/6/22.
 */

public class GuessAppManager {
    private static Stack<Activity> activityStack;
    private volatile static GuessAppManager instance;

    /**
     * 单一实例
     */
    public static GuessAppManager getInstance() {
        if (null == instance) {
            synchronized (GuessAppManager.class) {
                if (null == instance) {
                    instance = new GuessAppManager();
                    instance.activityStack = new Stack<>();
                }
            }
        }
        return instance;
    }

    /**
     * 添加activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前activity
     */
    public Activity currentActivity() {
        try {
            Activity activity = activityStack.lastElement();
            return activity;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取当前activity的前一个activity
     */
    public Activity preActivity() {
        int index = activityStack.size() - 2;
        if (index < 0) {
            return null;
        }
        return activityStack.get(index);
    }


    /**
     * 结束当前activity
     */
    public void finishCurrentActivity() {
        finishActivity(activityStack.lastElement());
    }

    /**
     * 结束指定的activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }


    /**
     * 结束所有activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size;i++){
            if(null!=activityStack.get(i)){
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 返回到指定的activity
     */
    public  void returnToActivity(Class<?> cls){
        while (activityStack.size()>0){
            if(activityStack.peek().getClass()==cls){
                break;
            }else{
                finishActivity(activityStack.peek());
            }
        }
    }

    /**
     * 判断某个activity是否在栈顶
     */
    public boolean isOpenActivity(Class<?> cls){
        if(activityStack!=null){
            if(cls == activityStack.peek().getClass()){
                return true;
            }
        }
        return false;
    }


    /**
     * 退出应用程序
     * @param context
     * @param isBackground
     */
    public  void AppExit(Context context,Boolean isBackground){
        try {
            finishAllActivity();
            ActivityManager activityMgr= (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());//重启应用
        }catch (Exception e){

        }finally {
            // 注意，如果您有后台程序运行，请不要支持此句子
            if(!isBackground){
                System.exit(0);
            }
        }
    }

}
