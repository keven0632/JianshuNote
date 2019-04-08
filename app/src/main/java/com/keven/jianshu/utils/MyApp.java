package com.keven.jianshu.utils;

import android.app.Application;
import android.content.Context;

public class MyApp extends Application {

    private static MyApp myApp = null;

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
    }

    public static MyApp getInstance() {
        return myApp;
    }
}
