package com.dragger2.demo.di.component;

import com.dragger2.demo.activity.MainActivity;
import com.dragger2.demo.di.PerActivity;
import com.dragger2.demo.di.module.ActivityModule;

import dagger.Component;

/**
 * Created by chawei on 2018/6/14.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}
