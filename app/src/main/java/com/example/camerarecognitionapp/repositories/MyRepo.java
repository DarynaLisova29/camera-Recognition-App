package com.example.camerarecognitionapp.repositories;

import android.database.Observable;

import androidx.lifecycle.LiveData;

import com.example.camerarecognitionapp.App;
import com.example.camerarecognitionapp.model.Car;
import com.example.camerarecognitionapp.model.room.CarDao;
import com.example.camerarecognitionapp.model.room.CarDatabase;

import java.util.List;

public class MyRepo {
    private CarDao carDao;
    private LiveData<List<Car>> allCars;

    public MyRepo(){
        carDao= App.getCarDatabase().getCarDao();
        allCars=carDao.getAllCars();
    }

//    public Observable<Car>getInfoCar()
    public LiveData<List<Car>>getAllCars(){
        return allCars;
    }

    public void insert(Car car){
        CarDatabase.databaseWriteExecutor.execute(()->{
            carDao.insert(car);
        });
    }
}
