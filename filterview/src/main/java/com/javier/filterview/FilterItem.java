package com.javier.filterview;


import android.support.annotation.ColorRes;

/**
 * Created by usuario on 20/02/17.
 */

public class FilterItem {
    private String title;
    @ColorRes
    private int titleColor;
    private Object tag;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(@ColorRes int titleColor) {
        this.titleColor = titleColor;
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    public FilterItem(){}

    public FilterItem(String title) {
        this.title = title;
    }

    public FilterItem(Object tag) {
        this.tag = tag;
    }

    public FilterItem(int titleColor) {
        this.titleColor = titleColor;
    }

    public FilterItem(String title, Object tag) {
        this.title = title;
        this.tag = tag;
    }

    public FilterItem(String title, @ColorRes int titleColor, Object tag) {
        this.title = title;
        this.titleColor = titleColor;
        this.tag = tag;
    }

    public FilterItem(String title, @ColorRes int titleColor) {
        this.title = title;
        this.titleColor = titleColor;
    }
}
