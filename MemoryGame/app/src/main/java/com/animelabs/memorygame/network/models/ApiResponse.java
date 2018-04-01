package com.animelabs.memorygame.network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by asheeshsharma on 01/04/18.
 */

public class ApiResponse {

    @SerializedName("title")
    private String title;
    @SerializedName("link")
    private String link;
    @SerializedName("description")
    private String description;
    @SerializedName("modified")
    private String modified;
    @SerializedName("generator")
    private String generator;
    @SerializedName("items")
    private List<ImageItems> items = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getGenerator() {
        return generator;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public List<ImageItems> getItems() {
        return items;
    }

    public void setItems(List<ImageItems> items) {
        this.items = items;
    }

}
