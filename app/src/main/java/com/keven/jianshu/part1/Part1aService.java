package com.keven.jianshu.part1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.keven.jianshu.utils.LogUtils;

public class Part1aService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        LogUtils.i("Service 执行了 onBind( )");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        LogUtils.i("Service 执行了 onUnbind( )");
        return super.onUnbind(intent);

    }

    @Override
    public void onCreate() {
        LogUtils.i("Service 执行了 onCreate( )");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.i("Service 执行了 onStartCommand( )");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        LogUtils.i("Service 执行了 onDestroy( )");
        super.onDestroy();
    }
}
