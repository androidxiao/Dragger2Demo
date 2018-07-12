package com.commmonlibrary.cn.delegatemvp.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.commmonlibrary.cn.delegatemvp.view.IDelegate;
import com.commmonlibrary.cn.mvp.MvpBaseFragment;


/**
 * Created by chawei on 2018/7/3.
 */

public abstract class FragmentPresenter<T extends IDelegate> extends MvpBaseFragment {

    public T mViewDelegate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            mViewDelegate = getDelegateClass().newInstance();
        } catch (IllegalAccessException e) {
            throw new RuntimeException("create IDelegate error");
        } catch (java.lang.InstantiationException e) {
            throw new RuntimeException("create IDelegate error");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mViewDelegate.create(inflater, container, savedInstanceState);
        return mViewDelegate.getRootView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewDelegate.initWidget();
        bindEventListener();
    }

    protected abstract void bindEventListener();

    protected abstract Class<T> getDelegateClass();

    @Override
    public View initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 只需要默认实现，子类无需重写
        return null;
    }


}
