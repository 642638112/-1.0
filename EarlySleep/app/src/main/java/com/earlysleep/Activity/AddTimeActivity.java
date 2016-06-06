package com.earlysleep.Activity;

import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.earlysleep.R;
import com.earlysleep.View.WheelView;
import com.musketeer.baselibrary.Activity.BaseActivity;

import java.lang.reflect.Field;
import java.util.Arrays;

import butterknife.Bind;

/**
 * Created by zml on 2016/6/4.
 * 介绍：
 */
public class AddTimeActivity extends BaseActivity {
    @Bind(R.id.headbar_left_imagebutton_two)
     ImageView back;//返回
    @Bind(R.id.headbar_right_imagebutton_two)
      ImageView sure;//queing按钮
    @Bind(R.id.headbar_title_two)
      TextView addtitle;
    @Bind(R.id.default_headbar_two)
      RelativeLayout headerbar_two;
    @Bind(R.id.main_wv1)
    WheelView wheelView1;
    @Bind(R.id.main_wv)
    WheelView wheelView;

    @Bind(R.id.monday_tv)//周一
    TextView monday;

    @Bind(R.id.tuesday_tv)//周2
    TextView tuesday;
    @Bind(R.id.wednesday_tv)
    TextView wednesday;

    @Bind(R.id.thursday_tv)
    TextView thursday;

    @Bind(R.id.friday_tv)
    TextView friday;
    @Bind(R.id.saturday_tv)
    TextView saturday;
    @Bind(R.id.sunday_tv)
    TextView sunday;
    private boolean flag1,flag2,flag3,flag4,flag5,flag6,flag7;
        /**
     *
     */
    private static final String[] PLANETS = new String[]{"1", "2", "3", "4", "5", "6", "7", "8","9","10","11","12"};
    private static final String [] MINUTES=new String[60];


    @Override
    public void setContentView(Bundle savedInstanceState) {
          setContentView(R.layout.activity_add_time);

    }

    @Override
    public void initView() {
        headerbar_two.setBackgroundColor(getResources().getColor(R.color.titleblue));
        back.setVisibility(View.VISIBLE);
        back.setImageResource(R.mipmap.back);
        back.setBackground(getResources().getDrawable(R.drawable.button_default_bg));
        sure.setVisibility(View.VISIBLE);
        sure.setImageResource(R.mipmap.sure);
        sure.setBackground(getResources().getDrawable(R.drawable.button_default_bg));
        addtitle.setTextColor(getResources().getColor(R.color.white));
        System.out.println(addtitle.getCurrentTextColor());
    }

    @Override
    public void initEvent() {
        back.setOnClickListener(this);
        sure.setOnClickListener(this);
        monday.setOnClickListener(this);
        tuesday.setOnClickListener(this);
        wednesday.setOnClickListener(this);
        thursday.setOnClickListener(this);
        friday.setOnClickListener(this);
        saturday.setOnClickListener(this);
        sunday.setOnClickListener(this);
    }

    @Override
    public void initData() {
       for(int i=0;i<=59;i++){
          if(i<10){
              MINUTES[i]="0"+i;
          }
           MINUTES[i]=i+"";
       }

        wheelView.setOffset(2);
        wheelView.setItems(Arrays.asList(PLANETS));
        wheelView.setSeletion(10 + 3);

        wheelView1.setOffset(2);
        wheelView1.setItems(Arrays.asList(MINUTES));
        wheelView1.setSeletion(100);

        wheelView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                Log.d(TAG, "selectedIndex: " + selectedIndex + ", item: " + item);
            }
        });




        addtitle.setText(R.string.addtime_title_naem);
    }


    /**
     * @param v
     */
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.headbar_left_imagebutton_two:
                finish();
              break;
            case R.id.headbar_right_imagebutton_two:
               // finish();
                break;
            case R.id.monday_tv:
                if(flag1==true){
                    monday.setSelected(false);
                    flag1=false;
                    System.out.println("取消选择");
                }
                else {monday.setSelected(true);  System.out.println("选择");
                    flag1=true;
                }
                break;
            case R.id.tuesday_tv:
                if(flag2==true){
                    tuesday.setSelected(false);
                    flag2=false;
                    System.out.println("取消选择");
                }
                else {tuesday.setSelected(true);  System.out.println("选择");
                    flag2=true;
                }
             //   finish();
                break;
            case R.id.wednesday_tv:
                if(flag3==true){
                    wednesday.setSelected(false);
                    flag3=false;
                    System.out.println("取消选择");
                }
                else {wednesday.setSelected(true);  System.out.println("选择");
                    flag3=true;
                }
               // finish();
                break;
            case R.id.thursday_tv:
             //   finish();
                if(flag4==true){
                    thursday.setSelected(false);
                    flag4=false;
                    System.out.println("取消选择");
                }
                else {thursday.setSelected(true);  System.out.println("选择");
                    flag4=true;
                }
                break;
            case R.id.friday_tv:
              //  finish();
                if(flag5==true){
                    friday.setSelected(false);
                    flag5=false;
                    System.out.println("取消选择");
                }
                else {friday.setSelected(true);  System.out.println("选择");
                    flag5=true;
                }
                break;
            case R.id.saturday_tv:
                if(flag6==true){
                    saturday.setSelected(false);
                    flag6=false;
                    System.out.println("取消选择");
                }
                else {saturday.setSelected(true);  System.out.println("选择");
                    flag6=true;
                }
                //  finish();
                break;
            case R.id.sunday_tv:
                if(flag7==true){
                    sunday.setSelected(false);
                    flag7=false;
                    System.out.println("取消选择");
                }
                else {sunday.setSelected(true);  System.out.println("选择");
                    flag7=true;
                }
              //  finish();
                break;

        }
    }


    public void settextselct(boolean f,TextView textView){
        if(f==true){
            textView.setSelected(false);
            f=false;
            System.out.println("取消选择");
    }
    else {textView.setSelected(true);  System.out.println("选择");
            f=true;
    }

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
