package com.commmonlibrary.cn.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.commmonlibrary.cn.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by chawei on 2018/6/22.
 *
 */

public class CountDownBtn extends RoundBtn {

    private Disposable mDisposable;

    public CountDownBtn(Context context) {
        this(context, null);
    }

    public CountDownBtn(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CountDownBtn(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    private void initView(Context context) {
        //explain start：起始数值 count：发射数量  initialDelay：延迟执行时间 period：发射周期时间  unit：时间单位
        setText(context.getString(R.string.send_code_msg));
        setOnClickListener(view -> {

            if (mListener != null) {
                mListener.start();
            }

            mDisposable = Flowable.intervalRange(1, mCountDownTime, 0, 1, TimeUnit.SECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext(aLong -> {
                        setEnable(false);
                        setText(context.getString(R.string.get_code_msg_again) + " " + ((mCountDownTime) - aLong) + " 秒");
                    })
                    .doOnComplete(() -> {
                        setEnable(true);
                        setText(context.getString(R.string.send_code_msg));
                    })
                    .subscribe();
        });
    }



    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
            mDisposable = null;
        }

        if (mListener != null) {
            mListener=null;
        }
    }

    private ISendCodeListener mListener;

    public void setISendCodeListener(ISendCodeListener listener) {
        mListener=listener;
    }

    public interface ISendCodeListener{
        void start();
    }
}
