package com.example.camerarecognitionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.camerarecognitionapp.model.Car;
import com.example.camerarecognitionapp.viewModel.AppViewModel;
import com.example.camerarecognitionapp.viewModel.InfoViewModel;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InfoActivity extends AppCompatActivity {
    private TextView brandText, yearText, colorText,
                typeText, bodyText, fuelText,
                engineVolumeText, weightText,
                ownerText, addressText;
    private InfoViewModel infoViewModel;
    private AppViewModel appViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        brandText=findViewById(R.id.brandText);
        yearText=findViewById(R.id.yearText);
        colorText=findViewById(R.id.colorText);
        typeText=findViewById(R.id.typeText);
        bodyText=findViewById(R.id.bodeText);
        fuelText=findViewById(R.id.fuelText);
        engineVolumeText=findViewById(R.id.engineVolumeText);
        weightText=findViewById(R.id.weightText);
        ownerText=findViewById(R.id.ownerText);
        addressText=findViewById(R.id.addressText);

        infoViewModel=new ViewModelProvider(this).get(InfoViewModel.class);
        appViewModel=new ViewModelProvider(this).get(AppViewModel.class);



        Car car=new Car(appViewModel.getNumberCar().toString());
        Log.d("Number1", car.getNumberCar());

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            try {
                car.setInfo();
                handler.post(() -> {
                    // Оновлення UI
                    Log.d("CarInfo", car.toString());


                    infoViewModel.setValue(car);
                    // TODO: 05.12.2024 rewrite "One value"
                    brandText.setText("1.Марка - модель: "+infoViewModel.getInfoCarLiveData().getValue().getBrand());
                    yearText.setText("2.Рік випуску: "+infoViewModel.getInfoCarLiveData().getValue().getYear());
                    colorText.setText("3.Колір: "+infoViewModel.getInfoCarLiveData().getValue().getColor());
                    typeText.setText("4.Тип ТЗ: "+infoViewModel.getInfoCarLiveData().getValue().getType());
                    bodyText.setText("5.Кузов: "+infoViewModel.getInfoCarLiveData().getValue().getBody());
                    fuelText.setText("6.Паливо: "+infoViewModel.getInfoCarLiveData().getValue().getFuel());
                    engineVolumeText.setText("7.Обєм двигуна: "+infoViewModel.getInfoCarLiveData().getValue().getEngineVolume());
                    weightText.setText("8.Вага без навантаження: "+infoViewModel.getInfoCarLiveData().getValue().getWeight());
                    ownerText.setText("9.Власник: "+infoViewModel.getInfoCarLiveData().getValue().getOwner());
                    addressText.setText("10.Адреса: "+infoViewModel.getInfoCarLiveData().getValue().getAddress());
                    infoViewModel.insert(car);
                });
            } catch (IOException e) {
                handler.post(() -> {
                    Toast.makeText(this, "Помилка: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        });



//        try {
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }


        infoViewModel.getInfoCarLiveData().observe(this, new Observer<Car>() {
            @Override
            public void onChanged(Car car) {
                if(car!=null) {
                    brandText.setText("1.Мапка - модель: " + car.getBrand());
                    yearText.setText("2.Рік: " + car.getYear());
                    colorText.setText("3.Колір: " + car.getColor());
                    typeText.setText("4.Тип ТЗ: " + car.getType());
                    bodyText.setText("5.Кузов: " + car.getBody());
                    fuelText.setText("6.Паливо: " + car.getFuel());
                    engineVolumeText.setText("7.Обєм двигуна: " + car.getEngineVolume() + " см3");
                    weightText.setText("8.Вага без навантаження: " + car.getWeight() + " кг");
                    ownerText.setText("9.Власник: " + car.getOwner());
                    addressText.setText("10.Адреса: " + car.getAddress());

                }
            }
        });

    }
}