package com.commmonlibrary.cn.mvp;

import android.support.annotation.StringRes;

/**
 * 所有使用MVP（Model、View、Presenter）的类都必须实现此接口。
 * Created by chawei on 2018/3/8.
 */

public interface MvpView {

    void showLoading();

    void hideLoading();

    void onError(@StringRes int resId);

    void onError(String message);

    void showMessage(@StringRes int resId);

    void showMessage(String message);

}
