package com.tahirova_ain1.boredapi.models;

import com.google.gson.annotations.SerializedName;

public class DoModel {
    @SerializedName("key")
    private String key;

    @SerializedName("activity")
    private String activity;

    @SerializedName("price")
    private String price;

    @SerializedName("link")
    private String link;

    @SerializedName("participants")
    private String participants;

    @SerializedName("category")
    private String category;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
