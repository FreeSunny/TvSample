package com.sample.tv.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sample.tv.R;


/**
 * Created by hzsunyj on 16/7/5.
 */
public class ContactView extends LinearLayout {

    private ImageView head;

    private TextView name;

    private TextView phone;

    public ContactView(Context context) {
        super(context);
        init(context);
    }

    public ContactView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ContactView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.contact_layout, this);
        findViews();
    }

    private void findViews() {
        head = (ImageView) findViewById(R.id.contact_head);
        name = (TextView) findViewById(R.id.contact_name);
        phone = (TextView) findViewById(R.id.contact_phone);
    }


    public void setHead(int resId) {
        head.setImageResource(resId);
    }

    public void setName(String text) {
        name.setText(text);
    }

    public void setPhone(String text) {
        phone.setText(text);
    }
}
