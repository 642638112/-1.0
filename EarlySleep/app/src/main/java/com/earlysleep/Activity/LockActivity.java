package com.earlysleep.Activity;

import android.app.KeyguardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

import com.earlysleep.R;
import com.musketeer.baselibrary.Activity.BaseActivity;

/**
 * Created by zml on 2016/6/27.
 * 介绍：
 */
public class LockActivity extends BaseActivity {
    private int i;//锁屏时间

    @Override
    public void setContentView(Bundle savedInstanceState) {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_lock);

        Window win = getWindow();  WindowManager.LayoutParams lp = win.getAttributes();
        lp.flags |= 0x80000000;
        win.setAttributes(lp);


    }

    @Override
    public void initView() {

    }

    @Override
    public void initEvent() {
        KeyguardManager   mKeyguardManager = (KeyguardManager)this.getSystemService(Context.KEYGUARD_SERVICE);
    }

    @Override
    public void initData() {
        i=this.getIntent().getIntExtra("time",0);
    }

    //屏蔽掉Home键

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
          //  exit();
            System.out.println("返回被点击");
            return false;
        }
        if(keyCode==KeyEvent.KEYCODE_HOME){
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
