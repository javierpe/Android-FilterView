package com.javier.filterview.extra.ctext;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.text.TextWatcher;

import com.javier.filterview.extra.ExtraOption;

/**
 * Created by usuario on 23/02/17.
 */

public class ExtraEditText extends ExtraOption{
    private TextType textType;
    private String hint;
    private int icon;
    private int colorAccent;
    private TextWatcher textWatcher;

    public void addTextChangedListener(TextWatcher textWatcher){
        this.textWatcher = textWatcher;
    }

    public TextWatcher getTextChangeListener(){
        return this.textWatcher;
    }

    public TextType getTextType() {
        return textType;
    }

    public void setTextType(TextType textType) {
        this.textType = textType;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(@DrawableRes int icon) {
        this.icon = icon;
    }

    public int getColorAccent() {
        return colorAccent;
    }

    public void setColorAccent(@ColorRes int colorAccent) {
        this.colorAccent = colorAccent;
    }

    public ExtraEditText(int id, String title, @ColorRes int titleColor, TextType textType) {
        super(title, titleColor);
        this.textType = textType;
        this.setId(id);
    }

    public ExtraEditText(int id, String title, @ColorRes int titleColor, TextType textType, String hint) {
        super(title, titleColor);
        this.textType = textType;
        this.hint = hint;
        this.setId(id);
    }

    public ExtraEditText(int id, String title, @ColorRes int titleColor, TextType textType, String hint, @DrawableRes int icon) {
        super(title, titleColor);
        this.textType = textType;
        this.hint = hint;
        this.icon = icon;
        this.setId(id);
    }

    public ExtraEditText(int id, String title, @ColorRes int titleColor, TextType textType, String hint, @DrawableRes int icon, @ColorRes int colorAccent) {
        super(title, titleColor);
        this.textType = textType;
        this.hint = hint;
        this.icon = icon;
        this.colorAccent = colorAccent;
        this.setId(id);
    }
}