/**   
* @Title: BaseSupportFragment.java 
* @Package com.musketeer.lib.activity 
*
* @author musketeer zhongxuqi@163.com  
* @date 2015-3-15 下午3:39:25 
* @version V1.0   
*/
package com.musketeer.baselibrary.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

import com.musketeer.baselibrary.util.LogUtils;

import butterknife.ButterKnife;

/**
 * @author zhongxuqi
 *
 */
public abstract class BaseSupportFragment extends Fragment implements OnClickListener, BaseFragmentUITask{
	public static final String TAG = "Musketeer_BaseSupportFragment";

	protected View BaseView;
	
	/**屏幕的宽度*/
	protected int mScreenWidth;
	/**屏幕高度*/
	protected int mScreenHeight;
	/**屏幕密度*/
	protected float mDensity;
	
	private Toast mToast;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		DisplayMetrics metric = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(metric);
		mScreenWidth = metric.widthPixels;
		mScreenHeight = metric.heightPixels;
		mDensity = metric.density;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		setContentView(inflater, container, savedInstanceState);
		ButterKnife.bind(this, BaseView);
		initView();
		initEvent();
		initData();
		return BaseView;
	}
	
	protected void startActivity(Class<?> cls) {
		Intent intent=new Intent();
		intent.setClass(getActivity(), cls);
		startActivity(intent);
	}
	
	protected void startActivity(Class<?> cls,Bundle bundle) {
		Intent intent=new Intent();
		intent.putExtras(bundle);
		intent.setClass(getActivity(), cls);
		startActivity(intent);
	}
	
	protected void startActivityForResult(Class<?> cls,int requestCode) {
		Intent intent=new Intent();
		intent.setClass(getActivity(), cls);
		super.startActivityForResult(intent, requestCode);
	}
	
	protected void startActivityForResult(Class<?> cls,Bundle bundle,int requestCode) {
		Intent intent=new Intent();
		intent.putExtras(bundle);
		intent.setClass(getActivity(), cls);
		super.startActivityForResult(intent, requestCode);
	}
	
	protected void showCustomDebug(String text) {
		LogUtils.d(TAG, text);
	}
	
	protected void showCustomToast(String text) {
		if (mToast!=null) {
			mToast.cancel();
		}
		mToast=Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT);
		mToast.show();
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
