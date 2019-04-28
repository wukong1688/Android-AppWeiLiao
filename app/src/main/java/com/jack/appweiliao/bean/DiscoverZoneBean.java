package com.jack.appweiliao.bean;

import java.util.ArrayList;
import java.util.List;

public class DiscoverZoneBean {
    public static final int VIEW_TYPE_SINGLE = 0;
    public static final int VIEW_TYPE_MULTI = 1;

    public String text;
    public List<String> images = new ArrayList<>();

    public DiscoverZoneBean() {
    }

    public DiscoverZoneBean(String text, List<String> images) {
        this.text = text;
        this.images = images;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public int getViewType() {
        if (images.size() == 1) {
            return VIEW_TYPE_SINGLE;
        } else {
            return VIEW_TYPE_MULTI;
        }
    }
}