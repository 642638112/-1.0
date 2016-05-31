package com.musketeer.baselibrary.paser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by zhongxuqi on 15-10-24.
 */
public interface JsonPaser<T> {
    /**
     * 创建模型
     * @param jsonObject
     * @return
     */
    public abstract T BuildModel(JSONObject jsonObject) throws JSONException;

    /**
     * 创建模型列表
     * @param jsonObject
     * @return
     */
    public abstract List<T> BuildModelList(JSONObject jsonObject) throws JSONException;
}
