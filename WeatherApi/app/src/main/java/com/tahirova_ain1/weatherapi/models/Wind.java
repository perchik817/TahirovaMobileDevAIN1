package com.tahirova_ain1.weatherapi.models;

import com.google.gson.annotations.SerializedName;

public class Wind {
    @SerializedName("speed")
    private double speed;

    public double getSpeed() {
        return speed;
    }
}
