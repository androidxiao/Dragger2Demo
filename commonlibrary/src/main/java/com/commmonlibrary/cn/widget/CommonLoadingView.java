package com.commmonlibrary.cn.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.commmonlibrary.cn.R;


/**
 * 通用的加载Loading
 * Created by wei on 2016/9/25.
 */
public class CommonLoadingView extends PopupWindow {

    private LoadingPointView mIvLoading;

    public CommonLoadingView(Context context) {
        super(context);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setOutsideTouchable(false);
        setBackgroundDrawable(new ColorDrawable());
        setFocusable(false);
        setClippingEnabled(false); //状态栏使用此方法
        initView(context);
    }

    private void initView(Context context) {
        RelativeLayout mainLayout = (RelativeLayout) View.inflate(context, R.layout.common_loading_view, null);
        mIvLoading = mainLayout.findViewById(R.id.iv_loading);
        // 添加视图
        setContentView(mainLayout);
    }

    /**
     * 显示
     * @param parent
     */
    public void show(final View parent) {
        // 解决 android.view.WindowManager$BadTokenException: Unable to add window -- token null is not valid; is your activity running?
        // 参考：http://stackoverflow.com/questions/4187673/problems-creating-a-popup-window-in-android-activity
        parent.post(new Runnable() {
            @Override
            public void run() {
                showAtLocation(parent, Gravity.CENTER, 0, 0);
            }
        });
    }

    @Override
    public void dismiss() {
        mIvLoading.stopAnimator();
        super.dismiss();
    }

}
