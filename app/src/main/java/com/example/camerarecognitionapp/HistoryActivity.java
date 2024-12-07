package com.example.camerarecognitionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.camerarecognitionapp.model.Car;
import com.example.camerarecognitionapp.model.recycler_view.CarAdapter;
import com.example.camerarecognitionapp.viewModel.HistoryViewModel;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private RecyclerView historyRecyclerView;
    private HistoryViewModel historyViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        historyRecyclerView=findViewById(R.id.recycler);
        // TODO: 05.12.2024 need to add to xml.file
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(HistoryActivity.this));
        initViewModel();
        initLiveData();

    }

    private void initViewModel(){
        historyViewModel=new ViewModelProvider(this).get(HistoryViewModel.class);

    }

    private void initLiveData(){
        historyViewModel.getHistoryCar().observe(this, new Observer<List<Car>>() {
            @Override
            public void onChanged(List<Car> cars) {
                historyRecyclerView.setAdapter(new CarAdapter(cars));
            }
        });
    }
}