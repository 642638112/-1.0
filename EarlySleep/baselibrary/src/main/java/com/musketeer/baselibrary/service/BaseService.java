/**
 * @Title: BaseService.java
 * @Package com.musketeer.lib.server
 * Copyright (C) 2014 Plusub Tech. Co. Ltd. All Rights Reserved <admin@plusub.com>
 *
 * Licensed under the Plusub License, Version 1.0 (the "License");
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author musketeer zhongxuqi@163.com
 * @date 2014-11-8 下午9:35:19
 * @version V1.0
 */
package com.musketeer.baselibrary.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.NonNull;

import com.musketeer.baselibrary.bean.RequestResult;
import com.musketeer.baselibrary.bean.RequestTask;
import com.musketeer.baselibrary.exception.NetErrorException;
import com.musketeer.baselibrary.net.DefaultHttpClient;
import com.musketeer.baselibrary.net.HttpClient;
import com.musketeer.baselibrary.paser.DefaultJsonChecker;
import com.musketeer.baselibrary.paser.JsonChecker;
import com.musketeer.baselibrary.util.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zhongxuqi
 *
 */
public class BaseService extends Service {
	public static final String TAG = "Musketeer_BaseService";
	public static final int THREAD_NUM = 4;

	//HTTP request queue
	protected final List<RequestTask> requestList = new LinkedList<RequestTask>();

	//request executor
	protected boolean isRun=false;

	//json check
	protected JsonChecker mJsonChecker;

	protected HttpClient mClient;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();

		mJsonChecker = new DefaultJsonChecker();
		mClient = new DefaultHttpClient();

		isRun=true;
		for (int i = 0; i < THREAD_NUM; i++) {
			new RequestTaskThread().start();
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		isRun=false;
	}

	public void addTask(RequestTask task) {
		if (task.getJsonPaser() == null) {
			throw new NullPointerException("Json Paser is Null.");
		}
		synchronized (requestList) {
			requestList.add(task);
			requestList.notifyAll();
		}
	}

	private Handler handler = new Handler();

	private class RequestTaskRunnable implements Runnable {
		private final RequestResult requestResult;

		public RequestTaskRunnable(@NonNull RequestResult requestResult) {
			this.requestResult = requestResult;
		}

		@Override
		public void run() {
			requestResult.task.doUIUpdate(requestResult);
		}
	}

	private class RequestTaskThread extends Thread {
		@Override
		public void run() {
			super.run();
			while (isRun) {
				RequestTask requestTask = null;
				synchronized (requestList) {
					if (requestList.size()>0) {
						requestTask = requestList.get(0);
						requestList.remove(0);
					}
				}
				if (requestTask != null) {
					doTask(requestTask);
				} else {
					try {
						synchronized (requestList) {
							requestList.wait();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}

		private void doTask(RequestTask requestTask) {
			// TODO Auto-generated method stub
			LogUtils.d(TAG, "Do Request: " + requestTask.getUrl() + requestTask.getParams().toString());
			RequestResult result=requestTask.getResult();
			result.setId(requestTask.getId());
			result.key=requestTask.key;
			result.task=requestTask.task;
			result.isBuildList=requestTask.isBuildList();
			String jsonStr;
			JSONObject JsonObject;
			try {
				switch (requestTask.getType()) {
					case GET:
						jsonStr = mClient.doGet(requestTask.getUrl(), requestTask.getParams());
						LogUtils.d(TAG, requestTask.getUrl() + "[GET]: " + jsonStr);
						JsonObject=new JSONObject(jsonStr);
						mJsonChecker.checkJson(JsonObject);
						if (!requestTask.isBuildList()) {
							result.resultObj=requestTask.getJsonPaser().BuildModel(JsonObject);
						} else {
							result.resultList=requestTask.getJsonPaser().BuildModelList(JsonObject);
						}
						break;
					case POST:
						jsonStr = mClient.doPost(requestTask.getUrl(), requestTask.getParams());
						LogUtils.d(TAG, requestTask.getUrl()+"[POST]: "+jsonStr);
						JsonObject=new JSONObject(jsonStr);
						mJsonChecker.checkJson(JsonObject);
						if (!requestTask.isBuildList()) {
							result.resultObj=requestTask.getJsonPaser().BuildModel(JsonObject);
						} else {
							result.resultList=requestTask.getJsonPaser().BuildModelList(JsonObject);
						}
						break;
				}
				result.isOk=true;//request is done
			} catch (NetErrorException netExcetion) {
				LogUtils.d(TAG, netExcetion.toString());
				netExcetion.printStackTrace();
				result.isOk=false;//request is fail
			} catch (JSONException jsonExcetion) {
				jsonExcetion.printStackTrace();
				result.isOk=false;//request is fail
			} catch (Exception e) {
				e.printStackTrace();
				result.isOk=false;//request is fail
			}
			handler.post(new RequestTaskRunnable(result));
		}
	}

}
