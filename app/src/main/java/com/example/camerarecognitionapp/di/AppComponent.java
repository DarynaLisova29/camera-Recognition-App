package com.example.camerarecognitionapp.di;

import com.example.camerarecognitionapp.viewModel.HistoryViewModel;
import com.example.camerarecognitionapp.viewModel.InfoViewModel;
import dagger.Component;

@Component(modules = MyRepoModule.class)
public interface AppComponent {
    void inject(HistoryViewModel historyViewModel);
    void inject(InfoViewModel infoViewModel);
}
