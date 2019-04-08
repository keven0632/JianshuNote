package com.keven.jianshu.part1;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.keven.jianshu.utils.LogUtils;
import com.keven.jianshu.utils.MyApp;

public class Part1aService extends Service {
    private static final String CHANNEL_ID_STRING = "KEVEN_JIANSHU";

    public Part1aService() {

    }

   private MyBinder binder = new MyBinder();

    @Override
    public IBinder onBind(Intent intent) {
        LogUtils.i("Service 执行了 onBind( )");
        return binder;
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

        //适配8.0+service
        NotificationManager notificationManager = (NotificationManager) MyApp.getInstance().getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel mChannel = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            mChannel = new NotificationChannel(CHANNEL_ID_STRING, "Keven 简书", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(mChannel);
            Notification notification = new Notification.Builder(getApplicationContext(), CHANNEL_ID_STRING).build();
            startForeground(1, notification);
        }

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

    class MyBinder extends Binder {
        public void getServiceMethod() {
            LogUtils.e("调用了 Part1aService 的 getServiceMethod( ) 方法");
        }
    }
}
