package com.keven.jianshu.part5;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.keven.jianshu.R;

public class Part5FrameJavaActivity extends AppCompatActivity implements View.OnClickListener {

    private View mImageView;
    private AnimationDrawable frameAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part5_frame_java);

        mImageView = findViewById(R.id.iv_frame1);
        Button bt_stop = findViewById(R.id.bt_stop);
        Button bt_start = findViewById(R.id.bt_start);
        bt_start.setOnClickListener(this);
        bt_stop.setOnClickListener(this);

        frameAnim = new AnimationDrawable();
        // 为AnimationDrawable添加动画帧
        frameAnim.addFrame(getResources().getDrawable(R.drawable.uncheck_in1), 200);
        frameAnim.addFrame(getResources().getDrawable(R.drawable.uncheck_in2), 200);
        frameAnim.addFrame(getResources().getDrawable(R.drawable.uncheck_in3), 200);
        frameAnim.addFrame(getResources().getDrawable(R.drawable.uncheck_in4), 200);
        frameAnim.addFrame(getResources().getDrawable(R.drawable.uncheck_in5), 200);
        frameAnim.addFrame(getResources().getDrawable(R.drawable.uncheck_in6), 200);
        frameAnim.setOneShot(false);

        mImageView.setBackground(frameAnim);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_start:
                if (frameAnim != null && !frameAnim.isRunning()) {
                    frameAnim.start();
                }
                break;
            case R.id.bt_stop:
                if (frameAnim != null && frameAnim.isRunning()) {
                    frameAnim.stop();
                }
                break;
        }

    }
}
