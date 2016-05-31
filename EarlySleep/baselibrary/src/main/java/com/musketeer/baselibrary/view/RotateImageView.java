/**   
* @Title: RotateImageView.java 
* @Package com.musketeer.lib.view 
*
* @author musketeer zhongxuqi@163.com  
* @date 2014-12-10 下午9:16:14 
* @version V1.0   
*/
package com.musketeer.baselibrary.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

/**
 * @author zhongxuqi
 *
 */
public class RotateImageView extends ImageView {
	private RotateAnimation mRotateAnimation;

	/**
	 * @param context
	 * @param attrs
	 * @param defStyleAttr
	 */
	public RotateImageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		init();
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public RotateImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}

	/**
	 * @param context
	 */
	public RotateImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}
	
	public void init() {
		mRotateAnimation=new RotateAnimation(0, 360, 
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		mRotateAnimation.setFillAfter(true);
		mRotateAnimation.setDuration(1000);
		mRotateAnimation.setInterpolator(new LinearInterpolator());
		mRotateAnimation.setRepeatCount(Animation.INFINITE);
		mRotateAnimation.setRepeatMode(Animation.RESTART);
		setAnimation(mRotateAnimation);
	}

	@Override
	protected void onAttachedToWindow() {
		// TODO Auto-generated method stub
		super.onAttachedToWindow();
		init();
	}

}
