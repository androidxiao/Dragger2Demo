package com.commmonlibrary.cn.widget;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.commmonlibrary.cn.R;


/**
 * 所有Toast提示使用此Toast
 * Created by wei on 2016/9/6.
 */
public class CommonToast extends Toast {

    private static Toast newToast;
    private CommonToast(Context context) {
        super(context);
    }

    /**
     * 自定义Toast，不能在最外层的layout设置背景，否则，设置的宽高无效
     * @param context
     * @param text
     * @param duration
     * @return
     */
    public static Toast makeText(Context context, CharSequence text, int duration) {
        if( newToast != null) {
            newToast.cancel();
            newToast = null;
        }
        newToast = new Toast(context);
        View layout = LayoutInflater.from(context).inflate(R.layout.common_toast_layout, null);
        TextView textView = (TextView) layout.findViewById(R.id.toast_text);
        textView.setText(text);

        newToast.setView(layout);
        newToast.setGravity(Gravity.CENTER, 0, 0);
        newToast.setDuration(duration);
        return newToast;
    }

}
