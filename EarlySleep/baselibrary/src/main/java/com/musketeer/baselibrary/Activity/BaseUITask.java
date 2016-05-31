package com.musketeer.baselibrary.Activity;

import android.os.Bundle;

/**
 * Created by zhongxuqi on 15-10-24.
 */
public interface BaseUITask {

    /**
     * 设置界面
     */
    void setContentView(Bundle savedInstanceState);

    /**
     * 初始化视图
     */
    void initView();

    /**
     * 初始化数据
     */
    void initEvent();

    /**
     * 初始化数据
     */
    void initData();
}
