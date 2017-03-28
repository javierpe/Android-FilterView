package com.javier.filterview.single;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.javier.filterview.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by usuario on 21/02/17.
 */

public class SingleSectionAdapter extends RecyclerView.Adapter<SingleSectionAdapter.SingleSectionHolder>{

    private Context context;
    private ArrayList<SingleOption> options;
    private SingleSection section;
    private OnSingleOptionListener listener;

    public SingleSectionAdapter(Context context,  SingleSection section, OnSingleOptionListener listener) {
        this.context = context;
        this.options = section.getOptions();
        this.listener = listener;
        this.section = section;
    }

    @Override
    public SingleSectionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.single_option_item, parent, false);
        return new SingleSectionAdapter.SingleSectionHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SingleSectionHolder holder, final int position) {
        final SingleOption option = options.get(position);

        if(option.getDefaultColor() != 0) {
            holder.btSingleButton.setBackgroundColor(option.getDefaultColor());
            holder.btSingleButton.setGhost(false);
        }else{
            holder.btSingleButton.setGhost(true);
        }

        if(option.getBorderColor() != 0){
            holder.btSingleButton.setBorderColor(ContextCompat.getColor(context, option.getBorderColor()));
        }else{
            holder.btSingleButton.setBorderColor(Color.parseColor("#FFFFFF"));
        }

        if(option.getBorderWidth() != 0){
            holder.btSingleButton.setBorderWidth(option.getBorderWidth());
        }else{
            holder.btSingleButton.setBorderWidth(2);
        }

        if(option.getFocusColor() != 0){
            holder.btSingleButton.setFocusBackgroundColor(ContextCompat.getColor(context, option.getFocusColor()));
        }else{
            holder.btSingleButton.setFocusBackgroundColor(Color.parseColor("#FFFFFF"));
        }

        if(option.getIcon() != 0){
            holder.btSingleButton.setIconResource(option.getIcon());
            holder.lbTitle.setText(option.getTitle().toUpperCase());
            holder.lbTitle.setVisibility(View.VISIBLE);
        }else{
            holder.btSingleButton.setText(option.getTitle());
            holder.lbTitle.setVisibility(View.GONE);
        }

        if(option.getTitleColor() != 0){
            holder.lbTitle.setTextColor(ContextCompat.getColor(context, option.getTitleColor()));
        }else{
            holder.lbTitle.setTextColor(Color.parseColor("#FFFFFF"));
        }

        if(!option.isEnabled()){
            if(option.getDeselectedIconColor() != 0) {
                holder.btSingleButton.getIconImageObject()
                        .setColorFilter(ContextCompat.getColor(context, option.getDeselectedIconColor()));
            }
        }

        holder.btSingleButton.setGhost(!option.isEnabled());
        holder.btSingleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                options.get(position).setEnabled(!option.isEnabled());

                Iterator<SingleOption> optionIterator = options.iterator();
                while (optionIterator.hasNext()){
                    SingleOption option1 = optionIterator.next();
                    if(option1.getTitle() != option.getTitle()){
                        option1.setEnabled(false);
                    }
                }

                notifyDataSetChanged();

                if(option.isEnabled()) {

                    if(option.getSelectedIconColor() != 0) {
                        holder.btSingleButton.getIconImageObject()
                                .setColorFilter(ContextCompat.getColor(context, option.getSelectedIconColor()));
                    }

                    if (listener != null) {

                        JSONObject data = new JSONObject();
                        try {
                            data.put("section", section.getId());
                            data.put("type", "single_option");
                            data.put("value", option.getTitle());
                            section.setResult(data);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        listener.onClick(option);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return options.size();
    }

    class SingleSectionHolder extends RecyclerView.ViewHolder{

        private FancyButton btSingleButton;
        private TextView lbTitle;

        public SingleSectionHolder(View itemView) {
            super(itemView);
            btSingleButton = (FancyButton)itemView.findViewById(R.id.btSingleButton);
            lbTitle = (TextView)itemView.findViewById(R.id.lbTitle);
        }
    }
}
