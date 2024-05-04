package com.tahirova_ain1.weatherapi.models;

import com.google.gson.annotations.SerializedName;

public class Clouds {
    @SerializedName("all")
    private int all;

    public int getAll() {
        return all;
    }
}
