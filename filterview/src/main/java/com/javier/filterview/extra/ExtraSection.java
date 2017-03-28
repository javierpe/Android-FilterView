package com.javier.filterview.extra;

import android.support.annotation.ColorRes;

import com.javier.filterview.FilterSection;

import java.util.ArrayList;

/**
 * Created by usuario on 23/02/17.
 */

public class ExtraSection extends FilterSection{

    /*
    LIST,
    NUMBER,
    DATE,
    HMS */

    private ArrayList<ExtraOption> options;
    private OnExtraOptionListener listener;

    public OnExtraOptionListener getOnExtraOptionListener() {
        return listener;
    }

    public void setOnExtraOptionListener(OnExtraOptionListener listener) {
        this.listener = listener;
    }

    public ArrayList<ExtraOption> getOptions() {
        return options;
    }

    public ExtraSection(int id, Builder builder) {
        super(id);
        this.setSectionNameColor(builder.sectionNameColor);
        this.setSectionName(builder.sectionName);
        this.options = builder.options;
    }

    public static class Builder{
        private ArrayList<ExtraOption> options;
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

        public Builder addOption(ExtraOption option){
            if(options == null){
                options = new ArrayList<>();
            }

            options.add(option);
            return this;
        }

        public ExtraSection build(){
            return new ExtraSection(id, this);
        }
    }
}
