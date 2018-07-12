package com.dragger2.demo.activity.customview;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.commmonlibrary.cn.base.BaseActivity;
import com.dragger2.demo.R;
import com.dragger2.demo.view.TitlePop;

/**
 * Created by chawei on 2018/7/12.
 */

public class BubbleLayoutActivity extends BaseActivity {


    private TitlePop titlePop;
    private ImageView mIv;

    @Override
    public void beforeViewData() {

    }

    @Override
    public void initLayoutView() {
        setContentView(R.layout.ac_bubbule);

        findView();

    }

    private void findView(){
        mIv = findViewById(R.id.iv_bubble);

        mIv.setOnClickListener(this);
    }

    private void showPop() {
        if (titlePop == null) {
            titlePop = new TitlePop(this);
        }
        titlePop.setListener(position -> {
            if (position == 0) {//搜索
                Toast.makeText(this, "搜索", Toast.LENGTH_SHORT).show();
            } else if (position == 1) {  //分享
                Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
            } else if (position == 2) {//设置
                Toast.makeText(this, "设置", Toast.LENGTH_SHORT).show();
            } else if (position == 3) { //说明
                Toast.makeText(this, "说明", Toast.LENGTH_SHORT).show();
            }
            titlePop.dismiss();
        });
        //showAsDropDown显示的点是以anchorView左下角点为参照点.
        //105=pop.width-anchor.marginRight
//        int xOff = dp2px(this, 95) - mRightTv.getWidth() / 2;
        titlePop.showAsDropDown(mIv);
    }


    @Override
    public void requestNetData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_bubble:
                showPop();
                break;
        }
    }
}
