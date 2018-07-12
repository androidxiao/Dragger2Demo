package com.dragger2.demo.activity.customtablayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.commmonlibrary.cn.base.BaseActivity;
import com.dragger2.demo.adapter.SimpleHomeAdapter;

/**
 * Created by chawei on 2018/7/12.
 */

public class TabLayoutHomeActivity extends BaseActivity {

    private Context mContext = this;
    private final String[] mItems = {"SlidingTabLayout", "CommonTabLayout", "SegmentTabLayout"};
    private final Class<?>[] mClasses = {SlidingTabActivity.class, CommonTabActivity.class,
            SegmentTabActivity.class};


    @Override
    public void beforeViewData() {

    }

    @Override
    public void initLayoutView() {
        ListView lv = new ListView(mContext);
        lv.setCacheColorHint(Color.TRANSPARENT);
        lv.setFadingEdgeLength(0);
        lv.setAdapter(new SimpleHomeAdapter(mContext, mItems));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, mClasses[position]);
                startActivity(intent);
            }
        });

        setContentView(lv);
    }

    @Override
    public void requestNetData() {

    }

    @Override
    public void onClick(View v) {

    }
}
