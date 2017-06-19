package com.sinocall.guess.api;

/**
 * Created by Administrator on 2017/6/19.
 */

public class GuessHostType {

    public static final int Guess_Base=1;
    private static final String Base_Url="http://www.qmguest.com/api";

    public static  String getBase_Url(int hostType){
        String url=null;
        switch (hostType){
            case 1:
                url=Base_Url;
                break;
        }
        return url;
    }
}
