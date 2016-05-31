package com.musketeer.baselibrary;

import android.app.Application;
import android.content.Intent;

/**
 * Created by zhongxuqi on 15-10-24.
 */
public class BaseApplication extends Application {
    public static BaseApplication instance;

    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public void startService(Class<?> cls) {
        Intent intent=new Intent();
        intent.setClass(this, cls);
        startService(intent);
    }
}
