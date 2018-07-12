package com.commmonlibrary.cn.mvp;


import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by chawei on 2017/3/8.
 */

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private Reference<V> mViewRef;


    @Override
    public void onAttach(V mvpView) {
        mViewRef = new WeakReference(mvpView);
    }

    @Override
    public void onDetach() {
        referenceClear();
    }

    /**
     * 清除View引用
     */
    private void referenceClear() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    /**
     * 页面退出时，请求还在，这时就不能再使用 getMvpView() 了
     * 这时候，会在 onDestroyView 中销毁相关内容，如：loadingView
     * 所以，这里会返回 true,在每个实现类的 presenter 中，如果有 UI 相关操作，
     * 需要调用该方法判断一下
     * @return
     */
    public boolean isViewAttached() {
        return mViewRef != null;
    }


    public V getMvpView() {
        checkViewAttached();
        return mViewRef.get();
    }

    public void checkViewAttached() {
        if (!isViewAttached())
            throw new MvpViewNotAttachedException();
    }

    /**
     * 清除个人相关信息
     */
    @Override
    public void setUserAsLoggedOut() {
        //TODO 清除用户数据
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("在Presenter请求数据之前，请先调用Presenter.onAttach(MvpView)");
        }
    }
}
