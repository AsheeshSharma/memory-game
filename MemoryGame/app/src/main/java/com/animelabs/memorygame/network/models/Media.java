package com.animelabs.memorygame.network.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by asheeshsharma on 01/04/18.
 * Media
 */

public class Media {

    @SerializedName("m")
    private String m;

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

}