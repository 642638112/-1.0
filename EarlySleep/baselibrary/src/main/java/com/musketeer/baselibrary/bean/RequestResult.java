/**   
* @Title: RequestResult.java 
* @Package com.musketeer.lib.entity 
*
* @author musketeer zhongxuqi@163.com  
* @date 2014-11-9 上午11:16:50 
* @version V1.0   
*/
package com.musketeer.baselibrary.bean;

import com.musketeer.baselibrary.net.UIUpdateTask;

import java.util.List;

/**
 * @author zhongxuqi
 *
 */
public class RequestResult<T> extends BaseEntity {
	public int key;
	public boolean isOk;
	public boolean isBuildList;
	public T resultObj;
	public List<T> resultList;
	public UIUpdateTask<T> task;
}
