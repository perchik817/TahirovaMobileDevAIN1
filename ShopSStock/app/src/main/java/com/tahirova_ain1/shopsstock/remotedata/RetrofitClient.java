package com.tahirova_ain1.shopsstock.remotedata;

import com.tahirova_ain1.stock.constant.ConstantApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient instance = null;
    private Api api;

    private void RetrofitBuilder() {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(ConstantApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(Api.class);
    }
    public static synchronized RetrofitClient getInstance(){
        if(instance==null){
            instance = new RetrofitClient();
        }
        return instance;
    }
    public Api getApi(){
        return null;
    }
}
