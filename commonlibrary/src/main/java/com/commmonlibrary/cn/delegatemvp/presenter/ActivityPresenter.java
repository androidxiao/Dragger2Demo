package com.commmonlibrary.cn.delegatemvp.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.commmonlibrary.cn.delegatemvp.view.IDelegate;
import com.commmonlibrary.cn.mvp.MvpBaseActivity;


/**
 * Created by chawei on 2018/7/3.
 */

public abstract class ActivityPresenter<T extends IDelegate> extends MvpBaseActivity implements View.OnClickListener{

    protected T mViewDelegate;

    public ActivityPresenter(){
        try{
            mViewDelegate=getDelegateClass().newInstance();
        } catch (IllegalAccessException e) {
            throw new RuntimeException("create IDelegate error");
        } catch (InstantiationException e) {
            throw new RuntimeException("create IDelegate error");
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewDelegate.create(getLayoutInflater(), null, savedInstanceState);
        setContentView(mViewDelegate.getRootView());
        mViewDelegate.initWidget();
        bindEventListener();
    }

    protected abstract void bindEventListener();

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (mViewDelegate == null) {
            try{
                mViewDelegate=getDelegateClass().newInstance();
            } catch (IllegalAccessException e) {
                throw new RuntimeException("create IDelegate error");
            } catch (InstantiationException e) {
                throw new RuntimeException("create IDelegate error");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewDelegate=null;
    }

    protected abstract Class<T> getDelegateClass();

    @Override
    public void initLayoutView() {
        // 只需要默认实现，子类无需重写
    }

    @Override
    public void requestNetData() {
        // 只需要默认实现，子类无需重写
    }
}
