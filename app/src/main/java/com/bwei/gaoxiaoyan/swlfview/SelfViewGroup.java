package com.bwei.gaoxiaoyan.swlfview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by xiaowu on 2017/6/15.
 */

public class SelfViewGroup extends ViewGroup {
    private static final String TAG = "SelfViewGroup";
    public SelfViewGroup(Context context) {
        this(context, null);
    }

    public SelfViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelfViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i(TAG, "viewgroup onDraw: ");
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawRect(0,0, 500, 500, paint);
    }

    /**
     * 继承ViewGroup的时候，可以实现onMeasure，但不是必须的
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i(TAG, "viewgroup onMeasure: ");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        getChildAt(0).measure(widthMeasureSpec, heightMeasureSpec);
        // 重新计算一下子view的宽高
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    // 继承ViewGroup必须实现的一个方法
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.i(TAG, "viewgroup onLayout: ");
        int totalWidth = 0;
        int totalHeight = 0;
        for (int i = 0; i < getChildCount(); i++) {
            // 获取当前的子view
            View view = getChildAt(i);
            int width = view.getMeasuredWidth();
            int height = view.getMeasuredHeight();
            view.layout(totalWidth, totalHeight, totalWidth + width, totalHeight + height);
            totalWidth += width;
            totalHeight += height;
        }
    }
}
