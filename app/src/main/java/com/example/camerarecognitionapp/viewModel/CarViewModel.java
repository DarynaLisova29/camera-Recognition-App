package com.example.camerarecognitionapp.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.camerarecognitionapp.MainActivity;
import com.example.camerarecognitionapp.model.Car;
import com.example.camerarecognitionapp.repositories.MyRepo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class CarViewModel extends ViewModel {
    private MutableLiveData<String> licensePlateLiveData = new MutableLiveData<>();


    public LiveData<String> getLicensePlateLiveData() {
        return licensePlateLiveData;
    }

    public void setValue(String number){
        licensePlateLiveData.setValue(number);
    }
    public String getNumber(){
        return licensePlateLiveData.getValue();
    }



}
