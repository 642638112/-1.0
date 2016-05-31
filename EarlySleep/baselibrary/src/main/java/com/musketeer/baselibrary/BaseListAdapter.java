package com.musketeer.baselibrary;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseListAdapter<T> extends BaseAdapter {

	protected Context mContext;
	protected List<T> mDataList;

	public BaseListAdapter(Context context, List<T> list) {
		super();
		// TODO Auto-generated constructor stub
		mContext=context;
		mDataList=new ArrayList<T>();
		if (list!=null) {
			mDataList.addAll(list);
		}
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mDataList.size();
	}

	@Override
	public T getItem(int position) {
		// TODO Auto-generated method stub
		return mDataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	public void addItem(T item) {
		mDataList.add(item);
		notifyDataSetChanged();
	}
	
	public void addItemByIndex(T item,int index) {
		mDataList.add(index,item);
		notifyDataSetChanged();
	}
	
	public void addList(List<T> list) {
		if (list!=null) {
			mDataList.addAll(list);
			notifyDataSetChanged();
		}
	}
	
	public void refreshList(List<T> list) {
		mDataList.clear();
		if (list!=null) {
			mDataList.addAll(list);
		}
		notifyDataSetChanged();
	}

}
