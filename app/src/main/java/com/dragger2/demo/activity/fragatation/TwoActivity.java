package com.dragger2.demo.activity.fragatation;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.commmonlibrary.cn.base.BaseActivity;
import com.commmonlibrary.cn.router.Router;
import com.dragger2.demo.R;
import com.dragger2.demo.adapter.FragmentAdapter;
import com.dragger2.demo.fragment.FragmentA;

import java.util.ArrayList;

/**
 * Created by chawei on 2018/6/19.
 *
 * TabLayout + ViewPager + Fragment
 */

public class TwoActivity extends BaseActivity {

    private TabLayout mTabLayout;
    private ViewPager mVp;
    private ArrayList<Fragment> mFragments;
    private String[] mStrings = new String[]{"标题1", "标题2","标题3","标题4","标题5","标题6"};

    @Override
    public void beforeViewData() {

    }

    @Override
    public void initLayoutView() {
        setContentView(R.layout.activity_two_layout);
        mTabLayout = findViewById(R.id.id_tab_main);
        mVp = findViewById(R.id.id_vp_main);

        mFragments = new ArrayList<>();

        mFragments.add(FragmentA.getInstance("我的是第一个"));
        mFragments.add(FragmentA.getInstance("我的是第二个"));
        mFragments.add(FragmentA.getInstance("我的是第三个"));
        mFragments.add(FragmentA.getInstance("我的是第四个"));
        mFragments.add(FragmentA.getInstance("我的是第五个"));
        mFragments.add(FragmentA.getInstance("我的是第六个"));

        FragmentAdapter fragmentAdapter=new FragmentAdapter(getSupportFragmentManager(),mFragments,mStrings);

        mVp.setAdapter(fragmentAdapter);
        mTabLayout.setupWithViewPager(mVp);
    }

    @Override
    public void requestNetData() {

    }

    public static void launch(Activity context){
        Router.newIntent(context)
                .to(TwoActivity.class)
                .launch();
    }

    @Override
    public void onClick(View v) {

    }
}
