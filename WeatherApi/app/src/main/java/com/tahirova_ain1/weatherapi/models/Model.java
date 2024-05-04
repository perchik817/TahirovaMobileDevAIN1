package com.tahirova_ain1.weatherapi.models;

import com.google.gson.annotations.SerializedName;

public class Model {
    @SerializedName("main")
    Main main;

    @SerializedName("wind")
    Wind wind;

    @SerializedName("clouds")
    Clouds clouds;

    @SerializedName("sys")
    Sys sys;

    @SerializedName("timezone")
    long timezone;

    public long getTimezone() {
        return timezone;
    }

    public Main getMain() {
        return main;
    }

    public Wind getWind() {
        return wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public Sys getSys() {
        return sys;
    }
}
