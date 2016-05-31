/**   
* @Title: RequestTask.java 
* @Package com.musketeer.lib.entity 
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
* @date 2014-11-8 下午9:39:42 
* @version V1.0   
*/
package com.musketeer.baselibrary.bean;

import com.musketeer.baselibrary.net.UIUpdateTask;
import com.musketeer.baselibrary.paser.JsonPaser;

/**
 * @author zhongxuqi
 *
 */
public class RequestTask<T> extends BaseEntity {
	private String url;
	private ParamsEntity params;
	private boolean isBuildList;
	private RequestType mType;
	private JsonPaser mJsonPaser;
	private RequestResult<T> result;
	public int key;
	public UIUpdateTask task;
	
	public enum RequestType {
		GET,POST
	}

	/**
	 * @param type
	 * @param isBuildList
	 * @param jsonPaser
	 * @param task
	 */
	public RequestTask(RequestType type, boolean isBuildList, JsonPaser<T> jsonPaser, UIUpdateTask<T> task) {
		this.task=task;
		this.isBuildList=isBuildList;
		this.mJsonPaser=jsonPaser;
		this.mType=type;
		this.result=new RequestResult<>();
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url.endsWith("?") ? url : url + "?";
	}

	public ParamsEntity getParams() {
		return params;
	}

	public void setParams(ParamsEntity params) {
		this.params = params;
	}

	public boolean isBuildList() {
		return isBuildList;
	}

	public void setBuildList(boolean isBuildList) {
		this.isBuildList = isBuildList;
	}

	public RequestType getType() {
		return mType;
	}

	public void setType(RequestType mType) {
		this.mType = mType;
	}

	public JsonPaser getJsonPaser() {
		return mJsonPaser;
	}

	public void setJsonPaser(JsonPaser mJsonPaser) {
		this.mJsonPaser = mJsonPaser;
	}

	public RequestResult<T> getResult() {
		return result;
	}

	public void setResult(RequestResult<T> result) {
		this.result = result;
	}
}
