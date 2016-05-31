package com.musketeer.baselibrary.service;

/**
 * Created by zhongxuqi on 15-11-24.
 */
public class ImageLoadOption {
    private int loadingImage;
    private int errorImage;

    public ImageLoadOption() {
        this(0, 0);
    }

    public ImageLoadOption(int loadingImage, int errorImage) {
        this.loadingImage=loadingImage;
        this.errorImage=errorImage;
    }

    public int getLoadingImage() {
        return loadingImage;
    }

    public void setLoadingImage(int loadingImage) {
        this.loadingImage = loadingImage;
    }

    public int getErrorImage() {
        return errorImage;
    }

    public void setErrorImage(int errorImage) {
        this.errorImage = errorImage;
    }
}
