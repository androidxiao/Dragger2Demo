package com.commmonlibrary.cn.router;

import android.app.Activity;

/**
 * Created by wei on 2017/6/19.
 */

public interface RouterCallback {

    void onBefore(Activity from, Class<?> to);

    void onNext(Activity from, Class<?> to);

    void onError(Activity from, Class<?> to, Throwable throwable);

}
