package com.keven.jianshu.part5;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.keven.jianshu.R;

public class Part5FrameActivity1 extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImageView;
    private AnimationDrawable frameAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part5_frame1);

        mImageView = findViewById(R.id.iv_frame1);
        Button bt_stop = findViewById(R.id.bt_stop);
        Button bt_start = findViewById(R.id.bt_start);
        bt_start.setOnClickListener(this);
        bt_stop.setOnClickListener(this);
        // 通过逐帧动画的资源文件获得AnimationDrawable示例
        frameAnim = (AnimationDrawable) getResources().getDrawable(R.drawable.part5_frame1);
        // 把AnimationDrawable设置为ImageView的背景
        mImageView.setBackgroundDrawable(frameAnim);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_start:
                frameAnim.start();
                break;
            case R.id.bt_stop:
                frameAnim.stop();
                break;
        }
    }
}
