package com.javier.filterview;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.javier.filterview.extra.ExtraSection;
import com.javier.filterview.extra.ExtraSectionAdapter;
import com.javier.filterview.extra.OnExtraOptionListener;
import com.javier.filterview.single.OnSingleOptionListener;
import com.javier.filterview.single.SingleOption;
import com.javier.filterview.single.SingleSection;
import com.javier.filterview.single.SingleSectionAdapter;
import com.javier.filterview.slider.OnSliderValueChangeListener;
import com.javier.filterview.slider.SliderOption;
import com.javier.filterview.slider.SliderSection;
import com.javier.filterview.slider.SliderSectionAdapter;
import com.javier.filterview.tag.OnTagListener;
import com.javier.filterview.tag.TagAdapter;
import com.javier.filterview.tag.TagSection;
import com.javier.filterview.R;

import java.util.ArrayList;

/**
 * Created by usuario on 21/02/17.
 */

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.FilterHolder>{

    private Context context;
    private ArrayList<Object> sections;
    private int divisorColor;

    public FilterAdapter(Context context, ArrayList<Object> sections) {
        this.context = context;
        this.sections = sections;
    }

    @Override
    public FilterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.filter_section, parent, false);
        return new FilterAdapter.FilterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FilterHolder holder, int position) {
        Object section = sections.get(position);

        if(section instanceof SingleSection){
            final SingleSection singleSection = (SingleSection)section;
            holder.sectionName.setText(singleSection.getSectionName());
            if(singleSection.getSectionNameColor() != 0) {
                holder.sectionName.setTextColor(ContextCompat.getColor(context, singleSection.getSectionNameColor()));
            }

            if(singleSection.getOptions() != null){
                SingleSectionAdapter adapter = new SingleSectionAdapter(context, singleSection,
                        new OnSingleOptionListener() {
                    @Override
                    public void onClick(SingleOption option) {
                        if(singleSection.getOnSingleOptionListener() != null){
                            singleSection.getOnSingleOptionListener().onClick(option);
                        }
                    }
                });

                int numCol = 3;
                int size = singleSection.getOptions().size();

                if(size < 3){
                    numCol = 2;
                }

                GridLayoutManager gridLayoutManager = new GridLayoutManager(context, numCol);
                holder.section.setLayoutManager(gridLayoutManager);
                holder.section.setAdapter(adapter);

                if(size < 3){
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.gravity = Gravity.CENTER;
                    holder.section.setLayoutParams(params);
                }
            }
        }else if(section instanceof SliderSection){
            final SliderSection sliderSection = (SliderSection)section;
            holder.sectionName.setText(sliderSection.getSectionName());
            if(sliderSection.getSectionNameColor() != 0) {
                holder.sectionName.setTextColor(ContextCompat.getColor(context, sliderSection.getSectionNameColor()));
            }

            if(sliderSection.getOption() != null){


                SliderSectionAdapter adapter = new SliderSectionAdapter(context, sliderSection.getType(),
                        sliderSection, new OnSliderValueChangeListener() {
                    @Override
                    public void onValue(SliderOption option, int min, int max) {
                        if(sliderSection.getOnSliderValueChangeListener() != null){
                            sliderSection.getOnSliderValueChangeListener().onValue(option, min, max);
                        }
                    }
                });

                holder.section.setLayoutManager(new LinearLayoutManager(context));
                holder.section.setAdapter(adapter);
            }
        }else if(section instanceof TagSection){
            final TagSection tagSection = (TagSection)section;
            holder.sectionName.setText(tagSection.getSectionName());
            if(tagSection.getSectionNameColor() != 0) {
                holder.sectionName.setTextColor(ContextCompat.getColor(context, tagSection.getSectionNameColor()));
            }

            if(tagSection.getLabels() != null){
                TagAdapter adapter = new TagAdapter(context, tagSection, new OnTagListener() {
                    @Override
                    public void onTagSelected(ArrayList<String> tags) {
                        if(tagSection.getOnTagListener() != null){
                            tagSection.getOnTagListener().onTagSelected(tags);
                        }
                    }
                });

                holder.section.setLayoutManager(new LinearLayoutManager(context));
                holder.section.setAdapter(adapter);
            }
        }else if(section instanceof ExtraSection){
            final ExtraSection extraSection = (ExtraSection)section;
            holder.sectionName.setText(extraSection.getSectionName());
            if(extraSection.getSectionNameColor() != 0) {
                holder.sectionName.setTextColor(ContextCompat.getColor(context, extraSection.getSectionNameColor()));
            }

            if(extraSection.getOptions() != null){
                ExtraSectionAdapter adapter = new ExtraSectionAdapter(context, extraSection, new OnExtraOptionListener() {
                    @Override
                    public void onExtra() {
                        if(extraSection.getOnExtraOptionListener() != null){
                            extraSection.getOnExtraOptionListener().onExtra();
                        }
                    }
                });

                holder.section.setLayoutManager(new LinearLayoutManager(context));
                holder.section.setAdapter(adapter);
            }
        }
    }

    @Override
    public int getItemCount() {
        return sections.size();
    }

    class FilterHolder extends RecyclerView.ViewHolder{

        public RecyclerView section;
        public TextView sectionName;

        public FilterHolder(View itemView) {
            super(itemView);
            section = (RecyclerView)itemView.findViewById(R.id.section);
            section.setNestedScrollingEnabled(false);
            sectionName = (TextView)itemView.findViewById(R.id.sectionName);
        }
    }
}
