package com.sinocall.guess.utils;

import java.lang.reflect.ParameterizedType;

/**
 * Created by Administrator on 2017/6/27.
 * 类转换初始化
 */

public class TUtils {
    public static <T> T getT(Object o,int i){
        try {
            return ((Class<T>)((ParameterizedType)o.getClass().getGenericSuperclass()).getActualTypeArguments()[i]).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
