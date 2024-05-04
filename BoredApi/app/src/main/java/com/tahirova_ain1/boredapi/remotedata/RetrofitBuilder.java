package com.tahirova_ain1.boredapi;

import com.tahirova_ain1.boredapi.models.DoApi;

public class RetrofitBuilder {
    private static DoApi instance;
    private RetrofitBuilder(){

    }
    public static DoApi getInstance(){
        if (instance == null){
            instance = getInstance();
        }
        return instance;
    }
}
