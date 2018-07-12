package com.commmonlibrary.cn.base;

import android.os.Bundle;
import android.view.View;

import com.commmonlibrary.cn.utils.ToastUtil;
import com.commmonlibrary.cn.widget.CommonLoadingView;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by chawei on 2018/6/20.
 */

public abstract class BaseActivity extends SupportActivity implements View.OnClickListener{

    protected boolean mHasInited;//Activity 是否初始化完成
    private CommonLoadingView mLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeViewData();
        initLayoutView();
    }

    /**
     * 在View初始化之前，需要初始化数据的，实现此方法
     */
    public abstract void beforeViewData();

    /**
     * 实现此方法，必需先加载化布局文件，再并初始化View, 并设置需要点击的控件的监听
     */
    public abstract void initLayoutView();

    /**
     * 请求网络数据实现此方法
     */
    public abstract void requestNetData();

    @Override
    public void onAttachedToWindow() {
        mHasInited  = true;
        requestNetData();
    }

    @Override
    protected void onDestroy() {
        mHasInited=false;
        dismissLoadingView();
        super.onDestroy();
    }

    /**
     * 显示加载Loading
     */
    public void showLoadingView() {
        if(mHasInited) {
            if(mLoadingView == null) {
                mLoadingView = new CommonLoadingView(this);
            }
            mLoadingView.show(getWindow().getDecorView());
        }
    }

    /**
     * 关闭加载Loading
     */
    public void dismissLoadingView() {
        if(mLoadingView != null && mLoadingView.isShowing()) {
            mLoadingView.dismiss();
            mLoadingView = null;
        }
    }

    /**
     * 短时Toast
     * @param text
     */
    public void showShortToast(String text) {
        ToastUtil.showShortToast(this, text);
    }

    /**
     * 长时Toast
     * @param text
     */
    public void showLongToast(String text) {
        ToastUtil.showLongToast(this, text);
    }

}
