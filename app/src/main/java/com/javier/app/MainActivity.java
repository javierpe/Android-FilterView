package com.javier.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.codetroopers.betterpickers.hmspicker.HmsPickerDialogFragment;
import com.javier.filterview.FilterView;
import com.javier.filterview.OnFilterViewResultListener;
import com.javier.filterview.extra.ExtraSection;
import com.javier.filterview.extra.cboolean.ExtraBoolean;
import com.javier.filterview.extra.cboolean.OnBooleanCheckedChangeListener;
import com.javier.filterview.extra.ccurrencytext.ExtraCurrencyEditText;
import com.javier.filterview.extra.cdate.ExtraDate;
import com.javier.filterview.extra.chms.ExtraHMS;
import com.javier.filterview.extra.clist.ExtraList;
import com.javier.filterview.extra.clist.TypeList;
import com.javier.filterview.extra.ctext.ExtraEditText;
import com.javier.filterview.extra.ctext.TextType;
import com.javier.filterview.single.OnSingleOptionListener;
import com.javier.filterview.single.SingleOption;
import com.javier.filterview.single.SingleSection;
import com.javier.filterview.slider.OnSliderValueChangeListener;
import com.javier.filterview.slider.SliderOption;
import com.javier.filterview.slider.SliderSection;
import com.javier.filterview.slider.SliderType;
import com.javier.filterview.tag.TagGravity;
import com.javier.filterview.tag.TagMode;
import com.javier.filterview.tag.TagSection;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {

    private Button btShowFilterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btShowFilterView = (Button)findViewById(R.id.btShowFilterView);
        btShowFilterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFilterView();
            }
        });
    }

    private void showFilterView(){

        // String title, int titleColor, int icon, int borderWidth, int borderColor
        SingleSection singleSection = new SingleSection.Builder("Category", 1)
                .setSectionNameColor(R.color.colorAccent)
                .addOption(new SingleOption("CUSTOMER", R.color.colorAccent,
                        R.drawable.ic_account_black_24dp, R.color.colorBackground,
                        R.color.colorAccent, 2, R.color.colorAccent))
                .addOption(new SingleOption("ORGANIZATION", R.color.colorAccent,
                        R.drawable.ic_layers_black_24dp, R.color.colorBackground,
                        R.color.colorAccent, 2, R.color.colorAccent))
                .build().setOnSingleOptionListener(new OnSingleOptionListener() {
                    @Override
                    public void onClick(SingleOption option) {

                    }
                });
        ;


        // int titleColor, int minValue, int maxValue, int barColor, int barHighlightColor, int leftThumbColor
        SliderSection sliderSection = new SliderSection.Builder("Cantidad", SliderType.TYPE_SINGLE, 2)
                .setSectionNameColor(R.color.colorAccent)
                .setSlider(new SliderOption(7, R.color.colorAccent,
                         0, 100, R.color.colorGray,
                        R.color.colorAccent,  R.color.colorAccent))
                .build();

        sliderSection.setOnSliderValueChangeListener(new OnSliderValueChangeListener() {
            @Override
            public void onValue(SliderOption option, int min, int max) {

            }
        });

        SliderSection sliderSectionRange = new SliderSection.Builder("Range", SliderType.TYPE_RANGE, 3)
                .setSectionNameColor(R.color.colorAccent)
                .setSlider(new SliderOption(6, R.color.colorAccent,
                        0, 100, R.color.colorGray,
                        R.color.colorAccent,  R.color.colorAccent))
                .build();

        sliderSectionRange.setOnSliderValueChangeListener(new OnSliderValueChangeListener() {
            @Override
            public void onValue(SliderOption option, int min, int max) {

            }
        });

      String[] data = {"TAG 1", "TAG 2", "TAG 3", "TAG 4", "TAG 5", "TAG 6"};

        TagSection tagSection = new TagSection.Builder("Tags", 4)
                .setSectionNameColor(R.color.colorAccent)
                .setSelectedColor(R.color.colorAccent)
                .setDeselectedColor(R.color.colorBackground)
                .setSelectedFontColor(R.color.colorBackground)
                .setDeselectedFontColor(R.color.colorAccent)
                .setGravity(TagGravity.CENTER)
                .setMode(TagMode.MULTI)
                .setLabels(data)
                .build();


        final ExtraBoolean extraBoolean1 = new ExtraBoolean(1, "Extra Boolean 1", R.color.colorAccent, R.color.colorCyan);
        extraBoolean1.setOnBooleanCheckedChangeListener(new OnBooleanCheckedChangeListener() {
            @Override
            public void onChecked(boolean isChecked) {
                System.out.println(extraBoolean1.getTitle());
                System.out.println(isChecked);
            }
        });

        final ExtraBoolean extraBoolean2 = new ExtraBoolean(2, "Extra Boolean 2", R.color.colorAccent, R.color.colorCyan);
        extraBoolean2.setOnBooleanCheckedChangeListener(new OnBooleanCheckedChangeListener() {
            @Override
            public void onChecked(boolean isChecked) {
                System.out.println(extraBoolean2.getTitle());
                System.out.println(isChecked);
            }
        });

        final ExtraBoolean extraBoolean3 = new ExtraBoolean(3, "Extra Boolean 3", R.color.colorAccent, R.color.colorCyan);
        extraBoolean3.setOnBooleanCheckedChangeListener(new OnBooleanCheckedChangeListener() {
            @Override
            public void onChecked(boolean isChecked) {
                System.out.println(extraBoolean3.getTitle());
                System.out.println(isChecked);
            }
        });

        ExtraEditText extraText = new ExtraEditText(4, "Extra EditTex", R.color.colorAccent,
                TextType.SINGLE, "Hint", R.drawable.ic_android_black_24dp);
        extraText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ExtraEditText extraText2 = new ExtraEditText(5, "Extra EditTex 2", R.color.colorAccent,
                TextType.MULTI_LINE, "Hint 2", R.drawable.ic_account_black_24dp);

        ExtraCurrencyEditText currencyEditText = new ExtraCurrencyEditText(6);
        currencyEditText.setColorAccent(R.color.colorAccent);
        currencyEditText.setHint("Hint Currency");
        currencyEditText.setIcon(R.drawable.ic_cash_usd_black_24dp);
        currencyEditText.setGroupDivider('.');
        currencyEditText.setMonetaryDivider(',');
        currencyEditText.setShowSymbol(true);
        currencyEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ExtraDate date = new ExtraDate("Selecciona una fecha", R.color.colorAccent, getSupportFragmentManager());
        date.setDoneText("OK");
        date.setCancelText("CANCEL");
        date.setOnDateSetListener(new CalendarDatePickerDialogFragment.OnDateSetListener() {
            @Override
            public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear, int dayOfMonth) {

            }
        });

        ExtraHMS extraHMS = new ExtraHMS("Ingresa el tiempo", R.color.colorAccent, getSupportFragmentManager());
        extraHMS.setHmsPickerDialogHandler(new HmsPickerDialogFragment.HmsPickerDialogHandlerV2() {
            @Override
            public void onDialogHmsSet(int reference, boolean isNegative, int hours, int minutes, int seconds) {

            }
        });

        String[] dataOpt = {"Option 1", "Option 2", "Option 3", "Option 4", "Option 5", "Option 6", "Option 7", "Option 8"};
        ExtraList extraList = new ExtraList("Select", R.color.colorAccent, dataOpt, TypeList.MULTI_CHOICE);

        ExtraSection extraSection = new ExtraSection.Builder("Extras", 5)
                .setSectionNameColor(R.color.colorAccent)
                .addOption(extraBoolean1)
                .addOption(extraBoolean2)
                .addOption(extraBoolean3)
                .addOption(extraText)
                .addOption(extraText2)
                .addOption(currencyEditText)
                .addOption(date)
                .addOption(extraHMS)
                .addOption(extraList)
                .build();


        new FilterView.Builder(this)
                .withTitle("Aplicar")
                .setToolbarVisible(true)
                .withTitleColor(R.color.colorAccent)
                .withDivisorColor(R.color.colorAccent)
                .setCloseIconColor(R.color.colorAccent)
                .addSection(singleSection)
                .addSection(sliderSectionRange)
                .addSection(tagSection)
                .addSection(extraSection)
                .build()
                .setOnFilterViewResultListener(new OnFilterViewResultListener() {
                    @Override
                    public void onResult(JSONArray data) {
                        System.out.println(data.toString());
                    }
                }).show();
    }
}
