package com.keven.jianshu.part4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.keven.jianshu.R;


public class Part4AnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part4_animation);


        final ImageView iv_animation = findViewById(R.id.iv_animation);
        iv_animation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyRotation(false, iv_animation, 0, -90);
            }
        });
    }

    /**
     * 设置一个新的三维旋转的容器视图。只翻一般，然后设置新的现实内容
     *
     * @param zheng 一个判断机制 如果为true 则向右翻转，如果false则向左翻转
     * @param view  传入的片段
     * @param start 起始位置
     * @param end   结束位置
     */
    public void applyRotation(final boolean zheng, final View view,
                              final float start, final float end) {
        // Find the center of the container
        final float centerX = view.getWidth() / 2.0f;
        final float centerY = view.getHeight() / 2.0f;

        // Create a new 3D rotation with the supplied parameter
        // The animation listener is used to trigger the next animation
        final Util_Rotate3DAnimation rotation = new Util_Rotate3DAnimation(
                start, end, centerX, centerY, 310.0f, true);
        rotation.setDuration(500);
        rotation.setFillAfter(true);
        rotation.setInterpolator(new AccelerateInterpolator());
        rotation.setAnimationListener(new DisplayNextView(zheng, view));// 添加监听执行现实内容的切换
        view.startAnimation(rotation);// 执行上半场翻转动画
    }

    /**
     * 执行完上半部分旋转之后，设置要显示的新的View然后继续执行下半部分旋转
     */
    private final class DisplayNextView implements Animation.AnimationListener {
        private final boolean mPosition;
        private final View mView;

        private DisplayNextView(boolean zheng, View view) {
            mPosition = zheng;
            mView = view;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            mView.post(new SwapViews(mPosition, mView));// 添加新的View
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    /**
     * 添加要显示的新的View，并执行下半部分的旋转操作
     */
    private final class SwapViews implements Runnable {
        private final boolean mPosition;
        private final ImageView mView;

        public SwapViews(boolean position, View view) {
            mPosition = position;
            mView = (ImageView) view;
        }

        public void run() {
            final float centerX = mView.getWidth() / 2.0f;
            final float centerY = mView.getHeight() / 2.0f;
            Util_Rotate3DAnimation rotation;

            if (mPosition) {
                rotation = new Util_Rotate3DAnimation(-90, 0, centerX, centerY,
                        310.0f, false);
            } else {
                rotation = new Util_Rotate3DAnimation(90, 0, centerX, centerY,
                        310.0f, false);
            }
            mView.setImageResource(R.drawable.head2);

            rotation.setDuration(500);
            rotation.setFillAfter(true);
            rotation.setInterpolator(new DecelerateInterpolator());
            mView.startAnimation(rotation);

        }
    }

}
