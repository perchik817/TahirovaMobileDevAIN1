package com.tahirova_ain1.shop.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tahirova_ain1.shop.models.ModelM;
import com.tahirova_ain1.shop.repos.JemRepo;

import java.util.List;

public class HomeViewModel extends ViewModel {
private JemRepo jemRepo;
private LiveData<List<ModelM>> modelMResponseLiveData;
    public HomeViewModel() {
        jemRepo = new JemRepo();
        modelMResponseLiveData = jemRepo.getDashJeminyList();
    }

    public LiveData<List<ModelM>> getModelMResponseLiveData() {
        return modelMResponseLiveData;
    }
}