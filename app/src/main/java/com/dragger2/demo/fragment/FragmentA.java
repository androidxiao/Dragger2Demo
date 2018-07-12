package com.dragger2.demo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.commmonlibrary.cn.base.BaseFragment;
import com.commmonlibrary.cn.utils.LogUtil;
import com.dragger2.demo.R;

/**
 * Created by chawei on 2018/6/20.
 */

public class FragmentA extends BaseFragment{


    private View mView;

    public static FragmentA getInstance(String content){
        FragmentA fragmentA=new FragmentA();
        Bundle bundle = new Bundle();
        bundle.putString("key",content);
        fragmentA.setArguments(bundle);
        return fragmentA;
    }


    @Override
    public void beforeViewData() {

    }

    @Override
    public View initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_a_layout, container, false);
        return mView;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        TextView tv=mView.findViewById(R.id.id_tv_text);
        String key=getArguments().getString("key");
        tv.setText(key);

        tv.setOnClickListener(view->{
            backValue();
        });
    }

    private void backValue(){
//        data.putExtra("key","我是从 FourActivity 中回传过来的值");
        Bundle bundle = new Bundle();
        bundle.putString("key","我是从 FragmentA 中回传过来的值");
        setFragmentResult(1001,bundle);
        pop();
    }


    @Override
    public void onClick(View v) {

    }



    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
        String key=getArguments().getString("key");
        LogUtil.debug("隐藏---->"+key);
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        String key=getArguments().getString("key");
        LogUtil.debug("显示---->"+key);
    }

    @Override
    public boolean onBackPressedSupport() {
        backValue();
        return false;
    }
}
