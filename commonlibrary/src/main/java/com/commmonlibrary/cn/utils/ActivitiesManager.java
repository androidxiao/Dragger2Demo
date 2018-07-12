package com.commmonlibrary.cn.utils;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.Iterator;
import java.util.Stack;

/**
 * Activity 管理工具类
 *
 *
 * Created by wei on 2017/10/11.
 */
public class ActivitiesManager implements Application.ActivityLifecycleCallbacks {

    private static Stack<Activity> activityStack;

    private ActivitiesManager() {}

    private static class SingletonHolder {
        public final static ActivitiesManager instance = new ActivitiesManager();
    }

    public static ActivitiesManager getInstance() {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        return SingletonHolder.instance;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        LogUtil.debug("新加了一个 Activity----->"+activity.getClass().getName());
        addActivity(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {
    }

    @Override
    public void onActivityResumed(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        LogUtil.debug("销毁了一个 Activity---->"+activity.getClass().getName());
        removeActivity(activity);
    }


    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishLastActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        Iterator<Activity> iterator = activityStack.iterator();
        while (iterator.hasNext()) {
            Activity next = iterator.next();
            if (activity.equals(next)) {
                activity.finish();
                iterator.remove();
            }
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        Iterator<Activity> iterator = activityStack.iterator();
        while (iterator.hasNext()) {
            Activity next = iterator.next();
            if (next.getClass().equals(cls)) {
                next.finish();
                iterator.remove();
            }
        }
    }

    /**
     * 移除指定的Activity
     */
    public void removeActivity(Activity activity) {
        Iterator<Activity> iterator = activityStack.iterator();
        while (iterator.hasNext()) {
            Activity next = iterator.next();
            if (activity.equals(next)) {
                iterator.remove();
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (Activity mActivity : activityStack) {
            mActivity.finish();
        }
        activityStack.clear();
    }

    /**
     * 关闭除指定Activity外的所有Activity
     */
    public void finishExceptActivity(Class<?> cls) {
        Iterator<Activity> iterator = activityStack.iterator();
        while (iterator.hasNext()) {
            Activity activity = iterator.next();
            String acName = activity.getClass().getName();
            if (acName.equals(cls.getName())) {
                continue;
            } else {
                activity.finish();
                iterator.remove();
            }
        }
    }

    /**
     * 删除多个activity
     *
     * @param activities
     */
    public void finishMoreActivity(Class<?>... activities) {
        for (Class<?> activity : activities)
            finishActivity(activity);
    }

}
