package com.example.camerarecognitionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.camerarecognitionapp.model.Car;
import com.example.camerarecognitionapp.viewModel.AppViewModel;
import com.example.camerarecognitionapp.viewModel.CarViewModel;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gun0912.tedimagepicker.builder.TedImagePicker;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imageView;
    private CardView cardView, photoCard, infoCard,historyCard;
    private TextView textViewResult;
    private TextRecognizer textRecognizer;
    private CarViewModel licensePlateLiveModel;
    private AppViewModel appViewModel;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initTextRecognizer();
        initViewModel();
        initLiveData();

        setClickListeners();

    }
    private void init(){
        imageView=findViewById(R.id.imageView);
        cardView=findViewById(R.id.cameraCard);
        photoCard=findViewById(R.id.photoCard);
        textViewResult=findViewById(R.id.textViewResult);
        infoCard=findViewById(R.id.infoCard);
        historyCard=findViewById(R.id.historyCard);

    }

    private void initTextRecognizer(){
        textRecognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void initViewModel(){
        licensePlateLiveModel=new ViewModelProvider(this).get(CarViewModel.class);
        appViewModel=new ViewModelProvider(this).get(AppViewModel.class);
    }

    private void initLiveData(){
        licensePlateLiveModel.getLicensePlateLiveData().observe(this,
                new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        textViewResult.setText(s);
                    }
                });
    }

    private void setClickListeners(){
        cardView.setOnClickListener(this);
        photoCard.setOnClickListener(this);
        infoCard.setOnClickListener(this);
        historyCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.cameraCard){
            openGalleryCamera(true);


        }else if(view.getId()==R.id.photoCard){
            Log.d("Gallery","Click button photo");
            openGalleryCamera(false);
        }else if(view.getId()==R.id.infoCard){
            Log.d("Click","Click");

            // TODO: 05.12.2024  
            if(textViewResult.getText()!=null&&!textViewResult.getText().equals("Не вдалося розпізнати номерний знак")) {
                InfoFragment infoFragment=InfoFragment.newInstance();
                addFragment(infoFragment);
            }

        }else if(view.getId()==R.id.historyCard){
               HistoryFragment historyFragment=HistoryFragment.newInstance();
               addFragment(historyFragment);
        }
    }

    public void openGalleryCamera(Boolean showCamera){
        TedImagePicker
                .with(this)
                .showCameraTile(showCamera)
                .image()
                .start(uri -> {
                    // Обробка URI отриманого зображення
                    Toast.makeText(this, "Captured Image URI: " + uri.toString(), Toast.LENGTH_SHORT).show();

                    // Завантаження вибраного зображення в ImageView за допомогою Glide
                    Glide.with(this)
                            .load(uri)
                            .into(imageView);
                    // Перетворення URI в Bitmap
                    Bitmap bitmap = null;
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    // Виклик методу для розпізнавання тексту
                    recognizeTextFromImage(bitmap);

                });
    }
    private void recognizeTextFromImage(Bitmap bitmap) {
        // Перетворення Bitmap в InputImage
        InputImage image = InputImage.fromBitmap(bitmap, 0);


        // Обробка зображення та отримання результату
        textRecognizer.process(image)
                .addOnSuccessListener(visionText -> {
                    StringBuilder resultText = new StringBuilder();
                    for (Text.TextBlock block : visionText.getTextBlocks()) {
                        resultText.append(block.getText()).append("\n");
                    }
                    textViewResult.setText(extractLicensePlate(resultText.toString()));
                    licensePlateLiveModel.setValue(extractLicensePlate(resultText.toString()));
                    appViewModel.setNumberCar(extractLicensePlate(resultText.toString()));
                    Log.d("MLKit", "Розпізнаний текст: " + resultText);

                })
                .addOnFailureListener(e -> {
                    Toast.makeText(MainActivity.this, "Помилка розпізнавання тексту", Toast.LENGTH_SHORT).show();
                    Log.e("MLKit", "Помилка розпізнавання тексту: ", e);
                });

    }
    private String extractLicensePlate(String text) {
        String regex = "[A-ZА-Я]{2}\\s?\\d{4}\\s?[A-ZА-Я]{2}|\\d{3}-\\d{2}\\s?[A-ZА-Я]{2}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return matcher.group(); // Повертає знайдений номер
        }
        return "Не вдалося розпізнати номерний знак";
    }

    private void addFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, fragment, fragment.getClass().getName())
                .addToBackStack(fragment.getClass().getName())
                .commit();
    }
}
