package com.dragger2.demo.activity.recyclerview.comment;

import android.view.View;

import com.commmonlibrary.cn.delegatemvp.presenter.ActivityPresenter;

/**
 * Created by chawei on 2018/7/11.
 */

public class CommentListActivity extends ActivityPresenter<CommentDelegate> {


    @Override
    public void beforeViewData() {

    }

    @Override
    protected void bindEventListener() {

    }

    @Override
    protected Class<CommentDelegate> getDelegateClass() {
        return CommentDelegate.class;
    }

    @Override
    public void onClick(View v) {

    }
}
