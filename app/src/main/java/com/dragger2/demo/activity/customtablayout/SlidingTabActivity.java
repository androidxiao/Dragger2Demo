package com.dragger2.demo.activity.customtablayout;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.commmonlibrary.cn.base.BaseActivity;
import com.dragger2.demo.R;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

/**
 * Created by chawei on 2018/7/12.
 */

public class SlidingTabActivity extends BaseActivity {

    private Context mContext = this;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {
            "热门", "iOS", "Android"
            , "前端", "后端", "设计", "工具资源"
    };
    private MyPagerAdapter mAdapter;

    @Override
    public void beforeViewData() {
        for (String title : mTitles) {
            mFragments.add(SimpleCardFragment.getInstance(title));
        }
    }

    @Override
    public void initLayoutView() {
        setContentView(R.layout.ac_sliding_tab);

        findView();
    }

    private void findView(){

        ViewPager vp=findViewById(R.id.vp);
        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        vp.setAdapter(mAdapter);

        SlidingTabLayout tl1=findViewById(R.id.tl_1);
        tl1.setViewPager(vp);




    }

    @Override
    public void requestNetData() {

    }

    @Override
    public void onClick(View v) {

    }


    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
