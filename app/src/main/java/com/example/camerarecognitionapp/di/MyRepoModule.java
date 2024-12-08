package com.example.camerarecognitionapp.di;

import androidx.lifecycle.LiveData;

import com.example.camerarecognitionapp.App;
import com.example.camerarecognitionapp.model.Car;
import com.example.camerarecognitionapp.model.room.CarDao;
import com.example.camerarecognitionapp.repositories.MyRepo;

import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class MyRepoModule {
    @Provides
    public CarDao provideCarDao(){return App.getCarDatabase().getCarDao();}
    @Provides
    public LiveData<List<Car>> provideAllCarCarData(CarDao carDao){
        return carDao.getAllCars();
    }
    @Provides
    public MyRepo provideMyRepo(CarDao carDao,LiveData<List<Car>>allCars){
        return new MyRepo(carDao,allCars);
    }
}
