package com.tahirova_ain1.shop.repos;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tahirova_ain1.shop.models.ModelM;
import com.tahirova_ain1.shop.remoteData.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JemRepo {

    final MutableLiveData<List<ModelM>> data = new MutableLiveData<>();
    public LiveData<List<ModelM>> getDashJeminyList(){
        Call<List<ModelM>> apiCall = RetrofitClient.getInstance().getApi().getStoreProducts();

        apiCall.enqueue(new Callback<List<ModelM>>() {
            @Override
            public void onResponse(Call<List<ModelM>> call, Response<List<ModelM>> response) {
                if(response.body() != null)
                    data.postValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<ModelM>> call, Throwable throwable) {
                Log.e("tag", "no data" + throwable.getLocalizedMessage());
                data.postValue(null);
            }
        });
        return data;
    }
}
