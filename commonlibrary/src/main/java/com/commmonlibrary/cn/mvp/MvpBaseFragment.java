package com.commmonlibrary.cn.mvp;

import android.os.Bundle;
import android.view.View;

import com.commmonlibrary.cn.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by chawei on 2018/3/8.
 */

public abstract class MvpBaseFragment<V extends MvpView, P extends MvpPresenter> extends BaseFragment implements MvpView {

    /**
     * 所有 Presenter 的实现类的实例给 mPresenter
     */
    protected P mPresenter;
    /**
     * 可能一个类中不止一个 Presenter，这里用 list 存储
     */
    protected List<P> mListPresenter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mListPresenter = new ArrayList<>();

    }

    @Override
    public void onDestroyView() {
        clearPresenter();
        hideLoading();
        super.onDestroyView();

    }

    private void clearPresenter() {

        if (mPresenter != null) {
            mPresenter.onDetach();
        }

        if (mListPresenter != null && mListPresenter.size() > 0) {
            for (P p : mListPresenter) {
                p.onDetach();
            }
            mListPresenter.clear();
        }
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
    }

    @Override
    public void showMessage(int resId) {

    }

    @Override
    public void showMessage(String message) {
        showMessage(message);
    }

}
