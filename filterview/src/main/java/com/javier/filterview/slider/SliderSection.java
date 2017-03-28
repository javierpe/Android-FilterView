package com.javier.filterview.slider;

import android.support.annotation.ColorRes;

import com.javier.filterview.FilterSection;

/**
 * Created by usuario on 20/02/17.
 */

public class SliderSection extends FilterSection{

    private SliderType type;
    private SliderOption option;
    private OnSliderValueChangeListener listener;

    public SliderSection(int id, Builder builder){
        super(id);
        this.setId(id);
        this.option = builder.option;
        this.type = builder.type;
        this.setSectionNameColor(builder.sectionNameColor);
        this.setSectionName(builder.sectionName);
    }

    public SliderType getType() {
        return type;
    }

    public void setType(SliderType type) {
        this.type = type;
    }

    public SliderOption getOption() {
        return option;
    }

    public void setOnSliderValueChangeListener(OnSliderValueChangeListener listener){
        this.listener = listener;
    }

    public OnSliderValueChangeListener getOnSliderValueChangeListener() {
        return listener;
    }

    public static class Builder{
        private SliderOption option;
        private String sectionName;
        private int sectionNameColor;
        private SliderType type;
        private int id;

        public Builder(String sectionName, SliderType type, int id){
            this.sectionName = sectionName;
            this.type = type;
            this.id = id;
        }

        public Builder setSectionNameColor(@ColorRes int sectionNameColor){
            this.sectionNameColor = sectionNameColor;
            return this;
        }

        public Builder setSlider(SliderOption option){
            this.option = option;
            return this;
        }

        public SliderSection build(){
            return new SliderSection(id, this);
        }
    }
}
