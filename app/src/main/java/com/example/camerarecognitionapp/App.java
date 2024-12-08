package com.example.camerarecognitionapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.camerarecognitionapp.di.AppComponent;
import com.example.camerarecognitionapp.di.DaggerAppComponent;
import com.example.camerarecognitionapp.di.MyRepoModule;
import com.example.camerarecognitionapp.model.room.CarDatabase;

public class App extends Application {
    private static CarDatabase carDatabase;
    public static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        carDatabase= Room.databaseBuilder(getApplicationContext(),
                CarDatabase.class,"carDB").fallbackToDestructiveMigration().build();

//        appComponent = DaggerAppComponent.builder().myRepoModule(new MyRepoModule()).build();
        appComponent= DaggerAppComponent.builder().myRepoModule(new MyRepoModule()).build();

    }

    public static CarDatabase getCarDatabase(){
        return carDatabase;
    }
}
