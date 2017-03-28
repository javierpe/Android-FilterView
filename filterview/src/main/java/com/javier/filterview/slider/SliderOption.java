package com.javier.filterview.slider;

import android.support.annotation.ColorRes;

import com.javier.filterview.extra.ExtraOption;

/**
 * Created by usuario on 20/02/17.
 */

public class SliderOption extends ExtraOption {
    private int maxValue;
    private int minValue;
    private int barColor;
    private int barHighlightColor;
    private int leftThumbColor;
    private float fixGap;

    public float getFixGap() {
        return fixGap;
    }

    public void setFixGap(float fixGap) {
        this.fixGap = fixGap;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getBarColor() {
        return barColor;
    }

    public void setBarColor(@ColorRes int colorAccent) {
        this.barColor = colorAccent;
    }

    public int getBarHighlightColor() {
        return barHighlightColor;
    }

    public void setBarHighlightColor(@ColorRes int barHighlightColor) {
        this.barHighlightColor = barHighlightColor;
    }

    public int getLeftThumbColor() {
        return leftThumbColor;
    }

    public void setLeftThumbColor(@ColorRes int leftThumbColor) {
        this.leftThumbColor = leftThumbColor;
    }


    public SliderOption(int id, @ColorRes int titleColor, int minValue, int maxValue, @ColorRes int barColor, @ColorRes int barHighlightColor, @ColorRes int leftThumbColor) {
        super(titleColor);
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.barColor = barColor;
        this.barHighlightColor = barHighlightColor;
        this.leftThumbColor = leftThumbColor;
        this.setId(id);
    }

    public SliderOption(int id, @ColorRes int titleColor, int minValue, int maxValue, int barColor, @ColorRes int barHighlightColor, @ColorRes int leftThumbColor, float fixGap) {
        super(titleColor);
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.barColor = barColor;
        this.barHighlightColor = barHighlightColor;
        this.leftThumbColor = leftThumbColor;
        this.fixGap = fixGap;
        this.setId(id);
    }
}
