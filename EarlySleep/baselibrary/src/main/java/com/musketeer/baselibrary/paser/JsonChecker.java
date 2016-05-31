package com.musketeer.baselibrary.paser;

import com.musketeer.baselibrary.exception.NetErrorException;

import org.json.JSONObject;

/**
 * Created by zhongxuqi on 15-10-25.
 */
public abstract class JsonChecker {
    public abstract void checkJson(JSONObject jo) throws NetErrorException;
}
