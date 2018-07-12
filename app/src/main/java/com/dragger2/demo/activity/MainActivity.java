package com.dragger2.demo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.commmonlibrary.cn.widget.ShapeViewGroup;
import com.dragger2.demo.DemoApplication;
import com.dragger2.demo.R;
import com.dragger2.demo.activity.customtablayout.TabLayoutHomeActivity;
import com.dragger2.demo.activity.customview.BubbleLayoutActivity;
import com.dragger2.demo.activity.customview.CustomBtnActivity;
import com.dragger2.demo.activity.customview.RoundViewGroupActivity;
import com.dragger2.demo.activity.delegatemvp.TestDelegateMvpActivity;
import com.dragger2.demo.activity.fragatation.ThirdActivity;
import com.dragger2.demo.activity.fragatation.TwoActivity;
import com.dragger2.demo.activity.glideapp.GlideAppActivity;
import com.dragger2.demo.activity.recyclerview.SimpleXRecyclerViewActivity;
import com.dragger2.demo.data.DataManager;
import com.dragger2.demo.data.model.User;
import com.dragger2.demo.di.ApplicationContext;
import com.dragger2.demo.di.component.ActivityComponent;
import com.dragger2.demo.di.component.DaggerActivityComponent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    DataManager mDataManager;

    private ActivityComponent mActivityComponent;

    @ApplicationContext
    private Context mContext;

    private TextView mTvUserInfo;
    private TextView mTvAccessToken;

    public ActivityComponent getActivityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .applicationComponent(DemoApplication.get(this).getComponent())
                    .build();
        }
        return mActivityComponent;
    }

    private RecyclerView recyclerView;
    private List<Class> mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);

        mTvUserInfo = findViewById(R.id.tv_user_info);
        mTvAccessToken = findViewById(R.id.tv_access_token);


        findView();
        initData();
        setAdapter();

    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        createUser();
        getUser();
        mDataManager.saveAccessToken("lsj[fwnn223oj001844");
        String token = mDataManager.getAccessToken();
        if (token != null) {
            mTvAccessToken.setText(token);
        }
    }

    private void createUser() {
        mDataManager.createUser(new User("Jack", "北京，朝阳区"));
    }

    private void getUser() {
        User user = mDataManager.getUser(1L);
        mTvUserInfo.setText(user.toString());
    }

    private void findView() {
        recyclerView = findViewById(R.id.id_recycleview);

    }

    private void initData() {
        mActivity = new ArrayList<>();
        mActivity.add(TwoActivity.class);
        mActivity.add(ThirdActivity.class);
        mActivity.add(CustomBtnActivity.class);
        mActivity.add(RoundViewGroupActivity.class);
        mActivity.add(SimpleXRecyclerViewActivity.class);
        mActivity.add(TestDelegateMvpActivity.class);
        mActivity.add(GlideAppActivity.class);
        mActivity.add(BubbleLayoutActivity.class);
        mActivity.add(TabLayoutHomeActivity.class);
    }

    private void intentTo(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    private void setAdapter() {
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new ViewHolder(getLayoutInflater().inflate(R.layout.item_textview_view, parent, false));
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
                ViewHolder vh = (ViewHolder) holder;
                vh.text.setText(mActivity.get(position).getSimpleName());
//                vh.text.setOnClickListener(view -> {
//                            intentTo(mActivity.get(position));
//                        }
//                );
//
                vh.mShapeViewGroup.setOnClickListener(view->{
                    intentTo(mActivity.get(position));

                });
            }

            @Override
            public int getItemCount() {
                return mActivity.size();
            }

            class ViewHolder extends RecyclerView.ViewHolder {

                ShapeViewGroup mShapeViewGroup;
                TextView text;

                public ViewHolder(View itemView) {
                    super(itemView);

                    mShapeViewGroup = itemView.findViewById(R.id.id_shape_vg);
                    text = itemView.findViewById(R.id.id_tv_test);
                }

            }
        });
    }
}
