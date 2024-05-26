package com.tahirova_ain1.shopsstock.remotedata;

import com.tahirova_ain1.shopsstock.models.Order;
import com.tahirova_ain1.shopsstock.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("api/v1/auth/users")
    Call<List<User>> getAllUsers();
    @GET("api/v1/order/all")
    Call<List<Order>> getAllOrders();
}
