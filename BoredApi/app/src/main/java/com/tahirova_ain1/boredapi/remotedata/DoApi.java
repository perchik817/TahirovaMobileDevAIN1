package com.tahirova_ain1.boredapi.models;

import com.tahirova_ain1.boredapi.remotedata.DoModel;

import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DoApi {
    @GET("api/activity")
    Call<DoModel> getActivities();

    @GET("/api/activity/{key}")
    Call<DoModel> getActivitiesByKey(
    @Query("key") String key);

    @GET("/api/activity?price")
    Call<DoModel> getActivitiesByPrice(
            @Query("price") double price);

    @GET("/api/activity?link")
    Call<DoModel> getActivitiesByLink(
            @Query("link") String link);
}
