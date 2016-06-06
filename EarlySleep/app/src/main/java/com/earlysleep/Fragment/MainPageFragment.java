package com.earlysleep.Fragment;

import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.earlysleep.Activity.AddTimeActivity;
import com.earlysleep.Activity.MainActivity;
import com.earlysleep.R;
import com.earlysleep.View.SwitchView;
import com.earlysleep.model.TimeSeting;
import com.musketeer.baselibrary.Activity.BaseFragment;

import org.litepal.crud.DataSupport;

import java.lang.reflect.Field;
import java.util.List;

import butterknife.Bind;

/**
 * Created by zml on 2016/6/2.
 * 介绍：
 */
public class MainPageFragment extends BaseFragment {
    @Bind(R.id.headbar_left_imagebutton)
      ImageView setting;//设置按钮
    @Bind(R.id.headbar_right_imagebutton)
      ImageView add;//添加按钮
    @Bind(R.id.headbar_title)
      TextView title;
    @Bind(R.id.default_headbar)
       RelativeLayout headerbar;
    @Bind(R.id.numpick)
    NumberPicker numPick;
    @Bind(R.id.numpickmin)
    NumberPicker numPick2;
    @Bind(R.id.switch_view_ll)//switch_view
    LinearLayout switch_view_ll;

            SwitchView switch_view;
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
        switch_view=new SwitchView(getActivity());
        switch_view_ll.addView(switch_view);
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

    /**
     *
     */
    @Override
    public void initData() {
        numPick.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);//设置numPick
        numPick.setMaxValue(24);
        numPick.setMinValue(0);
        numPick.setValue(1);


        setNumberPickerDividerColor(numPick);

        numPick2.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);//设置numPick
        String[] minute = {"0","15","30","45","60"};
        numPick2.setDisplayedValues(minute);
        numPick2.setMinValue(0);
        numPick2.setMaxValue(minute.length - 1);
        numPick2.setValue(0);
        setNumberPickerDividerColor(numPick2);
        title.setText(R.string.mianpege_title_naem);

        qurydata();
    }

    private void qurydata() {
        TimeSeting timeSeting = DataSupport.find(TimeSeting.class, 1);
    //    if(timeSeting.s)
      //  System.out.println("timeSeting:time="+timeSeting.getTime());
        List<TimeSeting> allNews = DataSupport.findAll(TimeSeting.class);
        System.out.println("allNews:time="+allNews.size());
        System.out.println(allNews.get(5).getTime());
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.headbar_left_imagebutton:
                    showMenu();
                break;
            case R.id.headbar_right_imagebutton:startActivity(AddTimeActivity.class);
                break;



        }
    }

    private void showMenu() {
        handler.sendEmptyMessage(100);//将点击事件返回给MainActivity进行处理

    //    SlidingMenu menu = MainActivity.()


    }

    /**
     * @param numberPicker s设置numberpicker的分割线颜色  设为透明
     */
    private void setNumberPickerDividerColor(NumberPicker numberPicker) {
        NumberPicker picker = numberPicker;
        Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDivider")) {
                pf.setAccessible(true);
                try {
//设置分割线的颜色值
                    pf.set(picker, new ColorDrawable(this.getResources().getColor(R.color.transparent)));
// pf.set(picker, new Div)
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}
