package com.keven.jianshu.utils;

import android.util.Log;

public class LogUtils {
    private  static final String TAG="TAG";

    public static void i(String message){
        Log.i(TAG,message);
    }
    public static void e(String message){
        Log.e(TAG,message);
    }
}
