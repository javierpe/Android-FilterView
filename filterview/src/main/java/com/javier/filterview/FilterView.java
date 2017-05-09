package com.javier.filterview;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by usuario on 20/02/17.
 */

public class FilterView extends LinearLayout{

    private String title;
    private ArrayList<Object> filterSections;
    private int backgroundColor;
    private int closeIcon;
    private int titleColor;
    private int toolbarBackgroundColor;
    private int closeIconColor;
    private OnFilterViewResultListener listener;

    private TextView lbTitle;
    private ImageView imgCloseIcon;
    private RecyclerView sections;
    private View topDivisor;
    private LinearLayout mainConteiner;
    private FrameLayout toolbar;

    private OnFilterCanceled onFilterCanceled;

    protected OnFilterCanceled getOnFilterCanceled() {
        return onFilterCanceled;
    }

    public void setOnFilterCanceled(OnFilterCanceled onFilterCanceled) {
        this.onFilterCanceled = onFilterCanceled;
    }

    private boolean toolbarVisible;

    private Dialog dialog;

    public FilterView(Context context, Builder builder) {
        super(context);
        this.title = builder.title;
        this.filterSections = builder.filterSections;
        this.backgroundColor = builder.backgroundColor;
        this.closeIcon = builder.closeIcon;
        this.titleColor = builder.titleColor;
        this.toolbarBackgroundColor = builder.toolbarBackgroundColor;
        this.closeIconColor = builder.closeIconColor;
        this.toolbarVisible = builder.isToolbarVisible;
        init(context);
    }

    public FilterView setOnFilterViewResultListener(OnFilterViewResultListener listener){
        this.listener = listener;
        return this;
    }

    public int getCloseIconColor() {
        return closeIconColor;
    }

    public int getToolbarBackgroundColor() {
        return toolbarBackgroundColor;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Object> getFilterSections() {
        return filterSections;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public int getCloseIcon() {
        return closeIcon;
    }

    public int getTitleColor() {
        return titleColor;
    }

    public boolean isToolbarVisible() {
        return toolbarVisible;
    }

    private void init(Context context){
        LayoutInflater.from(context).inflate(R.layout.filter_view, this, true);
        // Initialize view
        mainConteiner = (LinearLayout)findViewById(R.id.mainContainer);
        lbTitle = (TextView) findViewById(R.id.lbTitle);
        imgCloseIcon = (ImageView)findViewById(R.id.imgCloseIcon);
        sections = (RecyclerView)findViewById(R.id.sections);
        sections.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        toolbar = (FrameLayout)findViewById(R.id.toolbar);

        topDivisor = findViewById(R.id.topDivisor);

        // Set values to properties

        if(!isToolbarVisible()){
            toolbar.setVisibility(GONE);
        }

        if(getToolbarBackgroundColor() != 0){
            toolbar.setBackgroundColor(ContextCompat.getColor(context, getToolbarBackgroundColor()));
        }

        if(getBackgroundColor() != 0) {
            mainConteiner.setBackgroundColor(ContextCompat.getColor(context, getBackgroundColor()));
        }

        lbTitle.setText(getTitle());

        if(getTitleColor() != 0) {
            lbTitle.setTextColor(ContextCompat.getColor(context, getTitleColor()));
        }

        if(getCloseIcon() != 0) {
            Picasso.with(context).load(getCloseIcon()).into(imgCloseIcon);
        }

        if(getFilterSections() != null){
            sections.setAdapter(new FilterAdapter(context, getFilterSections()));
        }

        imgCloseIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelView();
            }
        });

        lbTitle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Iterator<Object> iterator = getFilterSections().iterator();
                JSONArray data = new JSONArray();
                while (iterator.hasNext()){
                    FilterSection section = (FilterSection)iterator.next();
                    if(section.getResult() != null){
                        data.put(section.getResult());
                    }
                }

                if(listener != null){
                    listener.onResult(data);
                }

                cancelView();
            }
        });

        if(getCloseIconColor() != 0){
            imgCloseIcon.setColorFilter(ContextCompat.getColor(context, getCloseIconColor()));
        }

    }

    private void cancelView(){
        if(getOnFilterCanceled() != null){
            getOnFilterCanceled().onCancel();
            if(dialog != null){
                dialog.cancel();
            }
        }
    }

    public LinearLayout getView(){
        return this;
    }

    public void show(){
        dialog = new Dialog(getContext(), android.R.style.Theme_Translucent_NoTitleBar);
        dialog.setContentView(this);
        dialog.show();
    }

    public static class Builder{
        private Context context;
        private String title;
        private ArrayList<Object> filterSections;
        private int backgroundColor;
        private int closeIcon;
        private int titleColor;
        private int divisorColor;
        private int toolbarBackgroundColor;
        private int closeIconColor;
        private boolean isToolbarVisible;

        public Builder(Context context){
            this.context = context;
        }

        public Builder setToolbarBackgroundColor(@ColorRes int color){
            this.toolbarBackgroundColor = color;
            return this;
        }

        public Builder setToolbarVisible(boolean isVisible){
            this.isToolbarVisible = isVisible;
            return this;
        }

        public Builder withDivisorColor(@ColorRes int color){
            this.divisorColor = color;
            return this;
        }

        public Builder withTitle(String title){
            this.title = title;
            return this;
        }

        public Builder addSection(FilterSection section){
            if(filterSections == null){
                filterSections = new ArrayList<>();
            }

            filterSections.add(section);
            return this;
        }

        public Builder withBackgroundColor(@ColorRes int color){
            this.backgroundColor = color;
            return this;
        }

        public Builder setCloseIcon(@DrawableRes int icon){
            this.closeIcon = icon;
            return this;
        }

        public Builder setCloseIconColor(@ColorRes int color){
            this.closeIconColor = color;
            return this;
        }

        public Builder withTitleColor(@ColorRes int color){
            this.titleColor = color;
            return this;
        }

        public FilterView build(){
            return new FilterView(context, this);
        }
    }
}
