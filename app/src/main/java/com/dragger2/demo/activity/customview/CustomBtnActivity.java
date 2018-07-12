package com.dragger2.demo.activity.customview;

import android.view.View;

import com.commmonlibrary.cn.base.BaseActivity;
import com.commmonlibrary.cn.widget.CountDownBtn;
import com.commmonlibrary.cn.widget.RoundBtn;
import com.dragger2.demo.R;

/**
 * Created by chawei on 2018/6/23.
 */

public class CustomBtnActivity extends BaseActivity {


    @Override
    public void beforeViewData() {

    }

    @Override
    public void initLayoutView() {
        setContentView(R.layout.activity_custom_btn_layout);


        RoundBtn roundBtn = findViewById(R.id.id_round_btn);
        CountDownBtn countDownBtn = findViewById(R.id.id_count_down_btn);
//        countDownBtn.setISendCodeListener(() -> Toast.makeText(CustomBtnActivity.this, "验证码已发送", Toast.LENGTH_SHORT).show());
        countDownBtn.setOnClickListener(view->showLoadingView());
    }

    @Override
    public void requestNetData() {

    }

    @Override
    public void onClick(View v) {

    }
}
