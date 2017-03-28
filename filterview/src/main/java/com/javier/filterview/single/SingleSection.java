package com.javier.filterview.single;

import android.support.annotation.ColorRes;

import com.javier.filterview.FilterSection;
import java.util.ArrayList;

/**
 * Created by usuario on 20/02/17.
 */

public class SingleSection extends FilterSection{

    private ArrayList<SingleOption> options;
    private OnSingleOptionListener listener;

    public ArrayList<SingleOption> getOptions() {
        return options;
    }

    public SingleSection setOnSingleOptionListener(OnSingleOptionListener listener){
        this.listener = listener;
        return this;
    }

    public OnSingleOptionListener getOnSingleOptionListener(){
        return this.listener;
    }

    public SingleSection(int id, Builder builder){
        super(id);
        this.options = builder.options;
        this.setSectionName(builder.sectionName);
        this.setSectionNameColor(builder.sectionNameColor);
        this.setId(id);
    }

    public static class Builder{
        private ArrayList<SingleOption> options;
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

        public Builder addOption(SingleOption option){
            if(options == null){
                options = new ArrayList<>();
            }

            options.add(option);
            return this;
        }

        public SingleSection build(){
            return new SingleSection(id, this);
        }
    }

}
