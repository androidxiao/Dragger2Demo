package com.dragger2.demo.data;

import android.content.Context;

import com.dragger2.demo.data.model.User;
import com.dragger2.demo.di.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by chawei on 2018/6/13.
 */

@Singleton
public class DataManager {

    private Context mContext;
    private DbHelper mDbHelper;
    private SharedPrefsHelper mSharedPrefsHelper;

    @Inject
    public DataManager(@ApplicationContext Context context,DbHelper dbHelper,SharedPrefsHelper sharedPrefsHelper){
        mContext=context;
        mDbHelper=dbHelper;
        mSharedPrefsHelper=sharedPrefsHelper;
    }

    public void saveAccessToken(String accessToken) {
        mSharedPrefsHelper.put(SharedPrefsHelper.PREF_KEYACCESS_TOKEN, accessToken);
    }

    public String getAccessToken(){
        return mSharedPrefsHelper.get(SharedPrefsHelper.PREF_KEYACCESS_TOKEN, null);
    }

    public long createUser(User user){
        try {
            return mDbHelper.insertUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public User  getUser(Long userId){
        return mDbHelper.getUser(userId);
    }
}
