package com.keven.jianshu.part1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.keven.jianshu.utils.LogUtils;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        String value = extras.getString("key");
        LogUtils.i("MyReceiver 收到广播，广播内容是："+value);
    }
}
