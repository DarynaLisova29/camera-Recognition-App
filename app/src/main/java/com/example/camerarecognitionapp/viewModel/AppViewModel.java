package com.example.camerarecognitionapp.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AppViewModel extends ViewModel {
    private MutableLiveData<String>numberCar=new MutableLiveData<>();

    public MutableLiveData<String> getNumberCar() {
        return numberCar;
    }

    public void setNumberCar(String number) {
        numberCar.setValue(number);
    }
}
