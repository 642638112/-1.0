package com.earlysleep.Application;

import org.litepal.LitePalApplication;

/**
 * Created by zml on 2016/6/1.
 * 介绍：整个应用的初始化 以及配置
 */
public class MyApplication extends LitePalApplication  {
    @Override
    public void onCreate() {
        super.onCreate();
        LitePalApplication.initialize(this);//配置数据库
    }
}
