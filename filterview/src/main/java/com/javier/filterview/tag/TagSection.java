package com.javier.filterview.tag;

import android.support.annotation.ColorRes;

import com.javier.filterview.FilterSection;

/**
 * Created by usuario on 20/02/17.
 */

public class TagSection extends FilterSection{

    private int selectedColor;
    private int selectedFontColor;
    private int deselectedColor;
    private int deselectedFontColor;
    private String[] labels;
    private TagMode mode;
    private TagGravity gravity;
    private OnTagListener listener;

    public TagSection setOnTagListener(OnTagListener listener){
        this.listener = listener;
        return this;
    }

    public OnTagListener getOnTagListener(){
        return this.listener;
    }

    public int getSelectedColor() {
        return selectedColor;
    }

    public int getSelectedFontColor() {
        return selectedFontColor;
    }

    public int getDeselectedColor() {
        return deselectedColor;
    }

    public int getDeselectedFontColor() {
        return deselectedFontColor;
    }

    public String[] getLabels() {
        return labels;
    }

    public TagMode getMode() {
        return mode;
    }

    public TagGravity getGravity() {
        return gravity;
    }


    public TagSection(int id, Builder builder){
        super(id);
        this.setId(id);
        this.selectedColor = builder.selectedColor;
        this.selectedFontColor = builder.selectedFontColor;
        this.deselectedColor = builder.deselectedColor;
        this.deselectedFontColor = builder.deselectedFontColor;
        this.labels = builder.labels;
        this.mode = builder.mode;
        this.gravity = builder.gravity;
        this.setSectionName(builder.sectionName);
        this.setSectionNameColor(builder.sectionNameColor);
    }

    public static class Builder{
        private int selectedColor;
        private int selectedFontColor;
        private int deselectedColor;
        private int deselectedFontColor;
        private String[] labels;
        private TagMode mode;
        private TagGravity gravity;
        private String sectionName;
        private int sectionNameColor;
        private int id;

        public Builder(String sectionName, int id){
            this.sectionName = sectionName;
            this.id = id;
        }

        public Builder setSectionNameColor(@ColorRes int sectionNameColor){
            this.sectionNameColor = sectionNameColor;
            return this;
        }

        public Builder setSelectedColor(@ColorRes int color){
            this.selectedColor = color;
            return this;
        }

        public Builder setSelectedFontColor(@ColorRes int color){
            this.selectedFontColor = color;
            return this;
        }

        public Builder setDeselectedColor(@ColorRes int color){
            this.deselectedColor = color;
            return this;
        }

        public Builder setDeselectedFontColor(@ColorRes int color){
            this.deselectedFontColor = color;
            return this;
        }

        public Builder setLabels(String[] labels){
            this.labels = labels;
            return this;
        }

        public Builder setMode(TagMode mode){
            this.mode = mode;
            return this;
        }

        public Builder setGravity(TagGravity gravity){
            this.gravity = gravity;
            return this;
        }

        public TagSection build(){
            return new TagSection(id, this);
        }
    }
}
