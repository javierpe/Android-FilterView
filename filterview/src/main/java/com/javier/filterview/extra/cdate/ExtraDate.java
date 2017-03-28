package com.javier.filterview.extra.cdate;

import android.support.annotation.ColorRes;
import android.support.v4.app.FragmentManager;
import android.util.SparseArray;

import com.codetroopers.betterpickers.OnDialogDismissListener;
import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.codetroopers.betterpickers.calendardatepicker.MonthAdapter;
import com.javier.filterview.extra.ExtraOption;

/**
 * Created by usuario on 23/02/17.
 */

public class ExtraDate extends ExtraOption {
    private String doneText;
    private String cancelText;
    private boolean themeDark;
    private boolean themeLight;
    private int themeCustom;
    private int firstDayOfWeek;
    private FragmentManager fragmentManager;
    private MonthAdapter.CalendarDay startDate;
    private MonthAdapter.CalendarDay endDate;
    private SparseArray<MonthAdapter.CalendarDay> disabledDays;
    private OnDialogDismissListener onDialogDismissListener;
    private CalendarDatePickerDialogFragment.OnDateSetListener onDateSetListener;

    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public int getThemeCustom() {
        return themeCustom;
    }

    public void setThemeCustom(int themeCustom) {
        this.themeCustom = themeCustom;
    }

    public String getDoneText() {
        return doneText;
    }

    public void setDoneText(String doneText) {
        this.doneText = doneText;
    }

    public String getCancelText() {
        return cancelText;
    }

    public void setCancelText(String cancelText) {
        this.cancelText = cancelText;
    }

    public boolean isThemeDark() {
        return themeDark;
    }

    public void setThemeDark(boolean themeDark) {
        this.themeDark = themeDark;
    }

    public boolean isThemeLight() {
        return themeLight;
    }

    public void setThemeLight(boolean themeLight) {
        this.themeLight = themeLight;
    }

    public int getFirstDayOfWeek() {
        return firstDayOfWeek;
    }

    public void setFirstDayOfWeek(int firstDayOfWeek) {
        this.firstDayOfWeek = firstDayOfWeek;
    }

    public MonthAdapter.CalendarDay getStartDate() {
        return startDate;
    }

    public void setStartDate(MonthAdapter.CalendarDay startDate) {
        this.startDate = startDate;
    }

    public MonthAdapter.CalendarDay getEndDate() {
        return endDate;
    }

    public void setEndDate(MonthAdapter.CalendarDay endDate) {
        this.endDate = endDate;
    }

    public SparseArray<MonthAdapter.CalendarDay> getDisabledDays() {
        return disabledDays;
    }

    public void setDisabledDays(SparseArray<MonthAdapter.CalendarDay> disabledDays) {
        this.disabledDays = disabledDays;
    }

    public OnDialogDismissListener getOnDialogDismissListener() {
        return onDialogDismissListener;
    }

    public void setOnDialogDismissListener(OnDialogDismissListener onDialogDismissListener) {
        this.onDialogDismissListener = onDialogDismissListener;
    }

    public CalendarDatePickerDialogFragment.OnDateSetListener getOnDateSetListener() {
        return onDateSetListener;
    }

    public void setOnDateSetListener(CalendarDatePickerDialogFragment.OnDateSetListener onDateSetListener) {
        this.onDateSetListener = onDateSetListener;
    }

    public ExtraDate(String title, @ColorRes int titleColor, FragmentManager fragmentManager) {
        super(title, titleColor);
        this.fragmentManager = fragmentManager;
    }
}
