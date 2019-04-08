package com.keven.jianshu.part1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.keven.jianshu.utils.LogUtils;

public class MyReceiver2 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        String value = extras.getString("key");
        LogUtils.i("MyReceiver2 收到广播，广播内容是："+value);
        //如果在接收器中调用 abortBroadcast( ) 方法，则之后的接收器无法收到广播
        abortBroadcast();
    }
}
