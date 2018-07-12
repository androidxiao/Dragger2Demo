package com.commmonlibrary.cn.delegatemvp.databind;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.commmonlibrary.cn.delegatemvp.model.IModel;
import com.commmonlibrary.cn.delegatemvp.presenter.FragmentPresenter;
import com.commmonlibrary.cn.delegatemvp.view.IDelegate;


/**
 * Created by chawei on 2018/7/3.
 */

public abstract class DataBindFragment<T extends IDelegate> extends FragmentPresenter<T> {

    protected DataBinder mBinder;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinder = getDataBinder();
    }

    public abstract DataBinder getDataBinder();

    public <D extends IModel> void notifyModelChanged(D data) {
        if (mBinder != null) {
            mBinder.viewBindModel(mViewDelegate, data);
        }
    }
}

