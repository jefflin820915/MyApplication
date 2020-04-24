package com.example.myapplicationimagepicker.domain;

public class ImageItem {

    private String path;
    private String title;
    private long data;

    public ImageItem(String path, String title, long data) {
        this.path = path;
        this.title = title;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ImageItem{" +
                "path='" + path + '\'' +
                ", title='" + title + '\'' +
                ", data=" + data +
                '}';
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }
}
