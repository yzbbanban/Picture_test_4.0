package com.wangban.yzbbanban.picture_test_40.entity;

/**
 * Created by YZBbanban on 16/6/5.
 */
public class Image {
    private String path;
    private int width;
    private int height;
    private String SkipPagePath;
    private String localPath;


    public Image(String path, int width, int height, String skipPagePath, String localPath) {
        this.path = path;
        this.width = width;
        this.height = height;
        SkipPagePath = skipPagePath;
        this.localPath = localPath;
    }


    public Image() {
    }

    public void setSetSkipPagePath(String setSkipPagePath) {
        this.SkipPagePath = setSkipPagePath;
    }

    public String getSkipPagePath() {
        return SkipPagePath;
    }

    public void setSkipPagePath(String skipPagePath) {
        SkipPagePath = skipPagePath;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return  path;
    }
}
