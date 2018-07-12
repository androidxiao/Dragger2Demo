package com.commmonlibrary.cn.mvp;

import android.os.Bundle;

import com.commmonlibrary.cn.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chawei on 2018/3/8.
 */

public abstract class MvpBaseActivity<V extends MvpView, P extends MvpPresenter> extends BaseActivity implements MvpView {


    /**
     * 将 Presenter 实现类的实例赋值给 mPresenter（页面只有一个 Presenter 时使用）
     */
    protected P mPresenter;
    /**
     * 页面有多个 Presenter 时将其添加到 List 中
     */
    protected List<P> mListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mListPresenter = new ArrayList<>();
    }

    @Override
    public void showLoading() {
        showLoadingView();
    }

    @Override
    public void hideLoading() {
        dismissLoadingView();
    }

    @Override
    public void onError(int resId) {

    }

    @Override
    public void onError(String message) {
        showMessage(message);
    }

    @Override
    public void showMessage(int resId) {
    }

    @Override
    public void showMessage(String message) {
        showShortToast(message);
    }


    @Override
    protected void onDestroy() {

        if (mPresenter != null) {
            mPresenter.onDetach();
            mPresenter = null;
        }

        if (mListPresenter != null && mListPresenter.size() > 0) {
            for (P p : mListPresenter) {
                p.onDetach();
            }
            mListPresenter.clear();
        }
        super.onDestroy();
    }
}

