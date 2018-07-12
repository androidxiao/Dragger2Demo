package com.commmonlibrary.cn.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.commmonlibrary.cn.R;

import static com.commmonlibrary.cn.utils.Px2DpUtil.dp2px;


/**
 * Created by chawei on 2018/5/2.
 * <p>
 * 圆点进度条
 */

public class LoadingPointView extends View {

    public static final int MESSAGE_ID = 0;
    //白色圆点
    private Paint mNormalPaint;
    //绿色圆点
    private Paint mFocusPaint;
    private Paint mRectPaint;
    //半径
    private int mRadius;
    private int mIndex;
    private final int POINT_COUNT = 3;
    private final int MARGIN;
    private final int RECTF_RADIUS;
    public static final int DELAY_TIME = 400;


    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ++mIndex;
            if (mIndex == POINT_COUNT) {
                mIndex = 0;
            }
            postInvalidate();
        }
    };


    public LoadingPointView(Context context) {
        this(context, null);
    }

    public LoadingPointView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingPointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        MARGIN = dp2px(context, 5);
        RECTF_RADIUS = dp2px(context, 5);
        initParmas(context);
    }

    private void initParmas(Context context) {

        mRectPaint = new Paint();
        mRectPaint.setAntiAlias(true);
        mRectPaint.setStyle(Paint.Style.FILL);
        mRectPaint.setColor(ContextCompat.getColor(getContext(), R.color.round_rect_bg));

        mNormalPaint = new Paint();
        mNormalPaint.setAntiAlias(true);
        mNormalPaint.setStyle(Paint.Style.FILL);
        mNormalPaint.setColor(ContextCompat.getColor(getContext(),R.color.c_normal_dot));

        mFocusPaint = new Paint();
        mFocusPaint.setAntiAlias(true);
        mFocusPaint.setStyle(Paint.Style.FILL);
        mFocusPaint.setColor(ContextCompat.getColor(getContext(),R.color.c_focus_dot));

        mRadius = (int) getResources().getDimension(R.dimen.d_loading_point_radius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF = new RectF(0, 0, getWidth(), getHeight());
        canvas.drawRoundRect(rectF, RECTF_RADIUS, RECTF_RADIUS, mRectPaint);
        for (int i = 0; i < POINT_COUNT; i++) {
            canvas.drawCircle(getWidth() / POINT_COUNT + MARGIN * i + mRadius * i * 2, getHeight() / 2, mRadius, mNormalPaint);
        }
        canvas.drawCircle(getWidth() / POINT_COUNT + MARGIN * mIndex + mRadius * mIndex * 2, getHeight() / 2, mRadius, mFocusPaint);
        //发送消息不断绘制，以达到无限循环的效果
        mHandler.sendEmptyMessageDelayed(MESSAGE_ID, DELAY_TIME);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopAnimator();
    }

    public void stopAnimator() {
        if (mHandler != null) {
            mHandler.removeMessages(MESSAGE_ID);
            mHandler = null;
        }
    }
}
