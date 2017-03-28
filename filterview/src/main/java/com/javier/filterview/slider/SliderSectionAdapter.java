package com.javier.filterview.slider;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.interfaces.OnSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.crystal.crystalrangeseekbar.widgets.CrystalSeekbar;
import com.javier.filterview.R;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by usuario on 21/02/17.
 */

public class SliderSectionAdapter extends RecyclerView.Adapter<SliderSectionAdapter.SliderSectionHolder>{

    private SliderType type;
    private Context context;
    private ArrayList<SliderOption> options;
    private SliderSection section;
    private OnSliderValueChangeListener onSliderValueChangeListener;

    public SliderSectionAdapter(Context context, SliderType type, SliderSection section, OnSliderValueChangeListener listener) {

        ArrayList<SliderOption> options = new ArrayList<>();
        options.add(section.getOption());

        this.context = context;
        this.options = options;
        this.type = type;
        this.section = section;
        this.onSliderValueChangeListener = listener;
    }

    @Override
    public SliderSectionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.slider_option_item, parent, false);
        return new SliderSectionAdapter.SliderSectionHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SliderSectionHolder holder, int position) {
        final SliderOption option = options.get(position);

        if(type.equals(SliderType.TYPE_SINGLE)){
            holder.slider.setVisibility(View.VISIBLE);
            holder.sliderRangeBar.setVisibility(View.GONE);
        }else if(type.equals(SliderType.TYPE_RANGE)){
            holder.slider.setVisibility(View.GONE);
            holder.sliderRangeBar.setVisibility(View.VISIBLE);
        }

        if(option.getBarColor() != 0){
            if(type.equals(SliderType.TYPE_SINGLE)) {
                holder.slider.setBarColor(ContextCompat
                        .getColor(context, option.getBarColor()));
            }else if(type.equals(SliderType.TYPE_RANGE)){
                holder.sliderRangeBar.setBarColor(ContextCompat
                        .getColor(context, option.getBarColor()));
            }
        }

       if(option.getBarHighlightColor() != 0){

           if(option.getBarHighlightColor() != 0) {
               holder.lbMax.setTextColor(ContextCompat
                       .getColor(context, option.getBarHighlightColor()));
               holder.lbMin.setTextColor(ContextCompat
                       .getColor(context, option.getBarHighlightColor()));
           }

           if(type.equals(SliderType.TYPE_SINGLE)) {
               holder.slider.setBarHighlightColor(ContextCompat
                       .getColor(context, option.getBarHighlightColor()));

           }else if(type.equals(SliderType.TYPE_RANGE)){
               /*holder.sliderRangeBar.setLeftThumbHighlightColor(ContextCompat
                       .getColor(context, option.getBarHighlightColor()));
               holder.sliderRangeBar.setRightThumbHighlightColor(ContextCompat
                       .getColor(context, option.getBarHighlightColor()));*/
               holder.sliderRangeBar.setRightThumbColor(ContextCompat
                       .getColor(context, option.getBarHighlightColor()));
               holder.sliderRangeBar.setBarHighlightColor(ContextCompat
                       .getColor(context, option.getBarHighlightColor()));
           }
       }

        if(option.getLeftThumbColor() != 0){
            if(type.equals(SliderType.TYPE_SINGLE)) {
                holder.slider.setLeftThumbColor(ContextCompat.getColor(context, option.getLeftThumbColor()));
            }else if(type.equals(SliderType.TYPE_RANGE)){
                holder.sliderRangeBar.setLeftThumbColor(ContextCompat.getColor(context, option.getLeftThumbColor()));
            }
        }

        if(type.equals(SliderType.TYPE_SINGLE)) {
            holder.slider.setMaxValue(option.getMaxValue());
        }else if(type.equals(SliderType.TYPE_RANGE)){
            if(option.getFixGap() != 0){
                holder.sliderRangeBar.setFixGap(option.getFixGap());
            }
            holder.sliderRangeBar.setMaxValue(option.getMaxValue());
        }

        holder.lbMax.setText(String.valueOf(option.getMaxValue()));
        holder.lbMin.setText(String.valueOf(option.getMinValue()));

        if(type.equals(SliderType.TYPE_SINGLE)) {
            holder.slider.setOnSeekbarChangeListener(new OnSeekbarChangeListener() {
                @Override
                public void valueChanged(Number value) {
                    holder.lbMin.setText(String.valueOf(value.intValue()));

                    if (onSliderValueChangeListener != null) {
                        onSliderValueChangeListener.onValue(option, value.intValue(), option.getMaxValue());
                    }

                    JSONObject data = new JSONObject();
                    try {
                        data.put("section", section.getId());
                        data.put("type", "single_bar");
                        data.put("value", String.valueOf(value.intValue()));
                        section.setResult(data);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }else if(type.equals(SliderType.TYPE_RANGE)){
            holder.sliderRangeBar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
                @Override
                public void valueChanged(Number minValue, Number maxValue) {
                    holder.lbMin.setText(String.valueOf(minValue.intValue()));
                    holder.lbMax.setText(String.valueOf(maxValue.intValue()));

                    if (onSliderValueChangeListener != null) {
                        onSliderValueChangeListener.onValue(option, minValue.intValue(), maxValue.intValue());
                    }

                    JSONObject data = new JSONObject();
                    try {
                        data.put("section", section.getId());
                        data.put("type", "range_bar");
                        data.put("value_min", String.valueOf(minValue.intValue()));
                        data.put("value_max", String.valueOf(maxValue.intValue()));
                        section.setResult(data);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return options.size();
    }

    class SliderSectionHolder extends RecyclerView.ViewHolder{

        public TextView lbMin;
        public TextView lbMax;
        public CrystalSeekbar slider;
        public CrystalRangeSeekbar sliderRangeBar;

        public SliderSectionHolder(View itemView) {
            super(itemView);
            slider = (CrystalSeekbar)itemView.findViewById(R.id.slider);
            sliderRangeBar = (CrystalRangeSeekbar)itemView.findViewById(R.id.sliderRangeBar);
            lbMin = (TextView)itemView.findViewById(R.id.lbMin);
            lbMax = (TextView)itemView.findViewById(R.id.lbMax);
        }
    }
}
