package com.musketeer.baselibrary.tools.impl;

import com.musketeer.baselibrary.tools.LruCache;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by zhongxuqi on 15-11-23.
 */
public abstract class MemoryLruCache<K, V> extends LruCache<K, V> {
    private static final String TAG = "MemoryLruCache";

    protected final LinkedHashMap<K, V> map = new LinkedHashMap<>();

    public MemoryLruCache(long maxSize) {
        super(maxSize);
    }

    @Override
    public boolean put(K k, V v) {
        synchronized (map) {
            if (map.containsKey(k)) {
                map.remove(k);
            }
            map.put(k, v);
        }
        currSize += sizeof(v);
        clear();
        return true;
    }

    @Override
    public synchronized V get(K k) {
        if (count>10000) {
            count /= 2;
            hitCnt /= 2;
        }
        count++;
        if (map.containsKey(k)) {
            hitCnt++;
            V v = map.get(k);
            map.remove(k);
            map.put(k, v);
            return v;
        }
        return null;
    }

    @Override
    public void clearTo(long size) {
        synchronized (map) {
            LinkedList<K> dellist = new LinkedList<>();
            Iterator<Map.Entry<K, V>> iter = map.entrySet().iterator();
            while (currSize > size && iter.hasNext()) {
                Map.Entry<K, V> entry = iter.next();
                currSize -= sizeof(entry.getValue());
                dellist.add(entry.getKey());
            }
            for (K k : dellist) {
                map.remove(k);
            }
        }
    }
}
