package com.earlysleep.model;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zml on 2016/6/23.
 * 介绍：
 */
public class AllData extends DataSupport {
    private  String music;
    private  int musictime;
    private  boolean musicchosse;
    List<TimeSeting> list=new ArrayList<>();
}
