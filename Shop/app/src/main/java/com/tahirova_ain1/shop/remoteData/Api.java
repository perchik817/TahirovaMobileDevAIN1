package com.tahirova_ain1.shop.remoteData;

import com.tahirova_ain1.shop.models.ModelM;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("products")
    Call<List<ModelM>> getStoreProducts();
}
