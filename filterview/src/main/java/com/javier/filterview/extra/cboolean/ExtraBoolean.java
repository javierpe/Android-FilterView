package com.javier.filterview.extra.cboolean;

import android.support.annotation.ColorRes;

import com.javier.filterview.extra.ExtraOption;

/**
 * Created by usuario on 23/02/17.
 */

public class ExtraBoolean extends ExtraOption {
    private int colorAccent;
    private OnBooleanCheckedChangeListener listener;

    public OnBooleanCheckedChangeListener getOnBooleanCheckedChangeListener() {
        return listener;
    }

    public void setOnBooleanCheckedChangeListener(OnBooleanCheckedChangeListener listener) {
        this.listener = listener;
    }

    public int getColorAccent() {
        return colorAccent;
    }

    public void setColorAccent(@ColorRes int colorAccent) {
        this.colorAccent = colorAccent;
    }

    public ExtraBoolean(int id, String title, @ColorRes int titleColor, @ColorRes int colorAccent) {
        super(title, titleColor);
        this.colorAccent = colorAccent;
        this.setId(id);
    }
}
