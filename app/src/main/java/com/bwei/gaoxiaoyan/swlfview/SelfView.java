package com.bwei.gaoxiaoyan.swlfview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by xiaowu on 2017/6/14.
 */

public class SelfView extends View {
    private static final String TAG = "SelfView";
    private Paint mPaint;
    private Rect mBound;

    private Context context;

    private String text = "hello world";

    public SelfView(Context context) {
        this(context, null);
    }

    public SelfView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelfView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        mPaint = new Paint();
        // 设置画笔的属性
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(40);
        mPaint.setAntiAlias(true);

        mBound = new Rect();
        mPaint.getTextBounds(text, 0, text.length(), mBound);

    }

    /**
     * 测量，测量出自定义view的宽高,widthMeasureSpec和heightMeasureSpec就是默认的宽高
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = 1000;
        int height = 1000;
        // 重新设置一下view的宽高,只能在onMeasure进行调用
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    /**
     * 画控件的时候的回调方法
     *
     * @param canvas 画布
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.RED);
        // 大部分类里面用的单位是px像素,涉及到dp和px的转换
        canvas.drawRect(0, 0, 500, 500, mPaint);
//        canvas.drawCircle(200, 200, 200, mPaint);
//        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
//        canvas.drawBitmap(bmp, 200, 200, mPaint);
        mPaint.setColor(Color.WHITE);

        canvas.drawText(text,
                getWidth() / 2 - mBound.width() / 2,
                getHeight() / 2 - mBound.height() / 2,
                mPaint);
        // 重绘
        invalidate();
    }

}
