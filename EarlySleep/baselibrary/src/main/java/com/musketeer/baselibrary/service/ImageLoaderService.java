package com.musketeer.baselibrary.service;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.musketeer.baselibrary.R;
import com.musketeer.baselibrary.tools.LruCache;
import com.musketeer.baselibrary.tools.impl.MemAndFSLruCache;
import com.musketeer.baselibrary.util.LogUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhongxuqi on 15-11-22.
 */
public class ImageLoaderService extends Service {
    private static final String TAG = "ImageLoaderService";
    public static final int THREAD_NUM = 4;

    private static ImageLoaderService instance;

    protected final List<ImageRequest> requestList = new LinkedList<>();
    protected ImageLoadOption defaultImageLoadOption = null;

    //request executor
    protected boolean isRun=false;

    private LruCache<String, Bitmap> cache;

    public static ImageLoaderService getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        File dir = new File(Environment.getExternalStorageDirectory() + File.separator
                + getString(R.string.app_name) + File.separator + "imagecache");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        cache = new MemAndFSLruCache<String, Bitmap>(Runtime.getRuntime().maxMemory()/4, dir.getAbsolutePath()) {
            @Override
            protected Bitmap readValue(FileInputStream in) throws IOException {
                return BitmapFactory.decodeStream(in);
            }

            @Override
            protected boolean writeValue(Bitmap bitmap, FileOutputStream out) {
                return bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            }

            @Override
            public long sizeof(Bitmap bitmap) {
                return bitmap.getByteCount();
            }
        };

        instance = this;

        isRun=true;
        for (int i = 0; i < THREAD_NUM; i++) {
            new ImageLoadThread().start();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        isRun=false;
    }

    public void loadImage(@NonNull ImageView imageView, @NonNull String imageUrl) {
        loadImage(imageView, imageUrl, defaultImageLoadOption);
    }

    public void loadImage(@NonNull ImageView imageView, @NonNull String imageUrl, ImageLoadOption imageLoadOption) {
        synchronized (requestList) {
            ImageRequest imageRequest = new ImageRequest(imageView, imageUrl, imageLoadOption);
            requestList.add(imageRequest);
            setDefaultImage(imageRequest);
            requestList.notifyAll();
        }
    }

    protected void setDefaultImage(ImageRequest imageRequest) {
        if (imageRequest.imageLoadOption != null && imageRequest.imageLoadOption.getLoadingImage() != 0)
            imageRequest.imageView.setImageResource(imageRequest.imageLoadOption.getLoadingImage());
    }

    protected void setErrorImage(ImageRequest imageRequest) {
        if (imageRequest.imageLoadOption != null && imageRequest.imageLoadOption.getErrorImage() != 0)
            imageRequest.imageView.setImageResource(imageRequest.imageLoadOption.getErrorImage());
    }

    public void setDefaultImageLoadOption(ImageLoadOption defaultImageLoadOption) {
        this.defaultImageLoadOption = defaultImageLoadOption;
    }

    private Handler handler = new Handler();

    private class ImageLoadRunnable implements Runnable {
        private final ImageRequest imageRequest;

        public ImageLoadRunnable(@NonNull ImageRequest imageRequest) {
            this.imageRequest = imageRequest;
        }

        @Override
        public void run() {
            if (imageRequest.bitmap != null) {
                imageRequest.imageView.setImageBitmap(imageRequest.bitmap);
                imageRequest.imageView.invalidate();
            } else {
                setErrorImage(imageRequest);
            }
        }
    }

    private class ImageLoadThread extends Thread {
        @Override
        public void run() {
            while (isRun) {
                ImageRequest imageRequest = null;
                synchronized (requestList) {
                    if (requestList.size()>0) {
                        imageRequest = requestList.get(0);
                        requestList.remove(0);
                    }
                }
                if (imageRequest != null) {
                    doTask(imageRequest);
                } else {
                    try {
                        synchronized (requestList) {
                            requestList.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        void doTask(ImageRequest imageRequest) {
            imageRequest.bitmap = cache.get(imageRequest.imageUrl);
            if (imageRequest.bitmap == null) {
                LogUtils.d(TAG, "Load Image: " + imageRequest.imageUrl);
                try {
                    URL url =  new URL(imageRequest.imageUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    imageRequest.bitmap = BitmapFactory.decodeStream(conn.getInputStream());
                    conn.getInputStream().close();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (imageRequest.bitmap != null) {
                    cache.put(imageRequest.imageUrl, imageRequest.bitmap);
                }
            }
            handler.post(new ImageLoadRunnable(imageRequest));
        }
    }

    class ImageRequest {
        public ImageView imageView;
        public String imageUrl;
        public Bitmap bitmap;
        public ImageLoadOption imageLoadOption;

        public ImageRequest(ImageView imageView, String imageUrl, ImageLoadOption imageLoadOption) {
            this.imageView=imageView;
            this.imageUrl=imageUrl;
            this.imageLoadOption=imageLoadOption;
        }
    }
}
