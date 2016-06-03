package com.earlysleep.View;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.earlysleep.R;

public class SwitchView extends LinearLayout implements OnClickListener {
	
	private static final int FLAG_MOVE_TRUE = 1; // ���󻬶���ʶ
	private static final int FLAG_MOVE_FALSE = 2; // ���һ�����ʶ
	
	private static final int HANDLE_LAYOUT_CURSOR = 100; // ������ÿ��ص�layout����
	
	private Context context; // �����Ķ���
	private RelativeLayout sv_container; // SwitchView�����Layout
	private ImageView iv_switch_cursor; // �����ʱ��ImageView

	
	private boolean isChecked = true; // �Ƿ��ѿ�
	private boolean checkedChange = false; // isChecked�Ƿ��иı�
	private OnCheckedChangeListener onCheckedChangeListener; // ���ڼ���isChecked�Ƿ��иı�
	
	private int margin =20; // �α����Եλ��(���ֵ��ͼƬ����, ��Ҫ��Ϊ��ͼƬ����ʾ��ȷ)
	private int bg_left; // ������
	private int bg_right; // ������
	private int cursor_left; // �α���
	private int cursor_top; // �α궥��
	private int cursor_right; // �α��Ҳ�
	private int cursor_bottom; // �α�ײ�
	
	private Animation animation; // �ƶ�����
	private int currentFlag = FLAG_MOVE_TRUE; // ��ǰ�ƶ�����flag

	public SwitchView(Context context) {
		super(context);
		this.context = context;
		initView();
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);

		bg_left = sv_container.getLeft();
		bg_right = sv_container.getRight();
		cursor_left = iv_switch_cursor.getLeft();
		cursor_top = iv_switch_cursor.getTop();
		cursor_right = iv_switch_cursor.getRight();
		cursor_bottom = iv_switch_cursor.getBottom();
	}
	
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch(msg.what) {
			case HANDLE_LAYOUT_CURSOR:
				iv_switch_cursor.layout(cursor_left, cursor_top, cursor_right, cursor_bottom);
				break;
			}
		}
	};
	
	public void onClick(View v) {

		if(v == this) {
			changeChecked(!isChecked);
		}
	}

	/**
	 * ��ʼ���ؼ�
	 */
	private void initView() {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.switch_view, this);
		view.setOnClickListener(this);
		sv_container = (RelativeLayout) view.findViewById(R.id.sv_container);


		iv_switch_cursor = (ImageView) view.findViewById(R.id.iv_switch_cursor);
		iv_switch_cursor.setClickable(false);
		iv_switch_cursor.setOnTouchListener(new OnTouchListener() {
			int lastX; //
			
			public boolean onTouch(View v, MotionEvent event) {
				switch(event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					lastX = (int) event.getRawX();
					
					cursor_left = v.getLeft();
					cursor_top = v.getTop();
					cursor_right = v.getRight();
					cursor_bottom = v.getBottom();
					break;
				case MotionEvent.ACTION_MOVE:
					int dx = (int) event.getRawX() - lastX;
					
					cursor_left = v.getLeft() + dx;
					cursor_right = v.getRight() + dx;
					
					// �����߽紦��
					if(cursor_left <= bg_left + margin) {
						cursor_left = bg_left + margin;
						cursor_right = cursor_left + v.getWidth();
					}
					if(cursor_right >= bg_right - margin) {
						cursor_right = bg_right - margin;
						cursor_left = cursor_right - v.getWidth();
					}
					v.layout(cursor_left, cursor_top, cursor_right, cursor_bottom);
					
					lastX = (int) event.getRawX();
					break;
				case MotionEvent.ACTION_UP:
					calculateIscheck();
					break;
				}
				return true;
			}
		});
	}
	
	/**
	 * ���㴦��true����false����, �����ı䴦��
	 */
	private void calculateIscheck() {
		float center = (float) ((bg_right - bg_left) / 2.0);
		float cursor_center = (float) ((cursor_right - cursor_left) / 2.0);
		if(cursor_left + cursor_center <= center) {
			changeChecked(true);
		} else {
			changeChecked(false);
		}
	}
	
	/**
	 * �ı�checked, ����checked�ƶ��α�
	 * @param isChecked
	 */
	private void changeChecked(boolean isChecked) {
		if(this.isChecked != isChecked) {
			checkedChange = true;
		} else {
			checkedChange = false;
		}
		if(isChecked) {
			currentFlag = FLAG_MOVE_TRUE;
		} else {
			currentFlag = FLAG_MOVE_FALSE;
		}
		cursorMove();
	}
	
	/**
	 * �α��ƶ�
	 */
	private void cursorMove() {

		animation = null;
		final int toX;
		if(currentFlag == FLAG_MOVE_TRUE) {
			toX = cursor_left - bg_left - margin;
			animation = new TranslateAnimation(0, -toX, 0, 0);
		} else {
			toX = bg_right - margin - cursor_right;
			animation = new TranslateAnimation(0, toX, 0, 0);
		}
		animation.setDuration(100);
		animation.setInterpolator(new LinearInterpolator());
		animation.setAnimationListener(new AnimationListener() {
			public void onAnimationStart(Animation animation) {
				
			}
			public void onAnimationRepeat(Animation animation) {
				
			}
			public void onAnimationEnd(Animation animation) {

				if(currentFlag == FLAG_MOVE_TRUE) {
					cursor_left -= toX;
					cursor_right = cursor_left + iv_switch_cursor.getWidth();
				} else {
					cursor_right = bg_right - margin;
					cursor_left = cursor_right - iv_switch_cursor.getWidth();
				}

				mHandler.sendEmptyMessageDelayed(HANDLE_LAYOUT_CURSOR, 5);

				if(checkedChange) {
					isChecked = !isChecked;
					if(onCheckedChangeListener != null) {
						onCheckedChangeListener.onCheckedChanged(isChecked);
					}

				}
			}
		});
		iv_switch_cursor.startAnimation(animation);
 	}
	
	/**
	 * �ı�������ʾ��ɫ
	 */
	private void changeTextColor() {
		if(isChecked) {

		} else {

		}
	}
	
	/**
	 * layout�α�
	 */
	private void layoutCursor() {
		if(isChecked) {
			cursor_left = bg_left + margin;
			cursor_right = bg_left + margin + iv_switch_cursor.getWidth();
		} else {
			cursor_left = bg_right - margin - iv_switch_cursor.getWidth();
			cursor_right = bg_right - margin;
		}
		iv_switch_cursor.layout(cursor_left, cursor_top, cursor_right, cursor_bottom);
	}
	
	/**
	 * isCheckedֵ�ı������
	 */
	public interface OnCheckedChangeListener {
		void onCheckedChanged(boolean isChecked);
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		if(this.isChecked != isChecked) {
			this.isChecked = isChecked;
			if(onCheckedChangeListener != null) {
				onCheckedChangeListener.onCheckedChanged(isChecked);
			}
			layoutCursor();
		}
	}

	public void setOnCheckedChangeListener(
			OnCheckedChangeListener onCheckedChangeListener) {
		this.onCheckedChangeListener = onCheckedChangeListener;
	}

}
