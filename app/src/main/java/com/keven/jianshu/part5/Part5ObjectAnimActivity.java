package com.keven.jianshu.part5;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.keven.jianshu.R;

public class Part5ObjectAnimActivity extends AppCompatActivity {

    private TextView mTv_object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part5_object_anim);

        mTv_object = findViewById(R.id.tv_object);
        Button bt_start=findViewById(R.id.bt_start);
        bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMoneyAnim(1,500);
            }
        });
    }

    private void showMoneyAnim(int mStartValue, int mEndValue) {
        ValueAnimator animator = ValueAnimator.ofInt(mStartValue, mEndValue);
        animator.setDuration(3000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mTv_object.setText(animation.getAnimatedValue().toString());
            }
        });
        animator.start();

    }
}
