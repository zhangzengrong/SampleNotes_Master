package com.example.www44.memorandum;

public class Tab {
    //标题资源
    private int title;
    //图片字图案
    private int img;
    //Fragment类资源
    private Class Fragment;

    public Tab(int title, int img, Class fragment) {
        this.title = title;
        this.img = img;
        Fragment = fragment;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public Class getFragment() {
        return Fragment;
    }

    public void setFragment(Class fragment) {
        Fragment = fragment;
    }
}