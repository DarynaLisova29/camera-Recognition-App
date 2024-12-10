package com.example.camerarecognitionapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.camerarecognitionapp.model.Car;
import com.example.camerarecognitionapp.viewModel.AppViewModel;
import com.example.camerarecognitionapp.viewModel.InfoViewModel;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InfoFragment extends Fragment {

    private TextView brandText, yearText, colorText,
            typeText, bodyText, fuelText,
            engineVolumeText, weightText,
            ownerText, addressText;
    private InfoViewModel infoViewModel;
    private AppViewModel appViewModel;
    private ExecutorService executor;
    private Handler handler;

    public static InfoFragment newInstance() {
        InfoFragment fragment = new InfoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_info, container, false);

        init(view);
        initViewModel();
        initLiveData();

        Car car=new Car(appViewModel.getNumberCar().getValue());
        Log.d("Number1", car.getNumberCar());

        executor(car);

        return view;

    }
    private void init(View view){
        brandText=view.findViewById(R.id.brandText);
        yearText=view.findViewById(R.id.yearText);
        colorText=view.findViewById(R.id.colorText);
        typeText=view.findViewById(R.id.typeText);
        bodyText=view.findViewById(R.id.bodeText);
        fuelText=view.findViewById(R.id.fuelText);
        engineVolumeText=view.findViewById(R.id.engineVolumeText);
        weightText=view.findViewById(R.id.weightText);
        ownerText=view.findViewById(R.id.ownerText);
        addressText=view.findViewById(R.id.addressText);

        executor= Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());

    }

    private void initViewModel(){
        infoViewModel=new ViewModelProvider(requireActivity()).get(InfoViewModel.class);
        appViewModel=new ViewModelProvider(requireActivity()).get(AppViewModel.class);
    }

    private void initLiveData(){
        infoViewModel.getInfoCarLiveData().observe(getViewLifecycleOwner(), new Observer<Car>() {
            @Override
            public void onChanged(Car car) {
                if(car!=null) {
                    // TODO: 05.12.2024 rewrite "One value"
                    brandText.setText("1.Марка - модель: " + car.getBrand());
                    yearText.setText("2.Рік: " + car.getYear());
                    colorText.setText("3.Колір: " + car.getColor());
                    typeText.setText("4.Тип ТЗ: " + car.getType());
                    bodyText.setText("5.Кузов: " + car.getBody());
                    fuelText.setText("6.Паливо: " + car.getFuel());
                    engineVolumeText.setText("7.Обєм двигуна: " + car.getEngineVolume());
                    weightText.setText("8.Вага без навантаження: " + car.getWeight());
                    ownerText.setText("9.Власник: " + car.getOwner());
                    addressText.setText("10.Адреса: " + car.getAddress());

                }
            }
        });
    }

    private void executor(Car car){
        executor.execute(() -> {
            try {
                car.setInfo();
                handler.post(() -> {
                    // Оновлення UI
                    Log.d("CarInfo", car.toString());


                    infoViewModel.setValue(car);
                    infoViewModel.insert(car);
                });
            } catch (IOException e) {
                handler.post(() -> {
                    Toast.makeText(requireContext(), "Помилка: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

}