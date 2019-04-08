package com.keven.jianshu.part1;

import android.app.Activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;

import android.view.View;
import android.widget.Button;

import com.keven.jianshu.utils.LogUtils;
import com.keven.jianshu.R;

public class Part1aActivity extends Activity {

    private Button bt_bind;
    private Button bt_start;
    private Button bt_unbind;
    private View bt_stop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part1a);
        LogUtils.i("B 的 onCreate()");
        initViews();


    }



    private void initViews() {
        bt_bind = findViewById(R.id.bt_bind_service);
        bt_start = findViewById(R.id.bt_start_service);
        bt_stop = findViewById(R.id.bt_stop_service);
        bt_unbind = findViewById(R.id.bt_unbind_service);
        bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进行8.0+ 以上启动服务的适配
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    Intent intent = new Intent(Part1aActivity.this, Part1aService.class);
                    startForegroundService(intent);
                } else {
                    Intent intent = new Intent(Part1aActivity.this, Part1aService.class);
                    startService(intent);
                }

            }
        });
        bt_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Part1aActivity.this, Part1aService.class);
                stopService(intent);
            }
        });
        bt_bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Part1aActivity.this, Part1aService.class);
                bindService(intent, connection, BIND_AUTO_CREATE);
            }
        });
        bt_unbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Part1aActivity.this, Part1aService.class);
                unbindService(connection);
            }
        });
    }

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Part1aService.MyBinder myBinder = (Part1aService.MyBinder) service;
            myBinder.getServiceMethod();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.i("B 的 onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.i("B 的 onResume()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.i("B 的 onRestart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.i("B 的 onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.i("B 的 onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.i("B 的 onDestroy()");

    }
}
