package com.dragger2.demo.activity.delegatemvp;

import com.commmonlibrary.cn.mvp.BasePresenter;

/**
 * Created by chawei on 2018/7/5.
 */

public class TestPresenter<V extends TestContact.ITestMvpView>  extends BasePresenter<V> implements TestContact.ITestMvpPresenter<V> {

    @Override
    public void reqeust() {
        getMvpView().test();
    }
}
