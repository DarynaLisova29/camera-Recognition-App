package com.example.camerarecognitionapp.model.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.camerarecognitionapp.model.Car;

import java.util.List;

@Dao
public interface CarDao {
    @Insert
    void insert(Car car);

    @Update
    void update(Car car);

    @Delete
    void delete(Car car);

    @Query("Select * from carsQuery")
    LiveData<List<Car>> getAllCars();

}
