package com.commmonlibrary.cn.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.commmonlibrary.cn.widget.CommonLoadingView;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by chawei on 2018/6/19.
 */

public abstract class BaseFragment extends SupportFragment implements View.OnClickListener{

    protected boolean mHasInited;//Fragment 是否初始化完 View
    private CommonLoadingView mLoadingView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mHasInited=true;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeViewData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return initLayout(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView(savedInstanceState);
    }

    /**
     * 在View初始化之前，需要初始化数据的，实现此方法
     */
    public abstract void beforeViewData();

    /**
     * 初化布局文件实现此方法
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    public abstract View initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);


    /**
     * 初始化View实现此方法,并设置需要点击的控件的监听
     * @param savedInstanceState
     */
    public abstract void initView(Bundle savedInstanceState);

    @Override
    public void onDestroyView() {
        mHasInited=false;
        super.onDestroyView();
    }

    /**
     * 显示加载Loading
     */
    public void showLoadingView() {
        if(mHasInited) {
            if(mLoadingView == null) {
                mLoadingView = new CommonLoadingView(getActivity());
            }
            mLoadingView.show(getActivity().getWindow().getDecorView());
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
}
