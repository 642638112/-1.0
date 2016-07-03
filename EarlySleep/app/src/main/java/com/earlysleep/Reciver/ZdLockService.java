package com.earlysleep.Reciver;

import android.app.KeyguardManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

import com.earlysleep.Activity.MainActivity;

public class ZdLockService extends Service {

	private static String TAG = "ZdLockService";
	private Intent zdLockIntent = null ;
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public void onCreate(){
		super.onCreate();
		
		
		zdLockIntent = new Intent(ZdLockService.this , MainActivity.class);
		zdLockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		
		/*ע��㲥*/
		IntentFilter mScreenOnFilter = new IntentFilter("android.intent.action.SCREEN_ON");
		ZdLockService.this.registerReceiver(mScreenOnReceiver, mScreenOnFilter);
		
		/*ע��㲥*/
		IntentFilter mScreenOffFilter = new IntentFilter("android.intent.action.SCREEN_OFF");
		ZdLockService.this.registerReceiver(mScreenOffReceiver, mScreenOffFilter);
	}

	public int onStartCommand(Intent intent , int flags , int startId){
		
		return Service.START_STICKY;
		
	}
	
	public void onDestroy(){
		super.onDestroy();
		ZdLockService.this.unregisterReceiver(mScreenOnReceiver);
		ZdLockService.this.unregisterReceiver(mScreenOffReceiver);
		//�ڴ���������
		startService(new Intent(ZdLockService.this, ZdLockService.class));
	}
	
	private KeyguardManager mKeyguardManager = null ;
	private KeyguardManager.KeyguardLock mKeyguardLock = null ;
	//��Ļ�����Ĺ㲥,����Ҫ����Ĭ�ϵ���������
	private BroadcastReceiver mScreenOnReceiver = new BroadcastReceiver(){

		@Override
		public void onReceive(Context context , Intent intent) {
			
            Log.i(TAG, intent.getAction());

			if(intent.getAction().equals("android.intent.action.SCREEN_ON")){
				Log.i(TAG, "----------------- android.intent.action.SCREEN_ON------");
//				mKeyguardManager = (KeyguardManager)context.getSystemService(Context.KEYGUARD_SERVICE);
//				mKeyguardLock = mKeyguardManager.newKeyguardLock("zdLock 1"); 
//				mKeyguardLock.disableKeyguard();
			}
		}
		
	};
	
	//
	private BroadcastReceiver mScreenOffReceiver = new BroadcastReceiver(){
		@Override
		public void onReceive(Context context , Intent intent) {
			String action = intent.getAction() ;
			
		    Log.i(TAG, intent.toString());
		    
			if(action.equals("android.intent.action.SCREEN_OFF")
					|| action.equals("android.intent.action.SCREEN_ON") ){
				mKeyguardManager = (KeyguardManager)context.getSystemService(Context.KEYGUARD_SERVICE);
				mKeyguardLock = mKeyguardManager.newKeyguardLock("zdLock 1"); 
			//	mKeyguardLock.disableKeyguard();
				startActivity(zdLockIntent);
			}
		}
		
	};
	
}
