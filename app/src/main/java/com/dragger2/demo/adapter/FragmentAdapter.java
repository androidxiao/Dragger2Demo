package com.dragger2.demo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by chawei on 2018/6/20.
 */

public class FragmentAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> fragmentsList;
    private String[] mStrings;


    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public FragmentAdapter(FragmentManager fm, ArrayList<Fragment> fragmentsList,String[] titles) {
        super(fm);
        this.fragmentsList = fragmentsList;
        mStrings=titles;
    }

    @Override
    public Fragment getItem(int position) {
        return (fragmentsList == null || fragmentsList.size() == 0) ? null : fragmentsList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentsList == null ? 0 : fragmentsList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mStrings[position];
    }
}
