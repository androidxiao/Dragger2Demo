package com.dragger2.demo.activity.delegatemvp;

import android.view.View;

import com.commmonlibrary.cn.delegatemvp.presenter.ActivityPresenter;


/**
 * Created by chawei on 2018/7/5.
 */

public class TestDelegateMvpActivity extends ActivityPresenter<TestDelegate> implements TestContact.ITestMvpView {



    @Override
    public void beforeViewData() {

    }

    @Override
    public void initLayoutView() {

    }

    @Override
    public void requestNetData() {
        TestPresenter mPresenter = new TestPresenter();
        mPresenter.onAttach(this);
        ((TestPresenter)mPresenter).reqeust();

        mListPresenter.add(mPresenter);
        mListPresenter.add(mPresenter);
    }

    @Override
    protected void bindEventListener() {

    }

    @Override
    protected Class<TestDelegate> getDelegateClass() {
        return TestDelegate.class;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void test() {

    }
}
