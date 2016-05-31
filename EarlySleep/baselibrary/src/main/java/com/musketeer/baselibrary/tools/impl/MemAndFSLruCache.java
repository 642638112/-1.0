package com.musketeer.baselibrary.tools.impl;

import android.os.Environment;

import com.musketeer.baselibrary.tools.LruCache;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by zhongxuqi on 15-11-23.
 */
public abstract class MemAndFSLruCache<K, V> extends LruCache<K, V> {
    private static final String TAG = "MemAndFSLruCache";

    protected final LinkedHashMap<K, V> memMap = new LinkedHashMap<>();
    protected final LinkedHashMap<K, String> fileMap = new LinkedHashMap<>();

    protected final File dir;
    protected long lastFileClearTime;

    public MemAndFSLruCache(long maxSize, String cachePath) {
        super(maxSize);
        lastFileClearTime = 0;
        File f = new File(cachePath);
        if (f.exists()) {
            if (!f.isDirectory()) {
                dir = new File(Environment.getExternalStorageDirectory() + File.separator + "imagecache");
                dir.mkdirs();
            } else {
                dir = f;
            }
        } else {
            dir = f;
            dir.mkdirs();
        }
    }

    @Override
    public synchronized boolean put(K k, V v) {
        if (memMap.containsKey(k)) {
            memMap.remove(k);
            memMap.put(k, v);
        }
        memMap.put(k, v);
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
        if (memMap.containsKey(k)) {
            hitCnt++;
            V v = memMap.get(k);
            memMap.remove(k);
            memMap.put(k, v);
            return v;
        } else if (fileMap.containsKey(k)) {
            V v = null;
            File file = new File(fileMap.get(k));
            try {
                v = readValue(file);
                memMap.put(k, v);
                currSize += sizeof(v);
            } catch (IOException e) {
                fileMap.remove(k);
                e.printStackTrace();
            }
            clear();
            return v;
        }
        return null;
    }

    @Override
    public synchronized void clearTo(long size) {
        LinkedList<Map.Entry<K, V>> dellist = new LinkedList<>();
        Iterator<Map.Entry<K, V>> iter = memMap.entrySet().iterator();
        while (currSize > size && iter.hasNext()) {
            Map.Entry<K, V> entry = iter.next();
            currSize -= sizeof(entry.getValue());
            dellist.add(entry);
        }
        for (Map.Entry<K, V> entry : dellist) {
            if (!fileMap.containsKey(entry.getKey())) {
                String path = dir.getAbsolutePath() + File.separator + getFormatDate(System.currentTimeMillis());
                try {
                    writeValue(entry.getValue(), new File(path));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                fileMap.put(entry.getKey(), path);
            }
            memMap.remove(entry.getKey());
        }
    }

    @Override
    public void clear() {
        super.clear();
        if (System.currentTimeMillis() - lastFileClearTime < 5 * 60 * 1000) return;
        new FileClearTask().start();
    }

    public void clearFileBefore(String date) {
        synchronized (fileMap) {
            lastFileClearTime = System.currentTimeMillis();
            File[] files = dir.listFiles();
            if (files == null) return;
            for (File file : files) {
                if (file.getName().compareTo(date) > 0)  continue;
                file.delete();
            }
        }
    }

    protected synchronized V readValue(File file) throws IOException {
        return readValue(new FileInputStream(file));
    }

    protected synchronized boolean writeValue(V v, File file) throws IOException{
        if (!file.exists()) file.createNewFile();
        return writeValue(v, new FileOutputStream(file));
    }

    protected abstract V readValue(FileInputStream in) throws IOException;

    protected abstract boolean writeValue(V v, FileOutputStream out);

    protected String getFormatDate(long time) {
        return getFormatDate(new Date(time));
    }

    protected String getFormatDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SSS");
        return format.format(date);
    }

    protected class FileClearTask extends Thread {
        @Override
        public void run() {
            super.run();
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.HOUR, -1);
            clearFileBefore(getFormatDate(calendar.getTime()));
        }
    }
}
