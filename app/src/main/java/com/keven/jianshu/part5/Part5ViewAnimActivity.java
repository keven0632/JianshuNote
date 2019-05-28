package com.keven.jianshu.part5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.keven.jianshu.R;

public class Part5ViewAnimActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part5_view_anim);

        ImageView iv_bee=findViewById(R.id.iv_bee);
        Animation loadAnimation = AnimationUtils.loadAnimation(this,
                R.anim.part5view_anim1);
        loadAnimation.setFillAfter(true);
        iv_bee.startAnimation(loadAnimation);
    }
}
