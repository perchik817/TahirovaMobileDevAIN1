package com.tahirova_ain1.weatherapi.remotedata;

import com.tahirova_ain1.weatherapi.models.Model;
import com.tahirova_ain1.weatherapi.models.WeatherModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    public static String URL = "9a3ed03a30e7ef4326c5ff957a5b90ca";
    @GET("data/2.5/weather")
    Call<Model> getCurrentWeather(
            @Query("q") String name,
            @Query("appid") String key);

    @GET("data/2.5/weather?&appid=9a3ed03a30e7ef4326c5ff957a5b90ca")
    Call<WeatherModel> getWeather(
            @Query("q") String name,
            @Query("appid") String key
    );

    String url = "api.openweathermap.org/data/2.5/weather?q=Bishkek&appid=9a3ed03a30e7ef4326c5ff957a5b90ca";
}
