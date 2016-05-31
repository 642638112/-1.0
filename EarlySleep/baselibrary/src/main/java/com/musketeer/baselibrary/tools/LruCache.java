package com.musketeer.baselibrary.tools;

/**
 * Created by zhongxuqi on 15-11-23.
 */
public abstract class LruCache<K,V> {
    private static final String TAG = "LruCache";

    protected static final float clearRate=0.8f;
    protected long currSize;
    protected long maxSize;

    protected long count;
    protected long hitCnt;

    public LruCache(long maxSize) {
        this.maxSize=maxSize;
        currSize=0;
        count=0;
        hitCnt=0;
    }

    public long getCurrSize() {
        return currSize;
    }

    public float getHitRate() {
        if (count==0) return 0;
        return 1.0f*hitCnt/count;
    }

    public abstract boolean put(K k, V v);

    public abstract V get(K k);

    public void clear() {
        new DoClearTask().start();
    }

    public abstract void clearTo(long size);

    public abstract long sizeof(V v);

    protected class DoClearTask extends Thread {
        @Override
        public void run() {
            super.run();
            if (currSize>maxSize) {
                clearTo((long)(clearRate * maxSize));
            }
        }
    }

}
