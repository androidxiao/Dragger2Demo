package com.dragger2.demo.activity.fragatation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.commmonlibrary.cn.base.BaseActivity;
import com.commmonlibrary.cn.router.Router;
import com.dragger2.demo.R;
import com.dragger2.demo.fragment.MainFragment;

/**
 * Created by chawei on 2018/6/19.
 *
 * Fragment 嵌套 Fragment
 */

public class ThirdActivity extends BaseActivity {

    @Override
    public void beforeViewData() {

    }

    @Override
    public void initLayoutView() {
        setContentView(R.layout.activity_third_layout);

        loadRootFragment(R.id.id_fragment_container, MainFragment.getInstance(new Bundle()));
    }

    @Override
    public void requestNetData() {

    }

    public static void launch(Activity context, Bundle bundle) {
        Router.newIntent(context)
                .to(ThirdActivity.class)
                .data(bundle)
                .launch();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
    }
}
