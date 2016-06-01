package com.earlysleep.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.earlysleep.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zml on 2016/5/31.
 * 主页的左边菜单
 */
public class MenuLeftFragment extends Fragment {

    private View mView;
    private ListView mCategories;
    private List<String> mDatas = Arrays
            .asList("1","2");

    private ListAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        if (mView == null)
        {
            initView(inflater, container);
        }
        return mView;
    }

    private void initView(LayoutInflater inflater, ViewGroup container)
    {
        mView = inflater.inflate(R.layout.left_menu, container, false);
        mCategories = (ListView) mView
                .findViewById(R.id.id_listview_categories);
        mAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, mDatas);
        mCategories.setAdapter(mAdapter);
    }

}
