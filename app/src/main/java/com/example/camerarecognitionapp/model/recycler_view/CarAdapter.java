package com.example.camerarecognitionapp.model.recycler_view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.camerarecognitionapp.R;
import com.example.camerarecognitionapp.model.Car;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarViewHolder> {
    List<Car> carList;

    public CarAdapter(List<Car> carList) {
        this.carList = carList;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       return new CarViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item,
               parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        holder.date.setText(carList.get(position).getDate());
        holder.numberCar.setText(carList.get(position).getNumberCar());
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }
}
