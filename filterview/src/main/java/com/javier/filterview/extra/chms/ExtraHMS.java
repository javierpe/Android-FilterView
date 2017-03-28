package com.javier.filterview.extra.chms;

import android.support.annotation.ColorRes;
import android.support.v4.app.FragmentManager;

import com.codetroopers.betterpickers.hmspicker.HmsPickerDialogFragment;
import com.javier.filterview.extra.ExtraOption;

/**
 * Created by usuario on 28/02/17.
 */

public class ExtraHMS extends ExtraOption{
    private FragmentManager fragmentManager;
    private HmsPickerDialogFragment.HmsPickerDialogHandlerV2  hmsPickerDialogHandlerV2;

    public HmsPickerDialogFragment.HmsPickerDialogHandlerV2 getHmsPickerDialogHandlerV2() {
        return hmsPickerDialogHandlerV2;
    }

    public void setHmsPickerDialogHandler(HmsPickerDialogFragment.HmsPickerDialogHandlerV2 hmsPickerDialogHandlerV2) {
        this.hmsPickerDialogHandlerV2 = hmsPickerDialogHandlerV2;
    }

    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public ExtraHMS(String title, @ColorRes int titleColor, FragmentManager fragmentManager) {
        super(title, titleColor);
        this.fragmentManager = fragmentManager;
    }
}
