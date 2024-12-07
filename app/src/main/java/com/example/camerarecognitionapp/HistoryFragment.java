package com.example.camerarecognitionapp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.camerarecognitionapp.model.Car;
import com.example.camerarecognitionapp.model.recycler_view.CarAdapter;
import com.example.camerarecognitionapp.viewModel.HistoryViewModel;

import java.util.List;

public class HistoryFragment extends Fragment {

    private RecyclerView historyRecyclerView;
    private HistoryViewModel historyViewModel;

    public static HistoryFragment newInstance() {
        HistoryFragment fragment = new HistoryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_history, container, false);

        historyRecyclerView=view.findViewById(R.id.recycler);
        // TODO: 05.12.2024 need to add to xml.file
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        initViewModel();
        initLiveData();

        return view;
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