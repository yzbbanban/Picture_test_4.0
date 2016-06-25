package com.wangban.yzbbanban.picture_test_40.entity;

/**
 * Created by YZBbanban on 16/6/5.
 */
public class DetialImage {
    private String detPath;
    private String detWidth;
    private String detHeight;

    public DetialImage() {
    }

    public DetialImage(String detPath, String detWidth, String detHeight) {
        this.detPath = detPath;
        this.detWidth = detWidth;
        this.detHeight = detHeight;
    }

    public String getDetPath() {
        return detPath;
    }

    public void setDetPath(String detPath) {
        this.detPath = detPath;
    }

    public String getDetWidth() {
        return detWidth;
    }

    public void setDetWidth(String detWidth) {
        this.detWidth = detWidth;
    }

    public String getDetHeight() {
        return detHeight;
    }

    public void setDetHeight(String detHeight) {
        this.detHeight = detHeight;
    }

    @Override
    public String toString() {
        return  detPath;
    }
}
