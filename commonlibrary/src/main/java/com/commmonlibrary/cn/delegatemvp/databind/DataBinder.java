package com.commmonlibrary.cn.delegatemvp.databind;


import com.commmonlibrary.cn.delegatemvp.model.IModel;
import com.commmonlibrary.cn.delegatemvp.view.IDelegate;

/**
 * Created by chawei on 2018/7/3.
 */

public interface DataBinder<T extends IDelegate,D extends IModel> {

    /**
     * 将数据与 View 绑定，这样当数据改变的时候，框架就知道是和哪个 View 绑定在一起的，就可以自动改变 UI
     * 当数据改变的时候，会回调本方法
     *
     * @param viewDelegate 视图层代理
     * @param data 数据模型对象
     */
    void viewBindModel(T viewDelegate, D data);

}
