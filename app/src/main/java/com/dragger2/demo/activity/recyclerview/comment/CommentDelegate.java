package com.dragger2.demo.activity.recyclerview.comment;

import android.support.v7.widget.RecyclerView;

import com.commmonlibrary.cn.delegatemvp.view.AppDelegate;
import com.dragger2.demo.R;

/**
 * Created by chawei on 2018/7/11.
 */

public class CommentDelegate extends AppDelegate {


    @Override
    public int getRootLayoutId() {
        return R.layout.ac_comment_list;
    }

    @Override
    public void initWidget() {
        super.initWidget();

        RecyclerView rv=get(R.id.rv);


    }

}
