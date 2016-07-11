package com.sample.tv.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by hzsunyj on 16/7/11.
 */
public class ToastUtil {


    public static void showToast(String text, Context context) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }
}
