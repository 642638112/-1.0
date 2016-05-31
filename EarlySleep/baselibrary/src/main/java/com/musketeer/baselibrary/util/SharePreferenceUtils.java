/**   
 * @Title: SharePreferenceUtils.java 
 * @Package com.musketeer.lib.util 
 *
 * @author musketeer zhongxuqi@163.com  
 * @date 2014-11-12 下午1:51:30 
 * @version V1.0   
 */
package com.musketeer.baselibrary.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author zhongxuqi
 * 
 */
public class SharePreferenceUtils {
	public static final String PREFERENCE_NAME = "Musketeer";

	public static boolean getBoolean(Context context, String key,
			boolean defaultvalue) {
		SharedPreferences mSharedPreferences = context.getSharedPreferences(
				PREFERENCE_NAME, Context.MODE_PRIVATE);
		return mSharedPreferences.getBoolean(key, defaultvalue);
	}

	public static boolean putBoolean(Context context, String key, boolean value) {
		SharedPreferences mSharedPreferences = context.getSharedPreferences(
				PREFERENCE_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = mSharedPreferences.edit();
		editor.putBoolean(key, value);
		return editor.commit();
	}

	public static String getString(Context context, String key,
			String defaultvalue) {
		SharedPreferences mSharedPreferences = context.getSharedPreferences(
				PREFERENCE_NAME, Context.MODE_PRIVATE);
		return mSharedPreferences.getString(key, defaultvalue);
	}

	public static boolean putString(Context context, String key, String value) {
		SharedPreferences mSharedPreferences = context.getSharedPreferences(
				PREFERENCE_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = mSharedPreferences.edit();
		editor.putString(key, value);
		return editor.commit();
	}

	public static int getInt(Context context, String key, int defaultvalue) {
		SharedPreferences mSharedPreferences = context.getSharedPreferences(
				PREFERENCE_NAME, Context.MODE_PRIVATE);
		return mSharedPreferences.getInt(key, defaultvalue);
	}

	public static boolean putInt(Context context, String key, int value) {
		SharedPreferences mSharedPreferences = context.getSharedPreferences(
				PREFERENCE_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = mSharedPreferences.edit();
		editor.putInt(key, value);
		return editor.commit();
	}

	public static long getLong(Context context, String key, long defaultvalue) {
		SharedPreferences mSharedPreferences = context.getSharedPreferences(
				PREFERENCE_NAME, Context.MODE_PRIVATE);
		return mSharedPreferences.getLong(key, defaultvalue);
	}

	public static boolean putLong(Context context, String key, long value) {
		SharedPreferences mSharedPreferences = context.getSharedPreferences(
				PREFERENCE_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = mSharedPreferences.edit();
		editor.putLong(key, value);
		return editor.commit();
	}

	public static float getFloat(Context context, String key, float defaultvalue) {
		SharedPreferences mSharedPreferences = context.getSharedPreferences(
				PREFERENCE_NAME, Context.MODE_PRIVATE);
		return mSharedPreferences.getFloat(key, defaultvalue);
	}

	public static boolean putFloat(Context context, String key, float value) {
		SharedPreferences mSharedPreferences = context.getSharedPreferences(
				PREFERENCE_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = mSharedPreferences.edit();
		editor.putFloat(key, value);
		return editor.commit();
	}

}
