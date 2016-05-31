package com.musketeer.baselibrary.Activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.musketeer.baselibrary.R;
import com.musketeer.baselibrary.util.LogUtils;

import butterknife.ButterKnife;
import systembartint.SystemBarTintManager;

/**
 * Created by zhongxuqi on 15-10-24.
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener, BaseUITask {
    public static final String TAG = "Musketeer_BaseActivity";

    /**屏幕的宽度*/
    protected int mScreenWidth;
    /**屏幕高度*/
    protected int mScreenHeight;
    /**屏幕密度*/
    protected float mDensity;

    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        mScreenWidth = metric.widthPixels;
        mScreenHeight = metric.heightPixels;
        mDensity = metric.density;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.yellow));
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                setTranslucentStatus(true);
            }

            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.yellow);
        }

        setContentView(savedInstanceState);
        ButterKnife.bind(this);
        initView();
        initEvent();
        initData();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    protected void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    protected void startActivity(Class<?> cls) {
        Intent intent=new Intent();
        intent.setClass(this, cls);
        startActivity(intent);
    }

    protected void startActivity(Class<?> cls,Bundle bundle) {
        Intent intent=new Intent();
        intent.putExtras(bundle);
        intent.setClass(this, cls);
        startActivity(intent);
    }

    protected void startActivityForResult(Class<?> cls,int requestCode) {
        Intent intent=new Intent();
        intent.setClass(this, cls);
        super.startActivityForResult(intent,requestCode);
    }

    protected void startActivityForResult(Class<?> cls,Bundle bundle,int requestCode) {
        Intent intent=new Intent();
        intent.putExtras(bundle);
        intent.setClass(this, cls);
        super.startActivityForResult(intent, requestCode);
    }

    protected void showCustomDebug(String text) {
        LogUtils.d(TAG, text);
    }

    protected void showCustomToast(String text) {
        if (mToast!=null) {
            mToast.cancel();
        }
        mToast=Toast.makeText(this, text, Toast.LENGTH_SHORT);
        mToast.show();
    }

    protected void showCustomToast(int resId) {
        if (mToast!=null) {
            mToast.cancel();
        }
        mToast=Toast.makeText(this, getResources().getString(resId), Toast.LENGTH_SHORT);
        mToast.show();
    }

    @Override
    public void onClick(View v) {

    }
}
