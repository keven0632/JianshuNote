package com.keven.jianshu.part1;

import android.app.Activity;
import android.os.Bundle;

import com.keven.jianshu.utils.LogUtils;
import com.keven.jianshu.R;

public class Part1aActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part1a);
        LogUtils.i("B 的 onCreate()");
    }

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
