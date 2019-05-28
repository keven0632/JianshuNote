package com.keven.jianshu.part5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.keven.jianshu.R;

public class Part5RootActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part5_root);

        findViewById(R.id.tv_frame_animation1).setOnClickListener(this);
        findViewById(R.id.tv_frame_animation2).setOnClickListener(this);
        findViewById(R.id.tv_view_animation1).setOnClickListener(this);
        findViewById(R.id.tv_object_animation1).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_frame_animation1:
                startActivity(new Intent(this,Part5FrameActivity1.class));
                break;
            case R.id.tv_frame_animation2:
                startActivity(new Intent(this,Part5FrameJavaActivity.class));
                break;
            case R.id.tv_view_animation1:
                startActivity(new Intent(this,Part5ViewAnimActivity.class));
                break;
            case R.id.tv_object_animation1:
                startActivity(new Intent(this,Part5ObjectAnimActivity.class));
                break;
        }
    }
}
