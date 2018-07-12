package com.commmonlibrary.cn.delegatemvp.databind;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.commmonlibrary.cn.delegatemvp.model.IModel;
import com.commmonlibrary.cn.delegatemvp.presenter.ActivityPresenter;
import com.commmonlibrary.cn.delegatemvp.view.IDelegate;


/**
 * Created by chawei on 2018/7/3.
 */

public abstract class DataBindActivity<T extends IDelegate> extends ActivityPresenter<T> {

    protected DataBinder mBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinder=getDataBinder();
    }

    public abstract DataBinder getDataBinder();

    public <D extends IModel> void notifyModelChanged(D data) {
        if (mBinder != null) {
            mBinder.viewBindModel(mViewDelegate, data);
        }
    }


}
