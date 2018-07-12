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

public class FourBFragment extends BaseFragment{


    private View mView;

    public static FourBFragment getInstance(String content){
        FourBFragment fragmentA=new FourBFragment();
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
        tv.setText("点击我退出---->"+key);
        tv.setOnClickListener(view->{
            pop();
        });
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

    /**
     * 不重写该方法，返回键会直接用作于它的 Activity
     * 如果 true,自己消费该事件，false，交由父类处理
     */
//    @Override
//    public boolean onBackPressedSupport() {
//        pop();
//        return true;
//    }
}
