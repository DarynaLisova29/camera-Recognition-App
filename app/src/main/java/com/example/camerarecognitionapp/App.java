package com.example.camerarecognitionapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.camerarecognitionapp.model.room.CarDatabase;

public class App extends Application {
    private static CarDatabase carDatabase;
    public static RecyclerView recyclerView;

    RoomDatabase.Callback myCallback=new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }

        @Override
        public void onDestructiveMigration(@NonNull SupportSQLiteDatabase db) {
            super.onDestructiveMigration(db);
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        carDatabase= Room.databaseBuilder(getApplicationContext(),
                CarDatabase.class,"carDB").fallbackToDestructiveMigration().build();

    }

    public static CarDatabase getCarDatabase(){
        return carDatabase;
    }
}
