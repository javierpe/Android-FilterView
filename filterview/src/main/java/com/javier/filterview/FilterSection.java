package com.javier.filterview;

import org.json.JSONObject;

/**
 * Created by usuario on 20/02/17.
 */

public class FilterSection {
    private String sectionName;
    private int sectionNameColor;
    private int id;
    private JSONObject result;


    public FilterSection(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public JSONObject getResult() {
        if(result == null){
            result = new JSONObject();
        }
        return result;
    }

    public void setResult(JSONObject result) {
        this.result = result;
    }

    public String getSectionName() {
        String sName = "";
        if(sectionName != null){
            sName = sectionName.toUpperCase();
        }
        return sName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public int getSectionNameColor() {
        return sectionNameColor;
    }

    public void setSectionNameColor(int sectionNameColor) {
        this.sectionNameColor = sectionNameColor;
    }
}
