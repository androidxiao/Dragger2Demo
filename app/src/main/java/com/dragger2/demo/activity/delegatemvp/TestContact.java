package com.dragger2.demo.activity.delegatemvp;

import com.commmonlibrary.cn.mvp.MvpPresenter;
import com.commmonlibrary.cn.mvp.MvpView;

/**
 * Created by chawei on 2018/7/5.
 */

public class TestContact {

    public interface ITestMvpView extends MvpView{
        void test();
    }

    public interface ITestMvpPresenter<V extends ITestMvpView> extends MvpPresenter<V>{
        void reqeust();
    }

}
