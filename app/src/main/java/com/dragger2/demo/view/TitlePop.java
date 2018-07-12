package com.dragger2.demo.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dragger2.demo.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import static com.commmonlibrary.cn.utils.Px2DpUtil.dp2px;

/**
 * Created by chawei on 2018/5/31.
 */

public class TitlePop extends PopupWindow {

    private WeakReference<Context> mContext;
    private View view;
    private RecyclerView rv;
    private List<String> list;

    public TitlePop(Context context) {
        this(context, dp2px(context,120), RelativeLayout.LayoutParams.WRAP_CONTENT);
        mContext=new WeakReference<>(context);
    }

    public TitlePop(Context context, int with, int height) {
        setWidth(with);
        setHeight(height);
        //设置可以获得焦点
        setFocusable(true);
        //设置弹窗内可点击
        setTouchable(true);
        //设置弹窗外可点击
        setOutsideTouchable(true);
        setBackgroundDrawable(new ColorDrawable());
        view = LayoutInflater.from(context).inflate(R.layout.title_pop_layout, null);
        setContentView(view);
        setAnimationStyle(R.style.title_pop_anim_style);
        initData();
    }

    private void initData() {
        rv = view.findViewById(R.id.title_list);
        final int[] imgs=new int[]{R.mipmap.ic_short_line_search,R.mipmap.ic_short_line_share,R.mipmap.ic_short_line_setting,R.mipmap.ic_short_line_intro};
        list = new ArrayList<>();
        list.add("搜索");
        list.add("分享");
        list.add("设置");
        list.add("说明");

        rv.setAdapter(new RecyclerView.Adapter<TitleHolder>() {
            @Override
            public TitleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new TitleHolder(LayoutInflater.from(mContext.get()).inflate(R.layout.item_title_layout, parent, false));
            }

            @Override
            public void onBindViewHolder(TitleHolder holder, int position) {
                if (position == list.size() - 1) {
                    holder.line.setVisibility(View.GONE);
                } else {
                    holder.line.setVisibility(View.VISIBLE);
                }
                Drawable drawableLeft = mContext.get().getResources().getDrawable(
                        imgs[position]);

                holder.tvTitle.setCompoundDrawablesWithIntrinsicBounds(drawableLeft,
                        null, null, null);
                holder.tvTitle.setCompoundDrawablePadding(dp2px(mContext.get(),15));
                holder.tvTitle.setText(list.get(position));

                holder.setPosition(position);
            }

            @Override
            public int getItemCount() {
                return list.size();
            }
        });
    }

    class TitleHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        View line;
        int position;

        public TitleHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.id_tv_title);
            line = itemView.findViewById(R.id.id_line);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.itemClick(position);
                    }
                }
            });
        }

        public void setPosition(int position) {
            this.position=position;
        }
    }

    private IOnItemClick mListener;
    public void setListener(IOnItemClick listener){
        mListener=listener;
    }
    public interface IOnItemClick{
        void itemClick(int position);
    }
}
