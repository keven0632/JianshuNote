package com.keven.jianshu;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.keven.jianshu.ioc.IocActivity;
import com.keven.jianshu.part11_retrofit.Part11RetrofitActivity;
import com.keven.jianshu.part7_litepal.LitePalActivity;
import com.keven.jianshu.netUtil.RetrofitManager;
import com.keven.jianshu.part1.MyReceiver;
import com.keven.jianshu.part1.MyReceiver2;
import com.keven.jianshu.part1.Part1dActivity;
import com.keven.jianshu.part2.Part2LayoutActivity;
import com.keven.jianshu.part4.Part4LayoutSeven;
import com.keven.jianshu.part5.Part5RootActivity;
import com.keven.jianshu.part6.Part6ImageActivity;
import com.keven.jianshu.part8_handler.Part8HandlerActivity;
import com.keven.jianshu.skeleton.SkeletonActivity;
import com.keven.jianshu.utils.LogUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private IntentFilter filter;
    private MyReceiver receiver;
    private MyReceiver2 receiver2;
    private IntentFilter filter1;
    private IntentFilter filter2;
    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtils.i("A 的 onCreate()");

        LogUtils.i("A 的 onCreate() 中 savedInstanceState 是空的吗？" + (null == savedInstanceState ? true : false));
        registerMyReceiver();

        TextView tv = findViewById(R.id.click);
        TextView click_part2 = findViewById(R.id.click_part2);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Part1dActivity.class);
                startActivity(intent);
            }
        });
        click_part2.setOnClickListener(this);
        findViewById(R.id.click_part4).setOnClickListener(this);
        findViewById(R.id.click_skeleton).setOnClickListener(this);
        findViewById(R.id.click_animation).setOnClickListener(this);
        findViewById(R.id.click_imageloader).setOnClickListener(this);
        findViewById(R.id.click_litepal).setOnClickListener(this);
        findViewById(R.id.click_ioc).setOnClickListener(this);
        findViewById(R.id.click_handler).setOnClickListener(this);
        findViewById(R.id.click_retrofit).setOnClickListener(this);

        getPermission();

//        getNet();
    }

    private void getNet() {
        RetrofitManager.getInstance().api().getTop250(1, 1).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 获取权限
     */
    private void getPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (this.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            } else {
//                mImei = DeviceUtil.getIMEI(this);
            }
        }
    }

    private void registerMyReceiver() {
        localBroadcastManager = LocalBroadcastManager.getInstance(MainActivity.this);
        filter1 = new IntentFilter();
        filter1.addAction("com.keven.receiver.MY_BROCASTRECEIVER");
        filter1.setPriority(100);
        receiver = new MyReceiver();
        localBroadcastManager.registerReceiver(receiver, filter1);

        filter2 = new IntentFilter();
        filter2.addAction("com.keven.receiver.MY_BROCASTRECEIVER");
        filter2.setPriority(200);
        receiver2 = new MyReceiver2();
        localBroadcastManager.registerReceiver(receiver2, filter2);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        LogUtils.i("A 的 onRestoreInstanceState()");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        LogUtils.i("A 的 onSaveInstanceState()");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.i("A 的 onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.i("A 的 onResume()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.i("A 的 onRestart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.i("A 的 onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.i("A 的 onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.i("A 的 onDestroy()");
        localBroadcastManager.unregisterReceiver(receiver);
        localBroadcastManager.unregisterReceiver(receiver2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.click_part2:
                Intent intent = new Intent(this, Part2LayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.click_part4:
                startActivity(new Intent(this, Part4LayoutSeven.class));
                break;
            case R.id.click_skeleton:
                startActivity(new Intent(this, SkeletonActivity.class));
                break;
            case R.id.click_animation:
                startActivity(new Intent(this, Part5RootActivity.class));
                break;
            case R.id.click_imageloader:
                startActivity(new Intent(this, Part6ImageActivity.class));
                break;
            case R.id.click_litepal:
                startActivity(new Intent(this, LitePalActivity.class));
                break;
            case R.id.click_ioc:
                startActivity(new Intent(this, IocActivity.class));
                break;
            case R.id.click_handler:
                startActivity(new Intent(this, Part8HandlerActivity.class));
                break;
            case R.id.click_retrofit:
                startActivity(new Intent(this, Part11RetrofitActivity.class));
                break;
        }
    }

}
