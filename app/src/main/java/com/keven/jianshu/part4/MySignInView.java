package com.keven.jianshu.part4;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.keven.jianshu.R;
import com.keven.jianshu.utils.MyApp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengjian on 2019/4/22.
 */

public class MySignInView extends ConstraintLayout implements View.OnClickListener {
    List<ImageView> ivList = new ArrayList();
    private ImageView mIv_first;
    private ImageView mIv_second;
    private ImageView mIv_third;
    private ImageView mIv_four;
    private ImageView mIv_five;
    private ImageView mIv_six;
    private ImageView mIv_seven;

    public MySignInView(Context context) {
        super(context);
    }

    public MySignInView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.seven_signin_layout, this);
        ivList.clear();
        mIv_first = findViewById(R.id.iv_first);
        mIv_second = findViewById(R.id.iv_second);
        mIv_third = findViewById(R.id.iv_third);
        mIv_four = findViewById(R.id.iv_four);
        mIv_five = findViewById(R.id.iv_five);
        mIv_six = findViewById(R.id.iv_six);
        mIv_seven = findViewById(R.id.iv_seven);
        ivList.add(mIv_first);
        ivList.add(mIv_second);
        ivList.add(mIv_third);
        ivList.add(mIv_four);
        ivList.add(mIv_five);
        ivList.add(mIv_six);
        ivList.add(mIv_seven);
        mIv_first.setOnClickListener(this);
        mIv_second.setOnClickListener(this);
        mIv_third.setOnClickListener(this);
        mIv_four.setOnClickListener(this);
        mIv_five.setOnClickListener(this);
        mIv_six.setOnClickListener(this);
        mIv_seven.setOnClickListener(this);
//        initClickable();
        setViewEnable(0);
    }

    private void initClickable() {
        for (int i = 0; i < ivList.size(); i++) {
            ivList.get(i).setEnabled(false);
            ivList.get(i).setClickable(false);
        }

    }

    public void setViewEnable(int index) {
        ivList.get(index).setEnabled(true);
        ivList.get(index).setClickable(true);
    }

    @Override
    public void onClick(View v) {
        applyRotation(false, findViewById(v.getId()), 0, -90);
        findViewById(v.getId()).setEnabled(false);
        findViewById(v.getId()).setClickable(false);
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

    public void setFrontImage(ArrayList<String> list) {
        for (int i = 0; i < ivList.size(); i++) {
            Glide.with(MyApp.getInstance()).load(list.get(i)).into(ivList.get(i));
        }
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
