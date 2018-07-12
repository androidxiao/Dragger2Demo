package com.dragger2.demo.activity.customview;

import android.view.View;
import android.widget.Toast;

import com.commmonlibrary.cn.base.BaseActivity;
import com.commmonlibrary.cn.widget.ShapeViewGroup;
import com.dragger2.demo.R;

/**
 * Created by chawei on 2018/6/24.
 *
 * 各种圆角背景
 */

public class RoundViewGroupActivity extends BaseActivity {


    @Override
    public void beforeViewData() {

    }

    @Override
    public void initLayoutView() {
        setContentView(R.layout.activity_round_viewgroup_layout);

        ShapeViewGroup shapeViewGroup=findViewById(R.id.id_shape_vg);
        shapeViewGroup.setOnClickListener(view->{
            Toast.makeText(this, "点击", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void requestNetData() {

    }

    @Override
    public void onClick(View v) {

    }
}
