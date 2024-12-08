package com.example.camerarecognitionapp.viewModel;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.camerarecognitionapp.App;
import com.example.camerarecognitionapp.di.AppComponent;
import com.example.camerarecognitionapp.model.Car;
import com.example.camerarecognitionapp.model.recycler_view.CarAdapter;
import com.example.camerarecognitionapp.repositories.MyRepo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class HistoryViewModel extends ViewModel {

    MutableLiveData<List<Car>> historyCarLiveData= new MutableLiveData<>();
    @Inject
    MyRepo myRepo;
    LiveData <List<Car>> getHistoryCar;

    public HistoryViewModel() {
        //Dagger 2 Inject
        App.appComponent.inject(this);
        getHistoryCar = myRepo.getAllCars();
    }


    public LiveData<List<Car>> getGetHistoryCar() {
        return getHistoryCar;
    }

    public List<Car> getCar(){
        return historyCarLiveData.getValue();
    }
//    public void setValue(Car car) {
//        infoCarLiveData.setValue(car);
//    }
    public LiveData<List<Car>> getHistoryCar(){return getHistoryCar;}

}
