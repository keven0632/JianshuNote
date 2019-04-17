package com.keven.jianshu.part3;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.keven.jianshu.R;

/**
 * Created by zhengjian on 2019/4/17.
 */

public class ViewProperty extends View {

    private int mSize;
    private int mColor;
    private Paint mPaint;

    public ViewProperty(Context context) {
        super(context);

    }

    public ViewProperty(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ViewProperty);
        mSize = array.getDimensionPixelSize(R.styleable.ViewProperty_default_size, 10);
        mColor = array.getColor(R.styleable.ViewProperty_backgroundColor, Color.RED);
        array.recycle();

        mPaint = new Paint();
        mPaint.setColor(mColor);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = 100;
        int width = 100;
        int height_mode = MeasureSpec.getMode(heightMeasureSpec);
        int with_mode = MeasureSpec.getMode(widthMeasureSpec);
        int height_size = MeasureSpec.getSize(heightMeasureSpec);
        int width_size = MeasureSpec.getSize(widthMeasureSpec);

        if (height_mode == MeasureSpec.AT_MOST) {
            height = Dp2Px(getContext(),100);
        } else if (height_mode == MeasureSpec.EXACTLY) {
            height = height_size;
        }
        if (with_mode == MeasureSpec.AT_MOST) {
            width = Dp2Px(getContext(),100);
        } else if (with_mode == MeasureSpec.EXACTLY) {
            width = width_size;
        }

        setMeasuredDimension(width, height);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0,0,getWidth(),getHeight(),mPaint);
    }

    public static int Px2Dp(Context context, int px) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px, context.getResources().getDisplayMetrics());
    }
    public static int Dp2Px(Context context, int dpi) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpi, context.getResources().getDisplayMetrics());
    }

}
