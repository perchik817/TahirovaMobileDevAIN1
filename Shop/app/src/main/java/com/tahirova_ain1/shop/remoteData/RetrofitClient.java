package com.tahirova_ain1.shop.remoteData;

import com.tahirova_ain1.shop.constant.ConstantApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient instance = null;
    private Api api;

    // Private constructor to prevent instantiation from outside
    private RetrofitClient() {
        RetrofitBuilder();
    }

    // Method to initialize Retrofit and create API service
    private void RetrofitBuilder() {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(ConstantApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(Api.class);
    }

    // Method to get instance of RetrofitClient
    public static synchronized RetrofitClient getInstance(){
        if(instance==null){
            instance = new RetrofitClient();
        }
        return instance;
    }

    // Method to get the API service
    public Api getApi(){
        return api;
    }
}

//package com.tahirova_ain1.shop.remoteData;
//
//import com.tahirova_ain1.shop.constant.ConstantApi;
//
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class RetrofitClient {
//    private static RetrofitClient instance = null;
//    private Api api;
//
//    private void RetrofitBuilder() {
//        Retrofit retrofit = new Retrofit
//                .Builder()
//                .baseUrl(ConstantApi.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        api = retrofit.create(Api.class);
//    }
//    public static synchronized RetrofitClient getInstance(){
//        if(instance==null){
//            instance = new RetrofitClient();
//        }
//        return instance;
//    }
//    public Api getApi(){
//        return api;
//    }
//}
