package com.dragger2.demo.activity.fragatation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.commmonlibrary.cn.base.BaseActivity;
import com.commmonlibrary.cn.router.Router;
import com.dragger2.demo.R;
import com.dragger2.demo.fragment.FourAFragment;

/**
 * Created by chawei on 2018/6/21.
 */

public class FourActivity extends BaseActivity {


    @Override
    public void beforeViewData() {

    }

    @Override
    public void initLayoutView() {
        setContentView(R.layout.activity_four_layout);
        TextView tv=findViewById(R.id.id_tv);
        FourAFragment fourAFragment = findFragment(FourAFragment.class);
        if (fourAFragment == null) {
            loadRootFragment(R.id.id_fl_container,FourAFragment.getInstance("我是在 FourActivity 页面里面的"));
        }

        String content=getIntent().getStringExtra("key");
        tv.setText(content);

        tv.setOnClickListener(view->{
            backValue();
        });
    }

    @Override
    public void onBackPressedSupport() {
//        super.onBackPressedSupport();
        backValue();
    }

    private void backValue(){
        Intent data = new Intent();
//        data.putExtra("key","我是从 FourActivity 中回传过来的值");
        Bundle bundle = new Bundle();
        bundle.putString("key","我是从 FourActivity 中回传过来的值");
        data.putExtras(bundle);
        setResult(1001,data);
        finish();
    }

    public static void launch(Activity context, String content, int requestCode){
        Router.newIntent(context)
                .to(FourActivity.class)
                .putString("key",content)
                .requestCode(requestCode)
                .launch();
    }

    @Override
    public void requestNetData() {

    }

    @Override
    public void onClick(View v) {

    }
}
