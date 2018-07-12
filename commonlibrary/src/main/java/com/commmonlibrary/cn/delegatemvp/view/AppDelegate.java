package com.commmonlibrary.cn.delegatemvp.view;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by chawei on 2018/7/3.
 */

public abstract class AppDelegate implements IDelegate {

    protected final SparseArray<View> mViews = new SparseArray<>();

    protected View mRootView;

    public abstract int getRootLayoutId();

    @Override
    public void create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int rootLayoutId = getRootLayoutId();
        mRootView = inflater.inflate(rootLayoutId, container, false);
    }

    @Override
    public View getRootView() {
        return mRootView;
    }

    public void setRootView(View rootView) {
        mRootView = rootView;
    }

    @Override
    public void initWidget() {

    }

    public <T extends View> T bindView(int id) {
        T view = (T) mViews.get(id);
        if (view == null) {
            view = (T) mRootView.findViewById(id);
            mViews.put(id, view);
        }
        return view;
    }

    public <T extends View> T get(int id) {
        return (T) bindView(id);
    }

    public void setOnClickListener(View.OnClickListener listener, int... ids) {
        if (ids == null) {
            return;
        }

        for (int id : ids) {
            get(id).setOnClickListener(listener);
        }

    }
}
