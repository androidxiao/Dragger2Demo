package com.dragger2.demo;

import android.app.Application;
import android.content.Context;

import com.commmonlibrary.cn.utils.ActivitiesManager;
import com.dragger2.demo.data.DataManager;
import com.dragger2.demo.di.component.ApplicationComponent;
import com.dragger2.demo.di.component.DaggerApplicationComponent;
import com.dragger2.demo.di.module.ApplicationModule;

import javax.inject.Inject;

/**
 * Created by chawei on 2018/6/14.
 */

public class DemoApplication extends Application {

    protected ApplicationComponent mApplicationComponent;

    @Inject
    DataManager mDataManager;

    public static DemoApplication get(Context context) {
        return (DemoApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        mApplicationComponent.inject(this);

        ActivitiesManager activitiesManager = ActivitiesManager.getInstance();
        registerActivityLifecycleCallbacks(activitiesManager);
    }

    public ApplicationComponent getComponent(){
        return  mApplicationComponent;
    }
}
