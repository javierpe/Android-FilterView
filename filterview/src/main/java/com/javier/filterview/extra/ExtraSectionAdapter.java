package com.javier.filterview.extra;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.codetroopers.betterpickers.OnDialogDismissListener;
import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.codetroopers.betterpickers.hmspicker.HmsPickerBuilder;
import com.codetroopers.betterpickers.hmspicker.HmsPickerDialogFragment;
import com.google.gson.Gson;
import com.javier.filterview.R;
import com.javier.filterview.extra.cboolean.ExtraBoolean;
import com.javier.filterview.extra.ccurrencytext.CurrencyEditText;
import com.javier.filterview.extra.ccurrencytext.ExtraCurrencyEditText;
import com.javier.filterview.extra.cdate.ExtraDate;
import com.javier.filterview.extra.chms.ExtraHMS;
import com.javier.filterview.extra.clist.ExtraList;
import com.javier.filterview.extra.clist.TypeList;
import com.javier.filterview.extra.ctext.ExtraEditText;
import com.javier.filterview.extra.ctext.TextType;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by usuario on 23/02/17.
 */

public class ExtraSectionAdapter extends RecyclerView.Adapter<ExtraSectionAdapter.ExtraSectionHolder>{


    private Context context;
    private ArrayList<ExtraOption> options;
    private OnExtraOptionListener listener;
    private ExtraSection section;

    public ExtraSectionAdapter(Context context, ExtraSection section, OnExtraOptionListener listener) {
        this.context = context;
        this.options = section.getOptions();
        this.listener = listener;
        this.section = section;
    }

    @Override
    public ExtraSectionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.extra_option_item, parent, false);
        return new ExtraSectionAdapter.ExtraSectionHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ExtraSectionHolder holder, int position) {
        Object object = options.get(position);

        if(object instanceof ExtraBoolean){
            // Add Boolean View
           holder.container.addView(buildViewBoolean((ExtraBoolean)object));
        }else if(object instanceof ExtraDate){
            // Add Date View
            holder.container.addView(buildViewDate((ExtraDate)object));
        }else if(object instanceof ExtraHMS){
            // Add ExtraHMS View
            holder.container.addView(buildViewHMS((ExtraHMS) object));
        }else if(object instanceof ExtraEditText){
            // Add EditText View
            holder.container.addView(buildViewEditText((ExtraEditText)object));
        }else if(object instanceof ExtraCurrencyEditText){
            // Add EditText View
            holder.container.addView(buildViewCurrencyEditText((ExtraCurrencyEditText) object));
        }else if(object instanceof ExtraList) {
            // Add ExtraList View
            holder.container.addView(buildViewList((ExtraList) object));
        }
    }

    @Override
    public int getItemCount() {
        return options.size();
    }


    private LinearLayout buildViewBoolean(final ExtraBoolean object){
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams linearLayoutParams =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
        linearLayoutParams.height = 200;
        linearLayoutParams.setMargins(0, 0, 0, 0);
        linearLayout.setLayoutParams(linearLayoutParams);
        linearLayout.setPadding(22, 22, 22, 22);

        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, typedValue, true);
        linearLayout.setBackgroundResource(typedValue.resourceId);

        TextView textView = new TextView(context);
        textView.setText(object.getTitle());

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        params.weight = 1;

        textView.setLayoutParams(params);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setTypeface(Typeface.create("sans-serif-condensed", Typeface.NORMAL));
        textView.setTextSize(16);

        final CheckBox checkBox = new CheckBox(context);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(object.getOnBooleanCheckedChangeListener() != null){
                    object.getOnBooleanCheckedChangeListener().onChecked(isChecked);
                }


                try {
                    section.getResult().put("type", "extra");
                    section.getResult().put("section", section.getId());
                    section.getResult().put(String.valueOf(object.getId()), isChecked);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBox.setChecked(!checkBox.isChecked());
            }
        });

        linearLayout.addView(textView);
        linearLayout.addView(checkBox);

        return linearLayout;
    }

    private LinearLayout buildViewEditText(final ExtraEditText object){
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams linearLayoutParams =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

        //linearLayoutParams.height = 400;
        linearLayoutParams.setMargins(0, 0, 0, 0);
        linearLayout.setLayoutParams(linearLayoutParams);
        linearLayout.setPadding(22, 22, 22, 22);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

        MaterialEditText editText = new MaterialEditText(context);
        editText.setLayoutParams(params);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(object.getTextChangeListener() != null){
                    object.getTextChangeListener().beforeTextChanged(s, start, count, after);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(object.getTextChangeListener() != null){
                    object.getTextChangeListener().onTextChanged(s, start, before, count);
                }

                try {
                    section.getResult().put("type", "extra");
                    section.getResult().put("section", section.getId());
                    section.getResult().put(String.valueOf(object.getId()), s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(object.getTextChangeListener() != null){
                    object.getTextChangeListener().afterTextChanged(s);
                }
            }
        });

        editText.setHint(object.getHint());
        editText.setFloatingLabelText(object.getHint());
        editText.setFloatingLabel(MaterialEditText.FLOATING_LABEL_HIGHLIGHT);

        if(object.getIcon() != 0){
            editText.setIconLeft(ContextCompat.getDrawable(context, object.getIcon()));
        }

        if(object.getColorAccent() != 0) {
            editText.setBaseColor(object.getColorAccent());
        }else if(object.getTitleColor() != 0){
            editText.setBaseColor(object.getTitleColor());
        }

        if(object.getTextType().equals(TextType.SINGLE)){
            editText.setSingleLine(true);
        }else if(object.getTextType().equals(TextType.MULTI_LINE)){
            editText.setSingleLine(false);
        }

        linearLayout.addView(editText);

        return linearLayout;
    }

    private LinearLayout buildViewCurrencyEditText(final ExtraCurrencyEditText object){
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams linearLayoutParams =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

        //linearLayoutParams.height = 400;
        linearLayoutParams.setMargins(0, 0, 0, 0);
        linearLayout.setLayoutParams(linearLayoutParams);
        linearLayout.setPadding(22, 22, 22, 22);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

        CurrencyEditText currencyEditText = new CurrencyEditText(context, null);
        currencyEditText.setLayoutParams(params);
        currencyEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(object.getTextChangeListener() != null){
                    object.getTextChangeListener().beforeTextChanged(s, start, count, after);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(object.getTextChangeListener() != null){
                    object.getTextChangeListener().onTextChanged(s, start, before, count);
                }

                try {
                    section.getResult().put("type", "extra");
                    section.getResult().put("section", section.getId());
                    section.getResult().put(String.valueOf(object.getId()), s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(object.getTextChangeListener() != null){
                    object.getTextChangeListener().afterTextChanged(s);
                }
            }
        });



        currencyEditText.setHint(object.getHint());
        currencyEditText.setFloatingLabelText(object.getHint());
        currencyEditText.setFloatingLabel(MaterialEditText.FLOATING_LABEL_HIGHLIGHT);

        currencyEditText.setMonetaryDivider(object.getMonetaryDivider());
        currencyEditText.setGroupDivider(object.getGroupDivider());

        if(object.getLocale() != null){
            currencyEditText.setLocale(object.getLocale());
        }

        currencyEditText.showSymbol(object.isShowSymbol());

        if(object.getIcon() != 0){
            currencyEditText.setIconLeft(ContextCompat.getDrawable(context, object.getIcon()));
        }

        if(object.getColorAccent() != 0) {
            currencyEditText.setBaseColor(object.getColorAccent());
        }else if(object.getTitleColor() != 0){
            currencyEditText.setBaseColor(object.getTitleColor());
        }

        currencyEditText.setSingleLine(true);
        linearLayout.addView(currencyEditText);

        return linearLayout;
    }

    private LinearLayout buildViewDate(final ExtraDate object){
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams linearLayoutParams =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);

        //linearLayoutParams.height = 200;
        linearLayout.setPadding(32, 32, 32, 32);
        linearLayout.setLayoutParams(linearLayoutParams);

        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, typedValue, true);
        linearLayout.setBackgroundResource(typedValue.resourceId);

        ImageView imageView = new ImageView(context);
        imageView.setBackgroundResource(R.drawable.ic_calendar_black_24dp);
        linearLayout.addView(imageView);
        linearLayout.setGravity(Gravity.CENTER_VERTICAL);

        LinearLayout linearLayout1 = new LinearLayout(context);
        linearLayout1.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams linearLayoutParams2 =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

        linearLayoutParams2.weight = 1;
        TextView textView = new TextView(context);

        if(object.getTitle().isEmpty()) {
            textView.setText("Selecciona una fecha");
        }else{
            textView.setText(object.getTitle());
        }

        textView.setLayoutParams(linearLayoutParams2);
        textView.setTextSize(16);
        if(object.getTitleColor() != 0) {
            textView.setTextColor(ContextCompat.getColor(context, object.getTitleColor()));
        }else{
            textView.setTextColor(Color.parseColor("#000000"));
        }

        textView.setTypeface(Typeface.create("sans-serif-condensed", Typeface.NORMAL));

        final TextView textView1 = new TextView(context);
        textView1.setText("- -/ - -/ - - -");

        linearLayout1.addView(textView);
        linearLayout1.addView(textView1);

        linearLayout1.setPadding(52, 0, 12, 0);
        linearLayout.addView(linearLayout1);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalendarDatePickerDialogFragment cdp = new CalendarDatePickerDialogFragment()
                        .setOnDateSetListener(new CalendarDatePickerDialogFragment.OnDateSetListener() {
                            @Override
                            public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear, int dayOfMonth) {
                                if(object.getOnDateSetListener() != null){
                                    object.getOnDateSetListener().onDateSet(dialog, year, monthOfYear, dayOfMonth);
                                }

                                String date = String.valueOf(dayOfMonth) + "/"
                                        + (monthOfYear <= 9 ? "0" + String.valueOf(monthOfYear) : String.valueOf(monthOfYear))
                                        + "/" + String.valueOf(year);

                                textView1.setText(date);

                                // Set result

                                try {
                                    section.getResult().put("type", "extra");
                                    section.getResult().put("section", section.getId());
                                    section.getResult().put(String.valueOf(object.getId()), date);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                        .setFirstDayOfWeek(object.getFirstDayOfWeek() != 0 ? object.getFirstDayOfWeek() : Calendar.SUNDAY)
                        .setDoneText(object.getDoneText())
                        .setCancelText(object.getCancelText())
                        .setOnDismissListener(new OnDialogDismissListener() {
                            @Override
                            public void onDialogDismiss(DialogInterface dialoginterface) {
                                if(object.getOnDialogDismissListener() != null){
                                    object.getOnDialogDismissListener().onDialogDismiss(dialoginterface);
                                }
                            }
                        });


                if(object.isThemeDark()){
                    cdp.setThemeDark();
                }else if(object.isThemeLight()){
                    cdp.setThemeLight();
                }

                if(object.getStartDate() != null && object.getEndDate() != null){
                    cdp.setDateRange(object.getStartDate(), object.getEndDate());
                }else{
                    if(object.getStartDate() != null){
                        cdp.setDateRange(object.getStartDate(), null);
                    }else if(object.getEndDate() != null){
                        cdp.setDateRange(null, object.getEndDate());
                    }
                }

                cdp.show(object.getFragmentManager(), "12");
            }
        });

        return linearLayout;
    }

    private LinearLayout buildViewHMS(final ExtraHMS object){
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams linearLayoutParams =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);

        //linearLayoutParams.height = 200;
        linearLayout.setPadding(32, 32, 32, 32);
        linearLayout.setLayoutParams(linearLayoutParams);

        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, typedValue, true);
        linearLayout.setBackgroundResource(typedValue.resourceId);

        ImageView imageView = new ImageView(context);
        imageView.setBackgroundResource(R.drawable.ic_clock_black_24dp);
        linearLayout.addView(imageView);
        linearLayout.setGravity(Gravity.CENTER_VERTICAL);

        LinearLayout linearLayout1 = new LinearLayout(context);
        linearLayout1.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams linearLayoutParams2 =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

        linearLayoutParams2.weight = 1;
        TextView textView = new TextView(context);
        if(object.getTitle().isEmpty()) {
            textView.setText("Ingresa el tiempo");
        }else {
            textView.setText(object.getTitle());
        }
        textView.setLayoutParams(linearLayoutParams2);
        textView.setTextSize(16);
        if(object.getTitleColor() != 0) {
            textView.setTextColor(ContextCompat.getColor(context, object.getTitleColor()));
        }else{
            textView.setTextColor(Color.parseColor("#000000"));
        }

        textView.setTypeface(Typeface.create("sans-serif-condensed", Typeface.NORMAL));

        final TextView textView1 = new TextView(context);
        textView1.setText("00:00:00");

        linearLayout1.addView(textView);
        linearLayout1.addView(textView1);

        linearLayout1.setPadding(52, 0, 12, 0);
        linearLayout.addView(linearLayout1);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HmsPickerBuilder hpb = new HmsPickerBuilder()
                        .setFragmentManager(object.getFragmentManager())
                        .setStyleResId(R.style.BetterPickersDialogFragment_Light);
                hpb.addHmsPickerDialogHandler(new HmsPickerDialogFragment.HmsPickerDialogHandlerV2() {
                    @Override
                    public void onDialogHmsSet(int reference, boolean isNegative, int hours, int minutes, int seconds) {
                        if(object.getHmsPickerDialogHandlerV2() != null){
                            object.getHmsPickerDialogHandlerV2().onDialogHmsSet(reference, isNegative, hours, minutes, seconds);
                        }

                        String hour = (hours <= 9 ? "0" + String.valueOf(hours) : String.valueOf(hours))
                                + ":" + (minutes <= 9 ? "0" + String.valueOf(minutes) : String.valueOf(minutes))
                                + ":" + (seconds <= 9 ? "0" + String.valueOf(seconds) : String.valueOf(seconds));

                        textView1.setText(hour);

                        // Set result
                        try {
                            section.getResult().put("type", "extra");
                            section.getResult().put("section", section.getId());
                            section.getResult().put(String.valueOf(object.getId()), hour);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

                hpb.show();
            }
        });

        return linearLayout;
    }

    private LinearLayout buildViewList(final ExtraList object){
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams linearLayoutParams =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);

        //linearLayoutParams.height = 200;
        linearLayout.setPadding(32, 32, 32, 32);
        linearLayout.setLayoutParams(linearLayoutParams);

        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, typedValue, true);
        linearLayout.setBackgroundResource(typedValue.resourceId);

        ImageView imageView = new ImageView(context);
        imageView.setBackgroundResource(R.drawable.ic_format_list_bulleted_black_24dp);
        linearLayout.addView(imageView);
        linearLayout.setGravity(Gravity.CENTER_VERTICAL);

        LinearLayout linearLayout1 = new LinearLayout(context);
        linearLayout1.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams linearLayoutParams2 =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

        linearLayoutParams2.weight = 1;
        TextView textView = new TextView(context);

        if(object.getTitle().isEmpty()) {
            if(object.getTypeList().equals(TypeList.SINGLE_CHOICE)) {
                textView.setText("Selecciona un elemento");
            }else if(object.getTypeList().equals(TypeList.MULTI_CHOICE)){
                textView.setText("Selecciona elementos");
            }
        }else {
            textView.setText(object.getTitle());
        }

        textView.setLayoutParams(linearLayoutParams2);
        textView.setTextSize(16);
        if(object.getTitleColor() != 0) {
            textView.setTextColor(ContextCompat.getColor(context, object.getTitleColor()));
        }else{
            textView.setTextColor(Color.parseColor("#000000"));
        }

        textView.setTypeface(Typeface.create("sans-serif-condensed", Typeface.NORMAL));

        final TextView textView1 = new TextView(context);
        textView1.setText("Nada seleccionado");

        linearLayout1.addView(textView);
        linearLayout1.addView(textView1);

        linearLayout1.setPadding(52, 0, 12, 0);
        linearLayout.addView(linearLayout1);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MaterialDialog.Builder dialog = new MaterialDialog.Builder(context)
                        .title(object.getTitle())
                        .items(object.getItems());

                if(object.getNegativeText() == null){
                    dialog.negativeText("CANCELAR");
                }else{
                    dialog.negativeText(object.getNegativeText());
                }

                if(object.getPositiveText() == null){
                    dialog.positiveText("ACEPTAR");
                }else {
                    dialog.positiveText(object.getPositiveText());
                }

                if(object.getTitle() != null){
                    dialog.title(object.getTitle());
                }

                if(object.getTypeList().equals(TypeList.MULTI_CHOICE)){
                    dialog.title("Selecciona elementos");
                    dialog.itemsCallbackMultiChoice(null, new MaterialDialog.ListCallbackMultiChoice() {
                        @Override
                        public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
                            if(object.getListCallbackMultiChoice() != null){
                                object.getListCallbackMultiChoice().onSelection(dialog, which, text);
                            }

                            String dataString = "";
                            if(text != null && text.length > 0){
                                if(text.length > 1){
                                    dataString = String.valueOf(text.length) + " elementos seleccionados";
                                }else{
                                    dataString = text[0].toString();
                                }

                                textView1.setText(dataString);

                                // Set result
                                try {
                                    section.getResult().put("type", "extra");
                                    section.getResult().put("section", section.getId());
                                    section.getResult().put(String.valueOf(object.getId()), new Gson().toJson(text));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            return true;
                        }
                    });
                }else {
                    dialog.title("Selecciona un elemento");
                    dialog.itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                        @Override
                        public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                            if(object.getListCallbackSingleChoice() != null){
                                object.getListCallbackSingleChoice().onSelection(dialog, itemView, which, text);
                            }

                            if(text != null){
                                textView1.setText(text.toString());

                                // Set result
                                JSONObject data = new JSONObject();

                                try {
                                    data.put("type", "extra");
                                    data.put("section", section.getId());
                                    data.put("value", text.toString());
                                    section.setResult(data);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            return true;
                        }
                    });
                }

                dialog.show();
            }
        });

        return linearLayout;
    }


    class ExtraSectionHolder extends RecyclerView.ViewHolder{

        public LinearLayout container;

        public ExtraSectionHolder(View itemView) {
            super(itemView);
            container = (LinearLayout)itemView.findViewById(R.id.container);
        }
    }
}
