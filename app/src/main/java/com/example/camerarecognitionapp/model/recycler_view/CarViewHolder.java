package com.example.camerarecognitionapp.model.recycler_view;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.camerarecognitionapp.R;

public class CarViewHolder extends RecyclerView.ViewHolder {
    TextView numberCar, date;
    public CarViewHolder(@NonNull View itemView) {
        super(itemView);
        date=itemView.findViewById(R.id.dateTextView);
        numberCar=itemView.findViewById(R.id.numberTextView);
    }
}
