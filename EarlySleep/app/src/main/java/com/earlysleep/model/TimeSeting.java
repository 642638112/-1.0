package com.earlysleep.model;

import org.litepal.crud.DataSupport;

/**
 * Created by zml on 2016/6/4.
 * 介绍：主页的时间信息包括（设定的具体时间  周几  以及是否打开开关）
 */
public class TimeSeting extends DataSupport {
    private String time;//具体时间 精确到分钟

    public boolean isOfforon() {
        return offoron;
    }

    public void setOfforon(boolean offoron) {
        this.offoron = offoron;
    }

    private boolean offoron;
    public String getWeeksday() {
        return weeksday;
    }

    public void setWeeksday(String weeksday) {
        this.weeksday = weeksday;
    }

    private  String weeksday;





    private boolean flag;//是否打开开关



    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }



    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
