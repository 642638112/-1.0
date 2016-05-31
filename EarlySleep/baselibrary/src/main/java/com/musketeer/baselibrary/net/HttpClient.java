package com.musketeer.baselibrary.net;

import com.musketeer.baselibrary.bean.ParamsEntity;

/**
 * Created by zhongxuqi on 15-10-25.
 */
public abstract class HttpClient {
    public String sessionId = "";

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * 执行Get请求
     * @param path
     * @param params
     * @return
     * @throws Exception
     */
    public abstract String doGet(String path, ParamsEntity params) throws Exception;

    /**
     * 执行Post请求
     * @param path
     * @param params
     * @return
     * @throws Exception
     */
    public abstract String doPost(String path,ParamsEntity params) throws Exception;
}
