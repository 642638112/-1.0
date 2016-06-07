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
    public timeadapter(Context context,List<TimeSeting> list){
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
        String t=list.get(position).getWeekday();
//        System.out.println(t.size());
   //     System.out.println(t.get(0)+"-----------");
    viewHolder.day.setText(t);//需要更改格式
        return convertView;
    }

    /**
     * @param t 传入List 返回 需要的格式
     */
    private String format(List<String> t) {
        StringBuilder s=new StringBuilder();
        if(t.size()==1){
            s.append(t.get(0));
        }
        else if(t.size()>1){
            for(int i=0;i<t.size()-1;i++){
                s.append(t.get(i)+"、");
            }
            s.append(t.get(t.size()-1));
        }
        return s.toString();
    }

    public class ViewHolder{
      //  boolean flag;
        TextView time;
        TextView  day;
    //    List<String> day;
    }
}
