package com.dragger2.demo.di.component;

import android.app.Application;
import android.content.Context;

import com.dragger2.demo.DemoApplication;
import com.dragger2.demo.data.DataManager;
import com.dragger2.demo.data.SharedPrefsHelper;
import com.dragger2.demo.di.ApplicationContext;
import com.dragger2.demo.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by chawei on 2018/6/14.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(DemoApplication application);

    @ApplicationContext
    Context getContext();

    Application getApplication();

    DataManager getDataManager();

    SharedPrefsHelper getSharedPrefsHelper();
}
