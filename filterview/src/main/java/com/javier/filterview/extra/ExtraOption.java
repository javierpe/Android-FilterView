package com.javier.filterview.extra;

import android.support.annotation.ColorRes;

import com.javier.filterview.FilterItem;

/**
 * Created by usuario on 27/02/17.
 */

public class ExtraOption extends FilterItem{

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ExtraOption(int id) {
        this.id = id;
    }

    public ExtraOption(String title) {
        super(title);
    }

    public ExtraOption(String title, Object tag) {
        super(title, tag);
    }

    public ExtraOption(String title, @ColorRes int titleColor, Object tag) {
        super(title, titleColor, tag);
    }

    public ExtraOption(String title, @ColorRes int titleColor) {
        super(title, titleColor);
    }
}
