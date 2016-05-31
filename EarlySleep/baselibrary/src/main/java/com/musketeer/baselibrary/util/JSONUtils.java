/**   
* @Title: JSONUtils.java 
* @Package com.musketeer.lib.util 
*
* @author musketeer zhongxuqi@163.com  
* @date 2014-11-10 下午1:00:38 
* @version V1.0   
*/
package com.musketeer.baselibrary.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author zhongxuqi
 *
 */
public class JSONUtils {
	
	/**
	 * 取int内容，并处理异常
	 * @param jsonObject
	 * @param key
	 * @param defaultvalue
	 * @return value
	 */
	public static int getInt(JSONObject jsonObject, String key, int defaultvalue) {
		if (jsonObject==null||StringUtils.isEmpty(key)) {
			return defaultvalue;
		}
		int value;
		try {
			value=jsonObject.getInt(key);
			return value;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return defaultvalue;
		}
	}
	
	/**
	 * 取long内容，并处理异常
	 * @param jsonObject
	 * @param key
	 * @param defaultvalue
	 * @return
	 */
	public static long getLong(JSONObject jsonObject, String key, long defaultvalue) {
		if (jsonObject==null||StringUtils.isEmpty(key)) {
			return defaultvalue;
		}
		long value;
		try {
			value=jsonObject.getLong(key);
			return value;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return defaultvalue;
		}
	}
	
	/**
	 * 取boolean内容，并处理异常
	 * @param jsonObject
	 * @param key
	 * @param defaultvalue
	 * @return
	 */
	public static boolean getBoolean(JSONObject jsonObject, String key, boolean defaultvalue) {
		if (jsonObject==null||StringUtils.isEmpty(key)) {
			return defaultvalue;
		}
		boolean value;
		try {
			value=jsonObject.getBoolean(key);
			return value;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return defaultvalue;
		}
	}
	
	/**
	 * 取double内容，并处理异常
	 * @param jsonObject
	 * @param key
	 * @param defaultvalue
	 * @return
	 */
	public static double getDouble(JSONObject jsonObject, String key, double defaultvalue) {
		if (jsonObject==null||StringUtils.isEmpty(key)) {
			return defaultvalue;
		}
		double value;
		try {
			value=jsonObject.getDouble(key);
			return value;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return defaultvalue;
		}
	}
	
	/**
	 * 取String内容，并处理异常
	 * @param jsonObject
	 * @param key
	 * @param defaultvalue
	 * @return
	 */
	public static String getString(JSONObject jsonObject, String key, String defaultvalue) {
		if (jsonObject==null||StringUtils.isEmpty(key)) {
			return defaultvalue;
		}
		String value;
		try {
			value=jsonObject.getString(key);
			return value;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return defaultvalue;
		}
	}
	
	/**
	 * 取JSONObject内容，并处理异常
	 */
	public static JSONObject getJSONObject(JSONObject jsonObject, String key) {
		if (jsonObject==null||StringUtils.isEmpty(key)) {
			return null;
		}
		JSONObject value;
		try {
			value=jsonObject.getJSONObject(key);
			return value;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 取JSONArray内容，并处理异常
	 */
	public static JSONArray getJSONArray(JSONObject jsonObject, String key) {
		if (jsonObject==null||StringUtils.isEmpty(key)) {
			return null;
		}
		JSONArray value;
		try {
			value=jsonObject.getJSONArray(key);
			return value;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
}
