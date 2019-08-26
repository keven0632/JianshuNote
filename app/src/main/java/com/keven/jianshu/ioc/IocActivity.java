package com.keven.jianshu.ioc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.keven.jianshu.R;

@KevenContentViewInject(R.layout.activity_ioc)
public class IocActivity extends AppCompatActivity {

    @KevenViewInject(R.id.tv_title)
    private TextView tv_title;
    @KevenViewInject(R.id.bt_pop)
    private Button bt_pop;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectUtils.inject(this);
    }

    @KevenOnClickInject(R.id.bt_pop)
    public void change(){
        tv_title.setText("hello IOC");
        Toast.makeText(this,"Hello IOC",Toast.LENGTH_SHORT).show();
    }
}
