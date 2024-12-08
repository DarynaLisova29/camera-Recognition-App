package com.example.camerarecognitionapp.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.camerarecognitionapp.App;
import com.example.camerarecognitionapp.model.Car;
import com.example.camerarecognitionapp.repositories.MyRepo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

public class InfoViewModel extends ViewModel {
    MutableLiveData<Car> infoCarLiveData= new MutableLiveData<>();
    @Inject
    MyRepo myRepo;

    public InfoViewModel() {
        App.appComponent.inject(this);
    }

    public MutableLiveData<Car>getInfoCarLiveData(){
        return  infoCarLiveData;
    }

    public Car getCar(){
        return infoCarLiveData.getValue();
    }
    public void setValue(Car car) {
        infoCarLiveData.setValue(car);
    }

    public void insert(Car car){
        // Отримати поточну дату і час
        Date now = new Date();

        // Форматувати дату у String
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm dd.MM.yyyy");
        String dateTimeString = formatter.format(now);
        car.setDate(dateTimeString);
        myRepo.insert(car);
    }
}
