package com.dragger2.demo.di.module;

import android.app.Activity;
import android.content.Context;

import com.dragger2.demo.di.ActivityContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chawei on 2018/6/14.
 */

@Module
public class ActivityModule {

    Activity mActivity;

    public ActivityModule(Activity activity){
        mActivity=activity;
    }


    @Provides
    @ActivityContext
    Context provideContext(){
        return mActivity;
    }

    @Provides
    Activity provideActivity(){
        return mActivity;
    }
}
