package com.dragger2.demo.activity.glideapp;

import android.widget.Button;

import com.commmonlibrary.cn.delegatemvp.view.AppDelegate;
import com.commmonlibrary.cn.utils.LogUtil;
import com.dragger2.demo.R;

/**
 * Created by chawei on 2018/7/6.
 */

public class GlideAppDelegate extends AppDelegate {

    @Override
    public int getRootLayoutId() {
        return R.layout.ac_glide_app;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        LogUtil.debug("initWidget-------->");
        Button btnClearCache=get(R.id.btn_clear_cache);
    }
}
