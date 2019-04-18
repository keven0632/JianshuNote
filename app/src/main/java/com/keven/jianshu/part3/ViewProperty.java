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

    /**
     *在java代码创建视图的时候被调用，
     *如果是从xml填充的视图，就不会调用这个
     */
    public ViewProperty(Context context) {
        super(context);
    }

    /**
     * 这个是在xml创建但是没有指定style的时候被调用
     */
    public ViewProperty(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //通过 TypedArray 获取自定义属性，使用完后记得及时回收
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ViewProperty);
        mSize = array.getDimensionPixelSize(R.styleable.ViewProperty_default_size, 10);
        mColor = array.getColor(R.styleable.ViewProperty_backgroundColor, Color.RED);
        array.recycle();

        //初始化画笔
        mPaint = new Paint();
        mPaint.setColor(mColor);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int height = 100;
        int width = 100;
        //获取宽高的测量模式及具体尺寸
        int height_mode = MeasureSpec.getMode(heightMeasureSpec);
        int with_mode = MeasureSpec.getMode(widthMeasureSpec);
        int height_size = MeasureSpec.getSize(heightMeasureSpec);
        int width_size = MeasureSpec.getSize(widthMeasureSpec);
        //根据具体测量模式来给定不同的尺寸
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
        //调用setMeasuredDimension()方法，传入测量后的尺寸值
        setMeasuredDimension(width, height);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //这里绘制一个矩形
        //传入左、上、右、下坐标及画笔
        canvas.drawRect(0,0,getWidth(),getHeight(),mPaint);
    }

    /**
     *下面是两个工具类，用于 dp 和 px 的转换，
     * 由于代码中使用的是 px，布局中尺寸一般使用 dp，
     * 所以需要做个转换
     */
    public static int Px2Dp(Context context, int px) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px, context.getResources().getDisplayMetrics());
    }
    public static int Dp2Px(Context context, int dpi) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpi, context.getResources().getDisplayMetrics());
    }

}
