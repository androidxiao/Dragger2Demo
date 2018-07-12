package com.commmonlibrary.cn.mvp;

/**
 * Created by chawei on 2018/3/8.
 */
public interface MvpPresenter<V extends MvpView> {

    void onAttach(V mvpView);

    void onDetach();

    void setUserAsLoggedOut();
}
