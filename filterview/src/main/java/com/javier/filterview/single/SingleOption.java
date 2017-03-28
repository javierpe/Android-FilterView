package com.javier.filterview.single;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;

import com.javier.filterview.FilterItem;

/**
 * Created by usuario on 20/02/17.
 */

public class SingleOption extends FilterItem {
    private int icon;
    private int borderWidth;
    private int borderColor;
    private int defaultColor;
    private int focusColor;
    private int selectedIconColor;
    private int deselectedIconColor;
    protected boolean enabled;

    public int getSelectedIconColor() {
        return selectedIconColor;
    }

    public void setSelectedIconColor(@ColorRes int selectedIconColor) {
        this.selectedIconColor = selectedIconColor;
    }

    public int getDeselectedIconColor() {
        return deselectedIconColor;
    }

    public void setDeselectedIconColor(@ColorRes int deselectedIconColor) {
        this.deselectedIconColor = deselectedIconColor;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(@DrawableRes int icon) {
        this.icon = icon;
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }

    public int getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(@ColorRes int borderColor) {
        this.borderColor = borderColor;
    }

    public int getDefaultColor() {
        return defaultColor;
    }

    public void setDefaultColor(@ColorRes int defaultColor) {
        this.defaultColor = defaultColor;
    }

    public int getFocusColor() {
        return focusColor;
    }

    public void setFocusColor(@ColorRes int focusColor) {
        this.focusColor = focusColor;
    }

    public SingleOption(){}

    public SingleOption(String title, int icon) {
        super(title);
        this.icon = icon;
    }

    public SingleOption(String title) {
        super(title);
    }

    public SingleOption(String title, @ColorRes int titleColor, @DrawableRes int icon, int borderWidth, @ColorRes int borderColor) {
        super(title, titleColor);
        this.icon = icon;
        this.borderWidth = borderWidth;
        this.borderColor = borderColor;
    }

    public SingleOption(String title, @ColorRes int titleColor, @DrawableRes int icon, @ColorRes int selectedIconColor,
                        @ColorRes int deselectedIconColor, int borderWidth, @ColorRes int borderColor) {
        super(title, titleColor);
        this.icon = icon;
        this.borderWidth = borderWidth;
        this.borderColor = borderColor;
        this.selectedIconColor = selectedIconColor;
        this.deselectedIconColor = deselectedIconColor;
    }

    public SingleOption(String title, @DrawableRes int icon, @ColorRes int borderColor, int borderWidth) {
        super(title);
        this.icon = icon;
        this.borderColor = borderColor;
        this.borderWidth = borderWidth;
    }
}
