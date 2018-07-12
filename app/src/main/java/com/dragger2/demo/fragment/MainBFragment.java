package com.dragger2.demo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.commmonlibrary.cn.base.BaseFragment;
import com.commmonlibrary.cn.utils.LogUtil;
import com.dragger2.demo.R;
import com.dragger2.demo.activity.fragatation.FourBctivity;

/**
 * Created by chawei on 2018/6/20.
 */

public class MainBFragment extends BaseFragment{


    private View mView;

    public static MainBFragment getInstance(String content){
        MainBFragment fragmentA=new MainBFragment();
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
//            start(FragmentA.getInstance("我是通过 start 产生的。。。"));
//            startForResult(FragmentA.getInstance("我是通过 start 产生的。。。"),1001);
            FourBctivity.launch(getActivity(),"我是从 MainBFragment 中传过来的",1001);
        });

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        LogUtil.debug("MainBFragment 中的 onActivityResult 方法");
        if (requestCode == 1001 && resultCode == 1001) {
            String content=data.getString("key");
            LogUtil.debug("requestCode----->"+requestCode+"    data----->"+content);
        }
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
        LogUtil.debug("显示---->"+key+"   栈顶的 Fragment---->"+getTopFragment());
    }

//    @Override
//    public boolean onBackPressedSupport() {
//        LogUtil.debug("MainBFragment 返回键-------->");
////        return super.onBackPressedSupport();
//        start(FragmentA.getInstance(""));
//        return true;
//    }
}
