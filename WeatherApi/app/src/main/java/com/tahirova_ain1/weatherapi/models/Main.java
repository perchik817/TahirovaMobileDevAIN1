package com.tahirova_ain1.weatherapi.models;

import com.google.gson.annotations.SerializedName;

public class Main {

    @SerializedName("name")
    private String name;
    @SerializedName("temp")
    private Double temp;
    @SerializedName("temp_max")
    private Double tempMax;

    @SerializedName("temp_min")
    private Double tempMin;
    @SerializedName("humidity")
    private int humidity;
    @SerializedName("pressure")
    private int pressure;


    public int getHumidity() {
        return humidity;
    }

    public int getPressure() {
        return pressure;
    }

    public Double getTemp() {
        return temp;
    }

    public String getName() {
        return name;
    }

    public Double getTempMax() {
        return tempMax;
    }

    public Double getTempMin() {
        return tempMin;
    }
}
