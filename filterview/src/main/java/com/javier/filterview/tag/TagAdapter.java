package com.javier.filterview.tag;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adroitandroid.chipcloud.ChipCloud;
import com.adroitandroid.chipcloud.ChipListener;
import com.javier.filterview.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by usuario on 22/02/17.
 */

public class TagAdapter extends RecyclerView.Adapter<TagAdapter.TagHolder>{

    private ArrayList<String> tags = new ArrayList<>();
    private Context context;
    private TagSection section;
    private OnTagListener listener;
    private ArrayList<TagSection> tagSections;

    public TagAdapter(Context context, TagSection section, OnTagListener listener) {
        ArrayList<TagSection> tagSections = new ArrayList<>();
        tagSections.add(section);

        this.context = context;
        this.tagSections = tagSections;
        this.section = section;
        this.listener = listener;
    }

    @Override
    public TagHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.tag_option_item, parent, false);
        return new TagAdapter.TagHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TagHolder holder, int position) {
        final TagSection section = tagSections.get(position);

        ChipCloud.Gravity gravity = ChipCloud.Gravity.LEFT;

        if(section.getGravity().equals(TagGravity.CENTER)){
            gravity = ChipCloud.Gravity.CENTER;
        }else if(section.getGravity().equals(TagGravity.RIGHT)){
            gravity = ChipCloud.Gravity.RIGHT;
        }else if(section.getGravity().equals(TagGravity.STAGGERED)){
            gravity = ChipCloud.Gravity.STAGGERED;
        }

        ChipCloud.Mode mode = ChipCloud.Mode.NONE;

        if(section.getMode().equals(TagMode.MULTI)){
            mode = ChipCloud.Mode.MULTI;
        }else if(section.getMode().equals(TagMode.REQUIRED)){
            mode = ChipCloud.Mode.REQUIRED;
        }else if(section.getMode().equals(TagMode.SINGLE)){
            mode = ChipCloud.Mode.SINGLE;
        }

        new ChipCloud.Configure()
                .chipCloud(holder.chipCloud)
                .selectedColor(ContextCompat.getColor(context, section.getSelectedColor()))
                .selectedFontColor(ContextCompat.getColor(context, section.getSelectedFontColor()))
                .deselectedColor(ContextCompat.getColor(context, section.getDeselectedColor()))
                .deselectedFontColor(ContextCompat.getColor(context, section.getDeselectedFontColor()))
                .labels(section.getLabels())
                .gravity(gravity)
                .mode(mode)
                .chipListener(new ChipListener() {
                    @Override
                    public void chipSelected(int i) {
                        String tag = section.getLabels()[i];
                        if(!tags.contains(tag)){
                            tags.add(tag);
                        }

                        if(listener != null){
                            listener.onTagSelected(tags);
                        }

                        JSONObject data = new JSONObject();
                        JSONArray jsonArray = new JSONArray();

                        try {
                            data.put("control", section.getId());
                            data.put("type", "tags");

                            for(int y = 0; y < tags.size(); y++){
                                jsonArray.put(y, tags.get(y));
                            }

                            data.put("value", jsonArray);
                            section.setResult(data);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void chipDeselected(int i) {
                        tags.remove(section.getLabels()[i]);

                        if(listener != null){
                            listener.onTagSelected(tags);
                        }

                        JSONObject data = new JSONObject();
                        JSONArray jsonArray = new JSONArray();

                        try {
                            data.put("control", section.getId());
                            data.put("type", "tags");

                            for(int y = 0; y < tags.size(); y++){
                                jsonArray.put(y, tags.get(y));
                            }

                            data.put("value", jsonArray);

                            section.setResult(data);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }).build();
    }

    @Override
    public int getItemCount() {
        return tagSections.size();
    }

    class TagHolder extends RecyclerView.ViewHolder{

        public ChipCloud chipCloud;

        public TagHolder(View itemView) {
            super(itemView);
            chipCloud = (ChipCloud)itemView.findViewById(R.id.chip);
        }
    }
}
