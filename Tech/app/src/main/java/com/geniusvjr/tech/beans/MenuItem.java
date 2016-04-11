package com.geniusvjr.tech.beans;

/**
 * Created by dream on 16/4/6.
 */
public class MenuItem {

    public int iconResId;
    public String text;

    public MenuItem() {
    }

    public MenuItem(String text, int iconResId) {
        this.iconResId = iconResId;
        this.text = text;
    }
}
