package com.example.camerarecognitionapp.model.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.camerarecognitionapp.model.Car;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Car.class}, version = 1, exportSchema = false)
public abstract class CarDatabase extends RoomDatabase {
    private static CarDatabase instance;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public abstract CarDao getCarDao();


}
