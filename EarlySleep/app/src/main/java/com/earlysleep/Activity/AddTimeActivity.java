package com.earlysleep.Activity;

import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.earlysleep.R;
import com.earlysleep.View.WheelView;
import com.earlysleep.manager.AlarmManagerUtil;
import com.earlysleep.model.TimeSeting;
import com.musketeer.baselibrary.Activity.BaseActivity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    @Bind(R.id.setting_time_sw)
    Switch sw;
    TimeSeting t;
    private boolean flag1,flag2,flag3,flag4,flag5,flag6,flag7;
    private  int cicle=0;//标志位 判断选中的天数
    String snumber="";//选中那几天
    //  private int[] WEEK;
    /**
     *
     */
    private static final String[] PLANETS = new String[]{"1", "2", "3", "4", "5", "6", "7", "8","9","10","11","12"};
    private static final String [] MINUTES=new String[60];
    private  String timehour;
    private String timeminute;
    List<String> day=new ArrayList<>();//记录  设定的天数

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
            else{
                MINUTES[i]=i+"";
            }

        }

        wheelView.setOffset(2);
        wheelView.setItems(Arrays.asList(PLANETS));
        wheelView.setSeletion(10 + 3);

        wheelView1.setOffset(2);
        wheelView1.setItems(Arrays.asList(MINUTES));
        wheelView1.setSeletion(100);
        timehour=wheelView.getSeletedItem();
        timeminute=wheelView1.getSeletedItem();
        // wheelView.seton
        wheelView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                timehour = item;
            }
        });
        wheelView1.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                timeminute = item;
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
            case R.id.headbar_right_imagebutton_two://确定
                // finish();
                saveinfor();
                finish();
                break;
            case R.id.monday_tv: TextPaint tp1 = monday.getPaint();
                if(flag1==true){
                    tp1.setFakeBoldText(false);
                    monday.setSelected(false);
                    flag1=false;
                    System.out.println("取消选择");
                }
                else {monday.setSelected(true);  System.out.println("选择");
                    flag1=true;
                    tp1.setFakeBoldText(true);
                }
                break;
            case R.id.tuesday_tv:
                TextPaint tp2 = tuesday.getPaint();
                if(flag2==true){

                    tuesday.setSelected(false);
                    flag2=false;
                    System.out.println("取消选择");   tp2.setFakeBoldText(false);
                }
                else {tuesday.setSelected(true);  System.out.println("选择");
                    flag2=true; tp2.setFakeBoldText(true);
                }
                //   finish();
                break;
            case R.id.wednesday_tv:
                TextPaint tp3 = wednesday.getPaint();
                if(flag3==true){
                    wednesday.setSelected(false);
                    flag3=false;
                    System.out.println("取消选择");   tp3.setFakeBoldText(false);
                }
                else {wednesday.setSelected(true);  System.out.println("选择");
                    flag3=true; tp3.setFakeBoldText(true);
                }
                // finish();
                break;
            case R.id.thursday_tv:  TextPaint tp = thursday.getPaint();
                //   finish();
                if(flag4==true){
                    thursday.setSelected(false);
                    flag4=false;
                    System.out.println("取消选择");
                    tp.setFakeBoldText(false);
                }
                else {thursday.setSelected(true);  System.out.println("选择");
                    //   thursday.

                    tp.setFakeBoldText(true);
                    flag4=true;
                }
                break;
            case R.id.friday_tv:
                TextPaint tp5 = friday.getPaint();
                //  finish();
                if(flag5==true){

                    friday.setSelected(false);
                    flag5=false;
                    System.out.println("取消选择");
                    tp5.setFakeBoldText(false);
                }
                else {friday.setSelected(true);  System.out.println("选择");
                    flag5=true;
                    tp5.setFakeBoldText(true);
                }
                break;
            case R.id.saturday_tv:
                TextPaint tp6 = saturday.getPaint();
                if(flag6==true){
                    saturday.setSelected(false);
                    flag6=false;
                    System.out.println("取消选择");
                    tp6.setFakeBoldText(false);
                }
                else {saturday.setSelected(true);  System.out.println("选择");
                    flag6=true;
                    tp6.setFakeBoldText(true);
                }
                //  finish();
                break;
            case R.id.sunday_tv:
                TextPaint tp7 = sunday.getPaint();
                if(flag7==true){
                    sunday.setSelected(false);
                    flag7=false;
                    System.out.println("取消选择");
                    tp7.setFakeBoldText(false);
                }
                else {sunday.setSelected(true);  System.out.println("选择");
                    flag7=true;
                    tp7.setFakeBoldText(true);
                }
                //  finish();


        }
    }

    /**
     * 保持添加的内容
     */
    private void saveinfor() {
        String time=timehour+":"+timeminute;
        String sdays= getdays3();
        setclock();//点击确定按钮 首先设置闹钟 然后是保存数据

        t=new TimeSeting();
        t.save();




        t.setTime(time);
        t.setWeeksday(sdays);


        t.setFlag(true);
        t.save();if (t.save()) {
            //    Toast.makeText(this, "存储成功", Toast.LENGTH_SHORT).show();
            //   List<TimeSeting> allNews = DataSupport.findAll(TimeSeting.class);

        } else {
            Toast.makeText(this, "存储失败", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     *设置定时任务  设置闹钟
     */
    public void setclock(){
        if(cicle==7){//是每天的闹钟
            System.out.println("每天的闹钟："+Integer.parseInt(timehour)+Integer.parseInt
                    (timeminute));
            AlarmManagerUtil.setAlarm(this, 0, Integer.parseInt(timehour), Integer.parseInt
                    (timeminute), 0, 0, "闹钟响了", 1);
        }
        else if(cicle==0){ //只响一次
            System.out.println("只响一次");
            AlarmManagerUtil.setAlarm(this, 1, Integer.parseInt(timehour), Integer.parseInt
                    (timeminute), 0, 0, "闹钟响了", 1);
        }
        else {int [] num2=changetonumber(snumber);
            System.out.println("设置多次");
            for(int i=0;i<num2.length;i++){//设置多次
                System.out.println(num2[i]+"");
                AlarmManagerUtil.setAlarm(this, 2, Integer.parseInt(timehour), Integer
                        .parseInt(timeminute), i, num2[i], "闹钟响了", 1);
            }


        }
        Toast.makeText(this, "闹钟设置成功", Toast.LENGTH_LONG).show();
    }

    private int[] changetonumber(String snumber) {

        int[] num = new int[snumber.length()];//定义整型数组用来接收转换的字符串数组
        for(int i=0;i<num.length;i++){
            num[i]=Integer.parseInt(snumber.charAt(i)+"");
        }
        return num;
    }

    /**
     * @return  选中的日子 字符串拼接形式
     */
    public  String getdays3(){
        String s="";

        if(flag1){
            s=s+"周一";
            cicle++;
            snumber=snumber+1;

        }
        if(flag2){ cicle++; snumber=snumber+2;
            s=s+"周二";}
        if(flag3){ s=s+"周三"; cicle++;snumber=snumber+3;}
        if(flag4){ s=s+"周四"; cicle++;snumber=snumber+4;}
        if(flag5){ s=s+"周五"; cicle++;snumber=snumber+5;
        }
        if(flag6){s=s+"周六"; cicle++;snumber=snumber+6;}
        if(flag7){ s=s+"周日"; cicle++;snumber=snumber+7;}
        if((flag1||flag2||flag3||flag4||flag5||flag6||flag7)==false){
            s="每天";
        }
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
        return  s;

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
