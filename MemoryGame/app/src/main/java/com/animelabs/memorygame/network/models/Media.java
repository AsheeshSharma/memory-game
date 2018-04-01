package com.animelabs.memorygame.network.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by asheeshsharma on 01/04/18.
 * Media
 */

public class Media implements Serializable{

    @SerializedName("m")
    private String m;

    public Media(String m) {
        this.m = m;
    }

    public String getM() {
        return m;
    }

    public Media() {
    }

    public void setM(String m) {
        this.m = m;
    }

}