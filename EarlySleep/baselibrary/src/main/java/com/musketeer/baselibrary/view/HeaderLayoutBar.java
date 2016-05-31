/**   
* @Title: HeaderLayoutBar.java 
* @Package com.musketeer.datasearch.view 
*
* @author musketeer zhongxuqi@163.com  
* @date 2015-3-26 下午1:04:23 
* @version V1.0   
*/
package com.musketeer.baselibrary.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

public class HeaderLayoutBar extends FrameLayout {

	public HeaderLayoutBar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		init();
	}

	public HeaderLayoutBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}

	public HeaderLayoutBar(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}
	
	private void init() {
		
	}

	@Override
	public void addView(View child) {
		// TODO Auto-generated method stub
		LayoutParams params=new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		child.setLayoutParams(params);
		super.addView(child);
	}

}
