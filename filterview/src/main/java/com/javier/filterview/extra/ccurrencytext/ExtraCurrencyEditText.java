package com.javier.filterview.extra.ccurrencytext;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.text.TextWatcher;
import com.javier.filterview.extra.ExtraOption;
import java.util.Locale;

/**
 * Created by usuario on 27/02/17.
 */

public class ExtraCurrencyEditText extends ExtraOption{

    private Locale locale;
    private boolean showSymbol;
    private char monetaryDivider;
    private char groupDivider;
    private String hint;
    private int icon;
    private int colorAccent;
    private TextWatcher textWatcher;

    public ExtraCurrencyEditText(int id) {
        super(id);
    }

    public void addTextChangedListener(TextWatcher textWatcher){
        this.textWatcher = textWatcher;
    }

    public TextWatcher getTextChangeListener(){
        return this.textWatcher;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public boolean isShowSymbol() {
        return showSymbol;
    }

    public void setShowSymbol(boolean showSymbol) {
        this.showSymbol = showSymbol;
    }

    public char getMonetaryDivider() {
        return monetaryDivider;
    }

    public void setMonetaryDivider(char monetaryDivider) {
        this.monetaryDivider = monetaryDivider;
    }

    public char getGroupDivider() {
        return groupDivider;
    }

    public void setGroupDivider(char groupDivider) {
        this.groupDivider = groupDivider;
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

    public TextWatcher getTextWatcher() {
        return textWatcher;
    }

    public void setTextWatcher(TextWatcher textWatcher) {
        this.textWatcher = textWatcher;
    }

}
