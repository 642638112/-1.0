package com.earlysleep.Reciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.earlysleep.Activity.AddTimeActivity;
import com.earlysleep.model.TimeSeting;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by loongggdroid on 2016/3/21.
 * ���ӳ�������������ָ��ʱ�������÷������ڴ���ʱ������
 */
public class LoongggAlarmReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
       // String msg = intent.getStringExtra("msg");
        Toast.makeText(context,"Loggingreciever",Toast.LENGTH_SHORT).show();
        //int flag = intent.getIntExtra("soundOrVibrator", 0);
       String time= intent.getStringExtra("time");//��ȡʱ��
        Intent clockIntent = new Intent(context, AddTimeActivity.class);
        clockIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(clockIntent);
        quredata(time);

    }

    /**
     * ��ѯ��ʱ���Ӧ�洢����Ϣ ��ѯ���ݿ� ��ʱ��Ϊ��ѯ����
     */
    private void quredata(String t) {
        List<TimeSeting> allNews = DataSupport.findAll(TimeSeting.class);
    }


}
