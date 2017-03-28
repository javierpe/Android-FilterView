package com.javier.filterview.extra.clist;

import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.MaterialDialog;
import com.javier.filterview.extra.ExtraOption;

/**
 * Created by usuario on 28/02/17.
 */

public class ExtraList extends ExtraOption {
    private int colorAccent;
    private String[] items;
    private String titleDialog;
    private String positiveText;
    private String negativeText;
    private TypeList typeList;
    private MaterialDialog.ListCallbackMultiChoice listCallbackMultiChoice;
    private MaterialDialog.ListCallbackSingleChoice listCallbackSingleChoice;

    public MaterialDialog.ListCallbackMultiChoice getListCallbackMultiChoice() {
        return listCallbackMultiChoice;
    }

    public void setListCallbackMultiChoice(MaterialDialog.ListCallbackMultiChoice listCallbackMultiChoice) {
        this.listCallbackMultiChoice = listCallbackMultiChoice;
    }

    public MaterialDialog.ListCallbackSingleChoice getListCallbackSingleChoice() {
        return listCallbackSingleChoice;
    }

    public void setListCallbackSingleChoice(MaterialDialog.ListCallbackSingleChoice listCallbackSingleChoice) {
        this.listCallbackSingleChoice = listCallbackSingleChoice;
    }

    public int getColorAccent() {
        return colorAccent;
    }

    public void setColorAccent(int colorAccent) {
        this.colorAccent = colorAccent;
    }

    public String[] getItems() {
        return items;
    }

    public void setItems(String[] items) {
        this.items = items;
    }

    public String getTitleDialog() {
        return titleDialog;
    }

    public void setTitleDialog(String titleDialog) {
        this.titleDialog = titleDialog;
    }

    public String getPositiveText() {
        return positiveText;
    }

    public void setPositiveText(String positiveText) {
        this.positiveText = positiveText;
    }

    public String getNegativeText() {
        return negativeText;
    }

    public void setNegativeText(String negativeText) {
        this.negativeText = negativeText;
    }

    public TypeList getTypeList() {
        return typeList;
    }

    public void setTypeList(TypeList typeList) {
        this.typeList = typeList;
    }

    public ExtraList(String title, int titleColor) {
        super(title, titleColor);
    }

    public ExtraList(String title, int titleColor, @NonNull String[] items, TypeList typeList) {
        super(title, titleColor);
        this.items = items;
        this.typeList = typeList;
    }
}
