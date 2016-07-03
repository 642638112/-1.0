package com.earlysleep.manager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.earlysleep.Reciver.LoongggAlarmReceiver;

import java.util.Calendar;

/**
 * Created by loonggg on 2016/3/21.
 */
public class AlarmManagerUtil {
    public static final String ALARM_ACTION = "com.loonggg.alarm.clock";

    public static void setAlarmTime(Context context, long timeInMillis, Intent intent) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent sender = PendingIntent.getBroadcast(context, intent.getIntExtra("id", 0),
                intent, PendingIntent.FLAG_CANCEL_CURRENT);
        int interval = (int) intent.getLongExtra("intervalMillis", 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            am.setWindow(AlarmManager.RTC_WAKEUP, timeInMillis, interval, sender);
        }
    }

    public static void cancelAlarm(Context context, String action, int id) {
        Intent intent = new Intent(action);
        PendingIntent pi = PendingIntent.getBroadcast(context, id, intent, PendingIntent
                .FLAG_CANCEL_CURRENT);
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        am.cancel(pi);
    }


    public static void setAlarm(Context context, int flag, int hour, int minute, int id, int
            week, String tips, int soundOrVibrator) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        long intervalMillis = 0;
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get
                (Calendar.DAY_OF_MONTH), hour, minute, 0);
        if (flag == 0) {//周一到周日 每天都
            intervalMillis = 24 * 3600 * 1000;
        } else if (flag == 1) { // 周一 周二
            intervalMillis = 24 * 3600 * 1000;
        } else if (flag == 2) {// 没周周一
            intervalMillis = 24 * 3600 * 1000 * 7;
        }
        Intent intent = new Intent(context, LoongggAlarmReceiver.class);
        intent.putExtra("intervalMillis", intervalMillis);
        intent.putExtra("msg", tips);
        intent.putExtra("id", id);
        intent.putExtra("soundOrVibrator", soundOrVibrator);
        intent.putExtra("time", hour+minute+"");
        PendingIntent sender = PendingIntent.getBroadcast(context, id, intent, PendingIntent
                .FLAG_CANCEL_CURRENT);

            if (flag == 0) {//每天
                am.setRepeating(AlarmManager.RTC_WAKEUP, calMethod(week, calendar.getTimeInMillis
                        ()), intervalMillis, sender);

            } else if(flag==2){
                am.setRepeating(AlarmManager.RTC_WAKEUP, calMethod(week, calendar.getTimeInMillis
                        ()), intervalMillis, sender);
            }
        else if(flag==1){//后续更改   设定一次
              //  am.set();
            }

    }


    private static long calMethod(int weekflag, long dateTime) {
        long time = 0;

        if (weekflag != 0) {
            Calendar c = Calendar.getInstance();
            int week = c.get(Calendar.DAY_OF_WEEK);
            if (1 == week) {
                week = 7;
            } else if (2 == week) {
                week = 1;
            } else if (3 == week) {
                week = 2;
            } else if (4 == week) {
                week = 3;
            } else if (5 == week) {
                week = 4;
            } else if (6 == week) {
                week = 5;
            } else if (7 == week) {
                week = 6;
            }

            if (weekflag == week) {
                System.out.println("xiangdeng");
                if (dateTime > System.currentTimeMillis()) {
                    time = dateTime;
                } else {
                    time = dateTime + 7 * 24 * 3600 * 1000;
                }
            } else if (weekflag > week) {
                time = dateTime + (weekflag - week) * 24 * 3600 * 1000;
            } else if (weekflag < week) {
                time = dateTime + (weekflag - week + 7) * 24 * 3600 * 1000;
            }
        } else {
            if (dateTime > System.currentTimeMillis()) {
                time = dateTime;
            } else {
                time = dateTime + 24 * 3600 * 1000;
            }
        }
        return time;
    }


}
