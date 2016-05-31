package com.musketeer.baselibrary.net;

import com.musketeer.baselibrary.bean.RequestResult;

/**
 * Created by zhongxuqi on 15-10-25.
 */
public interface UIUpdateTask<T> {
    void doUIUpdate(RequestResult<T> result);
}
