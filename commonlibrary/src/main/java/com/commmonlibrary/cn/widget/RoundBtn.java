package com.commmonlibrary.cn.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;

import com.commmonlibrary.cn.R;

import static com.commmonlibrary.cn.utils.Px2DpUtil.dp2px;

/**
 * Created by chawei on 2018/6/21.
 */

public class RoundBtn extends android.support.v7.widget.AppCompatButton {


    private GradientDrawable mShape;

    //不可用颜色
    private int mUnEnableColor;
    //按下颜色
    private int mPressedColor;
    //当前颜色
    private int mNormalColor;
    //当前圆角
    private float mBorderCorner;
    //四边边框宽度
    private float mStrokeWidth;
    //四边边框颜色
    private int mBorderColor;
    /**
     *  倒计时时间，如果要从初始值开始倒计时，需要多加 1（要从 5 秒开始，得写 6）
     */
    protected int mCountDownTime;

    private boolean mIsTouchPass = true;

    private boolean mIsEnable;

    private Context mContext;

    public RoundBtn(Context context) {
        this(context,null);
    }

    public RoundBtn(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RoundBtn(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
        initView(context,attrs);
    }

    private void initView(Context context, AttributeSet attrs){
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RoundBtn);
        mUnEnableColor = ta.getColor(R.styleable.RoundBtn_bgUnAbleColor, ContextCompat.getColor(context,R.color.light_blue_color));
        mNormalColor = ta.getColor(R.styleable.RoundBtn_bgAbleColor, ContextCompat.getColor(context,R.color.blue_color));
        mPressedColor = ta.getColor(R.styleable.RoundBtn_bgPressColor, ContextCompat.getColor(context,R.color.more_blue_color));
        mBorderColor = ta.getColor(R.styleable.RoundBtn_edgeColor, ContextCompat.getColor(context,R.color.blue_color));
        mStrokeWidth = ta.getInt(R.styleable.RoundBtn_edgeWidth, dp2px(context, 0));
        mBorderCorner =ta.getFloat(R.styleable.RoundBtn_roundRadius, dp2px(context, 0));
        mIsEnable = ta.getBoolean(R.styleable.RoundBtn_isEnable, true);
        mCountDownTime = ta.getInteger(R.styleable.RoundBtn_countTime, 1);


        mShape = new GradientDrawable();
        mShape.setShape(GradientDrawable.RECTANGLE);
        mShape.setCornerRadius(dp2px(context,mBorderCorner));

        setBtnStyles();

        setGravity(Gravity.CENTER);


        ta.recycle();
    }

    private void setBtnStyles() {

        setEnabled(mIsEnable);

        if(mIsEnable) {
            mShape.setColor(mNormalColor);
        }else{
            mShape.setColor(mUnEnableColor);
        }

        if (mBorderColor != 0) {
            mShape.setStroke((int) dp2px(mContext,mStrokeWidth), mBorderColor);
        }

        setBackgroundDrawable(mShape);

        if(mIsEnable) {
            //设置按钮点击之后的颜色更换
            setOnTouchListener((arg0, event) -> {
                setBackgroundDrawable(mShape);
                return setColor(event.getAction());
            });
        }

    }

    //处理按钮点击事件无效
    @Override
    public void setOnClickListener(OnClickListener l) {
        super.setOnClickListener(l);
        mIsTouchPass = false;
    }

    //处理按下去的颜色
    public boolean setColor(int action) {
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mShape.setColor(mPressedColor);
                break;
            case MotionEvent.ACTION_UP:
                mShape.setColor(mNormalColor);
                break;
            case MotionEvent.ACTION_CANCEL:
                mShape.setColor(mNormalColor);
                break;
        }

        return mIsTouchPass;
    }

    public RoundBtn setEnable(boolean isEnable){
        mIsEnable=isEnable;
        setBtnStyles();
        return this;
    }

}
