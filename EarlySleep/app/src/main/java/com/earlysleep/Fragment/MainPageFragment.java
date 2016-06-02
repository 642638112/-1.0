package com.earlysleep.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.earlysleep.Activity.MainActivity;
import com.earlysleep.R;
import com.musketeer.baselibrary.Activity.BaseFragment;

import butterknife.Bind;

/**
 * Created by zml on 2016/6/2.
 * 介绍：
 */
public class MainPageFragment extends BaseFragment {
    @Bind(R.id.headbar_left_imagebutton)
      ImageView setting;
    @Bind(R.id.headbar_right_imagebutton)
      ImageView add;
    @Bind(R.id.headbar_title)
     TextView title;
    @Bind(R.id.default_headbar)
     RelativeLayout headerbar;
    @Bind(R.id.numpick)
    NumberPicker numPick;
    private Handler handler;

    @Override
    public void setContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        BaseView = inflater.inflate(R.layout.fragemnt_mainpage, null);
        MainActivity activity=(MainActivity) getActivity();
        /**
         * 得到Activity的Handler
         */
        handler=activity.handler;
    }

    @Override
    public void initView() {
        headerbar.setBackgroundColor(getResources().getColor(R.color.titleblue));
        setting.setVisibility(View.VISIBLE);
        setting.setImageResource(R.mipmap.setting);
        setting.setBackground(getResources().getDrawable(R.drawable.button_default_bg));
        add.setVisibility(View.VISIBLE);
        add.setImageResource(R.mipmap.add);
        add.setBackground(getResources().getDrawable(R.drawable.button_default_bg));
        title.setTextColor(getResources().getColor(R.color.white));
    }

    @Override
    public void initEvent() {
        setting.setOnClickListener(this);
        add.setOnClickListener(this);

    }

    @Override
    public void initData() {
        numPick.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numPick.setMaxValue(100);
        numPick.setMinValue(0);
        numPick.setValue(50);
        title.setText(R.string.mianpege_title_naem);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.headbar_left_imagebutton:
                    showMenu();



        }
    }

    private void showMenu() {
        handler.sendEmptyMessage(100);//将点击事件返回给MainActivity进行处理

    //    SlidingMenu menu = MainActivity.()


    }
}
