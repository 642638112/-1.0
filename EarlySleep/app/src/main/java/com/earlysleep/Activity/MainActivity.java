package com.earlysleep.Activity;


import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.earlysleep.Fragment.MainPageFragment;
import com.earlysleep.Fragment.MenuLeftFragment;
import com.earlysleep.Fragment.SheQuFragment;
import com.earlysleep.R;
import com.earlysleep.manager.DefaultFragmentShowManager;
import com.earlysleep.manager.FragmentShowManager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MainActivity extends SlidingFragmentActivity implements View.OnClickListener{

    private FragmentShowManager fragmentShowManager;


    private LinearLayout mianpege_ll;//主页按钮

    private LinearLayout shequ_ll;//社区按钮

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
         mianpege_ll= (LinearLayout) findViewById(R.id.mianpagell);
        shequ_ll= (LinearLayout) findViewById(R.id.shequll);
        intLeftMenu();
        intView();
        intEvent();
        setBehindContentView(R.layout.left_menu_frame);
    }

    private void intEvent() {
        mianpege_ll.setOnClickListener(this);
        shequ_ll.setOnClickListener(this);

    }

    private void intView() {

        fragmentShowManager = new DefaultFragmentShowManager(this, R.id.fragment_content);
        List<Fragment> list = new ArrayList<>();
        list.add(new MainPageFragment());
        list.add(new SheQuFragment());
        fragmentShowManager.addAllFragment(list);
    }


    /**
     * 初始化slidingmemu
     */
    private void intLeftMenu() {
        android.support.v4.app.Fragment leftMenuFragment = new MenuLeftFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.id_left_menu_frame, leftMenuFragment).commit();
        SlidingMenu menu = getSlidingMenu();
        menu.setMode(SlidingMenu.LEFT);

        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);

        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);

        menu.setFadeDegree(0.35f);


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.mianpagell:
                setSelection(fragmentShowManager.getCurrentItem(), false);
                fragmentShowManager.setCurrentItem(0);
                setSelection(fragmentShowManager.getCurrentItem(), true);
                break;
            case R.id.shequll:
                setSelection(fragmentShowManager.getCurrentItem(), false);
                fragmentShowManager.setCurrentItem(1);
                setSelection(fragmentShowManager.getCurrentItem(), true);
        }


    }

    /**
     * @param index 被选中的序号
     * @param isSelected 是否被选中
     */
    private void setSelection(int index, boolean isSelected) {
        switch (index) {
            case 0:
                mianpege_ll.setSelected(isSelected);
                Toast.makeText(this,"主页被选中",Toast.LENGTH_SHORT).show();
                break;
            case 1:
                shequ_ll.setSelected(isSelected);
                Toast.makeText(this,"社区被选中",Toast.LENGTH_SHORT).show();
                break;

        }
    }

    /**
     * 回调 接收从Fragment返回的点击事件
     */
    public Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg!=null){
                switch (msg.what) {
                    case 100:
                        /**
                         * 显示slidingmenu
                         */
                        getSlidingMenu().showMenu();


                        break;

                    default:
                        break;
                }
            }
        }

    };
}
