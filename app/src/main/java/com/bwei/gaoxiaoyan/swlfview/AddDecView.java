package com.bwei.gaoxiaoyan.swlfview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * user:高肖燕  luccc第一次修改
 * date: 2017/6/15    20:41
 */

public class AddDecView extends LinearLayout implements View.OnClickListener{
    private TextView txtDecrease;
    private TextView txtAdd;
    private EditText etShow;

    private int count = 0;

    private int defaultColor = Color.parseColor("#FF0000");

    private int max = 0;

    public AddDecView(Context context) {
        this(context, null);
    }
    public AddDecView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public AddDecView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 通过这条语句把我们自定义的属性引入进来
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.addDecStyle, defStyleAttr, 0);
        // 自定义属性的id格式  stylable的名字_attr属性名字，可以有下面几种类型
        // string,color,demension,integer,enum,reference,float,boolean,fraction,flag;
        int bgColor = a.getColor(R.styleable.addDecStyle_textBackGround, defaultColor);
        max = a.getInt(R.styleable.addDecStyle_max, 0);
        String addText = a.getString(R.styleable.addDecStyle_addText);
        String decText = a.getString(R.styleable.addDecStyle_decText);


        //第三个参数是this，代表给当前view设置视图
        View view = View.inflate(context, R.layout.view_add_decrease, this);
//        View view = View.inflate(context, R.layout.view_add_decrease, null);
        txtDecrease = (TextView) view.findViewById(R.id.txt_view_decresease);
        txtAdd = (TextView) view.findViewById(R.id.txt_view_add);
        etShow = (EditText) view.findViewById(R.id.et_show);

        txtDecrease.setText(TextUtils.isEmpty(decText)?"-":decText);
        txtAdd.setText(TextUtils.isEmpty(addText)?"+":addText);


        etShow.setText(R.string.app_name);
//        etShow.setText(count);

        txtAdd.setOnClickListener(this);
        txtDecrease.setOnClickListener(this);

        txtDecrease.setBackgroundColor(bgColor);
        txtAdd.setBackgroundColor(bgColor);
        // 调用TypedArray的回收方法
        a.recycle();
    }
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
        etShow.setText(count + "");
    }

    interface onClickLisner {
        void onClick(int count);
    }

    private onClickLisner listener;
    public void setListener(onClickLisner listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_view_add:
                if (count < max) {
                    count++;
                    etShow.setText(count + "");
                }
                break;
            case R.id.txt_view_decresease:
                if (count > 0) {
                    count--;
                }
                etShow.setText(count + "");
                listener.onClick(count);
                break;
        }
    }
}
