/**   
* @Title: ParamsEntity.java 
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
* @date 2014-11-8 下午9:47:24 
* @version V1.0   
*/
package com.musketeer.baselibrary.bean;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author zhongxuqi
 */
public class ParamsEntity {
	private Map<String,String> map;
	private Map<String,File> mapFile;
	
	public ParamsEntity() {
		map=new HashMap<String,String>();
		mapFile=new HashMap<String,File>();
	}
	
	public void put(String key,String value) {
		map.put(key, value);
	}
	
	public void put(String key,File file) {
		mapFile.put(key, file);
	}
	
	public String toRequestString() {
		String result="";
		Iterator<String> iter=map.keySet().iterator();
		while (iter.hasNext()) {
			String key=iter.next();
			String value=map.get(key);
			result=result+key+"="+value;
			if (iter.hasNext()) {
				result=result+"&";
			}
		}		return result;
	}
	
	public String toString() {
		return toRequestString();
	}
	/*
	public HttpEntity getHttpEntity() throws UnsupportedEncodingException {
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();        
	    builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

	    String key,value;
	    
	    //add texts
	    if (!map.isEmpty()) {
		    Iterator<String> iter=map.keySet().iterator();
		    while (iter.hasNext()) {
		    	key=iter.next();
		    	value=map.get(key);
		    	builder.addTextBody(key, URLEncoder.encode(value, "UTF-8"));
		    }
	    }
	    
	    //add files
	    if (!mapFile.isEmpty()) {
		    Iterator<String> iterFile=mapFile.keySet().iterator();
		    File file;
		    while (iterFile.hasNext()) {
		    	key=iterFile.next();
		    	file=mapFile.get(key);
		    	builder.addPart(key, new FileBody(file));
		    }
	    }
	    
	    return builder.build();
	}*/

	public Map<String, String> getMap() {
		return map;
	}

	public Map<String, File> getMapFile() {
		return mapFile;
	}
}
