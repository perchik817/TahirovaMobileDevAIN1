package com.tahirova_ain1.weatherapi.models;

import android.content.Intent;

import com.google.gson.annotations.SerializedName;

public class WeatherModel {

    @SerializedName("icon")
    private String icon;

    @SerializedName("code")
    private Integer code;
    public String getIcon() {
        return icon;
    }

    public Integer getCode() {
        return code;
    }

}
