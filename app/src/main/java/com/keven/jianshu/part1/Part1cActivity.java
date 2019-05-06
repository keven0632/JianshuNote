package com.keven.jianshu.part1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;

import com.keven.jianshu.R;



public class Part1cActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part1c);

        LocalBroadcastManager localBroadcastManager=LocalBroadcastManager.getInstance(this);
        Intent intent = new Intent("com.keven.receiver.MY_BROCASTRECEIVER");
//        intent.setComponent(new ComponentName("com.keven.jianshu",
//                "com.keven.jianshu.part1.MyReceiver"));
        Bundle bundle=new Bundle();
        bundle.putString("key","Hello Receiver！");
        intent.putExtras(bundle);
        localBroadcastManager.sendBroadcast(intent);
    }
}
