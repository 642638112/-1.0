package com.musketeer.baselibrary.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by zhongxuqi on 15-10-25.
 */
public interface BaseFragmentUITask {
    /**
     * 设置界面
     */
    void setContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

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
