package com.earlysleep.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.earlysleep.R;
import com.earlysleep.model.TimeSeting;

import java.util.List;

/**
 * Created by zml on 2016/6/7.
 * 介绍：主页 时间列表 listview的adapter
 */
public class timeadapter extends BaseAdapter {
    private Context context;
    private List<TimeSeting> list;
    private LayoutInflater mInflater = null;
    public timeadapter(Context context, List<TimeSeting> list){
      //  super();
        mInflater = LayoutInflater.from(context);
        this.context=context;
        this.list=list;
    }



    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(
                    R.layout.item_time_lv, null);
            viewHolder = new ViewHolder();
            viewHolder.time = (TextView) convertView.findViewById(R.id.item_lv_timetv);
            viewHolder.day = (TextView) convertView.findViewById(R.id.item_lv_daytv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
   viewHolder.time.setText(list.get(position).getTime());
       String t=list.get(position).getWeeksday();

//        System.out.println(t.size());
   //     System.out.println(t.get(0)+"-----------");
  viewHolder.day.setText(format(t));//需要更改格式
        return convertView;
    }

    /**
     * @param t 传入String 返回 需要的格式
     * @return
     */
    private String format(String t) {
        String s1="";
        if(t==null){

        }
        else if(t.length()==2){
            s1=t;
      }
        else if(t.length()==14){
            s1= "每天";

      }
        else if(t.equals("周一周二周三周四周五")){
          s1= "工作日";
      }
        else {System.out.println(t);
          char[] arr=t.toCharArray();
            for(int i=0;i<arr.length;i++){
                System.out.println(arr[i]);
            }
         String s="";
          for(int i=0;i<arr.length-2;i=i+2){
              s=s+arr[i]+arr[i+1]+"、";
          }
          s=s+arr[arr.length-2]+arr[arr.length-1];
            s1=s;
      }
        return s1;
    }

    public class ViewHolder{
      //  boolean flag;
        TextView time;
        TextView  day;
    //    List<String> day;
    }
}
