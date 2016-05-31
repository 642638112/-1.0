/**   
* @Title: TimeUtils.java 
* @Package com.musketeer.lib.util 
*
* @author musketeer zhongxuqi@163.com  
* @date 2014-11-10 下午7:46:30 
* @version V1.0   
*/
package com.musketeer.baselibrary.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhongxuqi
 *
 */
public class TimeUtils {
	
	/**
	 * 格式为yyyy年MM月dd日  HH:mm:ss
	 * <p>Title: getDateCN
	 * <p>Description: 
	 * @return
	 */
	public static String getDateCN() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
		String date = format.format(new Date(System.currentTimeMillis()));
		return date;
	}
	
	/**
	 * 格式为yyyy年MM月dd日  HH:mm
	 * <p>Title: getDateCN
	 * <p>Description: 
	 * @return
	 */
	public static String getDateCNNotSecond() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日  HH:mm");
		String date = format.format(new Date(System.currentTimeMillis()));
		return date;
	}

	/**
	 * 格式为yyyy-MM-dd
	 * <p>Title: getDateEN
	 * <p>Description: 
	 * @return
	 */
	public static String getDateNotMinByCurrentTime() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String date1 = format1.format(new Date(System.currentTimeMillis()));
		return date1;
	}
	
	/**
	 * 格式为yyyy-MM-dd
	 * <p>Title: getDateEN
	 * <p>Description: 
	 * @return
	 */
	public static String getDateNotMin(long timeMills) {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String date1 = format1.format(new Date(timeMills));
		return date1;
	}
	
	/**
	 * 格式为MM-dd
	 * <p>Title: getDateEN
	 * <p>Description: 
	 * @return
	 */
	public static String getDateNotYear(long timeMills) {
		SimpleDateFormat format1 = new SimpleDateFormat("MM-dd");
		String date1 = format1.format(new Date(timeMills));
		return date1;
	}
	
	/**
	 * 格式为yyyy-MM-dd HH:mm:ss
	 * <p>Title: getDateEN
	 * <p>Description: 
	 * @return
	 */
	public static String getDateEN() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1 = format1.format(new Date(System.currentTimeMillis()));
		return date1;
	}
	
	/**
	 * 格式为yyyy-MM-dd HH:mm
	 * <p>Title: getDateEN
	 * <p>Description: 
	 * @return
	 */
	public static String getDateENNotSecond() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String date1 = format1.format(new Date(System.currentTimeMillis()));
		return date1;
	}
	
	/**
	 * 格式为yyyy-MM-dd HH:mm
	 * <p>Title: getDateEN
	 * <p>Description: 
	 * @return
	 */
	public static String getDateENNotSecond(long timeMills) {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String date1 = format1.format(new Date(timeMills));
		return date1;
	}
	
	/**
	 * 格式为yyyy-MM-dd HH:mm:ss
	 * <p>Title: getDateEN
	 * <p>Description: 
	 * @param timeMills
	 * @return
	 */
	public static String getDateEN(long timeMills){
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1 = format1.format(new Date(timeMills));
		return date1;
	}

	/**
	 * 格式为HH:mm
	 * <p>Title: getDate
	 * <p>Description: 
	 * @return
	 */
	public static String getDateByHourAndMin() {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		String date = format.format(new Date(System.currentTimeMillis()));
		return date;
	}
	
	/**
	 * 格式为yyyy_MM_dd_HH_mm_ss
	 * <p>Title: getDate
	 * <p>Description: 
	 * @return
	 */
	public static String getDateByFileName(long timeMills) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd HH_mm_ss");
		String date = format.format(new Date(timeMills));
		return date;
	}

}
