package com.keven.jianshu.ioc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by zhengjian on 2019/6/3.
 */
public class BaseIocActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectUtils.inject(this);
    }
}
