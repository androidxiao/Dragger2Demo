package com.dragger2.demo.activity.recyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.dragger2.demo.R;
import com.dragger2.demo.adapter.MyAdapter;
import com.simple.xrecyclerview.SimpleXRecyclerView;

import java.util.ArrayList;

public class SimpleXRecyclerViewActivity extends AppCompatActivity {

    private SimpleXRecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private ArrayList<String> listData;
    private ArrayList<String> listAll;
    private int refreshTime = 0;
    private int times = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simplexrecyclerview_layout);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

//        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
//        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);

        View header = LayoutInflater.from(this).inflate(R.layout.recyclerview_header, (ViewGroup) findViewById(android.R.id.content), false);
        mRecyclerView.addHeaderView(header);

        mRecyclerView.getDefaultFootView().setLoadingHint("自定义加载中提示");
        mRecyclerView.getDefaultFootView().setNoMoreHint("自定义加载完毕提示");

        // if you use setFooterView,the default footerView will unUseful
        View footMoreView = LayoutInflater.from(this).inflate(R.layout.xrecyclerview_load_more_footer, null);
//        mRecyclerView.setFootView(footMoreView, new CustomFooterViewCallBack() {
//            @Override
//            public void onLoadingMore(View view) {
//                TextView tv = view.findViewById(R.id.tv_loading);
//                tv.setText("正在加载...");
////                ((TextView)yourFooterView).setText("Loading");
//            }
//
//            @Override
//            public void onLoadMoreComplete(View view) {
//                TextView tv = view.findViewById(R.id.tv_loading);
//                tv.setText("加载完成...");
////                ((TextView)yourFooterView).setText("Load Complete");
//            }
//
//            @Override
//            public void onSetNoMore(View view, boolean noMore) {
//                TextView tv = view.findViewById(R.id.tv_loading);
//                tv.setText("都加载完了哦");
//                CircularProgressBar pb = view.findViewById(R.id.pb);
//                pb.setVisibility(View.GONE);
////                ((TextView)yourFooterView).setText("没有更多数据了");
//            }
//        });

        final int itemLimit = 2;

        // When the item number of the screen number is list.size-2,we call the onLoadMore
//        mRecyclerView.setLimitNumberToCallLoadMore(2);

        mRecyclerView.setLoadingListener(new SimpleXRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refreshTime++;
                times = 0;
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        listData.clear();
                        listAll.clear();
                        for (int i = 0; i < 20; i++) {
                            listData.add("item" + i + "after " + refreshTime + " times of refresh");
                        }
                        listAll.addAll(listData);
                        mAdapter.appendData(listAll);
                        mAdapter.notifyDataSetChanged();
                        mRecyclerView.refreshComplete();
                    }

                }, 1000);            //refresh data here
            }

            @Override
            public void onLoadMore() {
                Log.e("aaaaa", "call onLoadMore");
                if (times < 2) {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            listData.clear();
                            for (int i = 0; i < itemLimit; i++) {
                                listData.add("item" + (1 + listData.size()));
                            }
                            listAll.addAll(listData);
                            mAdapter.appendData(listAll);
                            mRecyclerView.loadMoreComplete();
                            mAdapter.notifyDataSetChanged();
                        }
                    }, 1000);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            listData.clear();
                            for (int i = 0; i < itemLimit; i++) {
                                listData.add("这是最后一次加载--->" + (1 + listData.size()));
                            }
                            listAll.addAll(listData);
                            mAdapter.appendData(listAll);
                            mRecyclerView.setNoMore(true);
                            mAdapter.notifyDataSetChanged();
                        }
                    }, 1000);
                }
                times++;
            }
        });

        listData = new ArrayList<String>();
        listAll = new ArrayList<String>();
        mAdapter = new MyAdapter(listData);
        mAdapter.setClickCallBack(
                new MyAdapter.ItemClickCallBack() {
                    @Override
                    public void onItemClick(int pos) {
                        // a demo for notifyItemRemoved
                        listData.remove(pos);
                        mRecyclerView.notifyItemRemoved(listData, pos);
                    }
                }
        );
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.refresh();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
