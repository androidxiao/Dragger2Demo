package com.dragger2.demo.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.commmonlibrary.cn.base.BaseFragment;
import com.dragger2.demo.R;
import com.dragger2.demo.view.BottomBar;
import com.dragger2.demo.view.BottomBar.OnTabSelectedListener;
import com.dragger2.demo.view.BottomBarDrawableTab;
import com.dragger2.demo.view.BottomBarTab;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by chawei on 2018/6/20.
 */

public class MainFragment extends BaseFragment {

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;

    private SupportFragment[] mFragments = new SupportFragment[3];

    private BottomBar mBottomBar;
    private BottomBar mBar;

    public static MainFragment getInstance(Bundle bundle){
        MainFragment mainFragment = new MainFragment();
        mainFragment.setArguments(bundle);
        return mainFragment;
    }

    @Override
    public void beforeViewData() {

    }

    @Override
    public View initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_main_layout,container,false);
        initView(view);
        return view;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        SupportFragment aFragment = findChildFragment(MainAFragment.class);
        if (aFragment==null) {
            mFragments[FIRST]=MainAFragment.getInstance("MainAFragmentAAA--->");
            mFragments[SECOND]=MainBFragment.getInstance("MainBFragmentBBB--->");
            mFragments[THIRD]=MainCFragment.getInstance("MainCFragmentCCC--->");
            loadMultipleRootFragment(R.id.fl_tab_container, FIRST,
                    mFragments[FIRST],mFragments[SECOND],mFragments[THIRD]);
        }else{
            //这里库已经做了 Fragment 恢复，所以不用额外的处理了，不会出现重叠问题
            //这里我们需要拿到 mFragments 的引用
            mFragments[FIRST]=aFragment;
            mFragments[SECOND] = findChildFragment(MainBFragment.class);
            mFragments[THIRD] = findChildFragment(MainCFragment.class);
        }
    }

    private void initView(View view){
        mBottomBar=view.findViewById(R.id.bottomBar);
//        mBottomBar.addItem(new BottomBarTab(_mActivity,R.drawable.ic_market_normal,"Msg"))
//                .addItem(new BottomBarTab(_mActivity,R.drawable.ic_header_normal,"Discover"))
//                .addItem(new BottomBarTab(_mActivity,R.drawable.ic_market_normal,"More"));

        Drawable drawable1 = ContextCompat.getDrawable(getContext(), R.drawable.ic_home_selector);
        Drawable drawable2 = ContextCompat.getDrawable(getContext(), R.drawable.ic_market_selector);
        Drawable drawable3 = ContextCompat.getDrawable(getContext(), R.drawable.ic_my_selector);
        mBottomBar.addItem(new BottomBarDrawableTab(_mActivity,drawable1,"Msg"))
        .addItem(new BottomBarDrawableTab(_mActivity,drawable2,"Discover"))
        .addItem(new BottomBarDrawableTab(_mActivity,drawable3,"More"))
        .addItem(new BottomBarTab(_mActivity,R.mipmap.ic_market_normal,"Other"));

        mBottomBar.setOnTabSelectedListener(new OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(mFragments[position],mFragments[prePosition]);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
