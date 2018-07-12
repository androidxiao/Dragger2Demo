package com.dragger2.demo.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.dragger2.demo.di.ApplicationContext;
import com.dragger2.demo.di.DatabaseInfo;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chawei on 2018/6/14.
 */

@Module
public class ApplicationModule {

    Application mApplication;

    public ApplicationModule(Application application) {
        mApplication=application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication(){
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName(){
        return "demo-dragger.db";
    }

    @Provides
    @DatabaseInfo
    Integer provideDatabaseVersion(){
        return 2;
    }

    @Provides
    SharedPreferences provideSharedPrefs(){
        return mApplication.getSharedPreferences("demo-prefs", Context.MODE_PRIVATE);
    }
}
