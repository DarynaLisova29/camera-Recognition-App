package com.example.camerarecognitionapp.model;

import android.util.Log;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


import java.io.IOException;
@Entity(tableName = "carsQuery")
public class Car {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String numberCar;
    private String brand;
    private int year;
    private String color;
    private String type;
    private String body;
    private String fuel;
    private String engineVolume;
    private String weight;
    private String owner;
    private String address;

    private String date;

    public Car(String numberCar) {
        this.numberCar = numberCar;
    }
    public void setInfo() throws IOException {
        Document doc= Jsoup.connect("https://ukr.zone/uk/gos-nomer/?q="+numberCar).get();
        Elements element1= doc.select("div.right");
        for(int i=1; i<11;i++){
            Log.d("LogInfo",element1.get(i).text());
        }

        if(isInteger(element1.get(2).text())) {
            brand = element1.get(1).text();
            year = Integer.parseInt(element1.get(2).text());
            color = element1.get(3).text();
            type = element1.get(4).text();
            body = element1.get(5).text();
            fuel = element1.get(6).text();
            engineVolume = element1.get(7).text();
            weight = element1.get(8).text();
            owner = element1.get(9).text();
            address = element1.get(10).text();
        }else{
            brand="";
            year=-1;
            color="";
            type="";
            body="";
            fuel="";
            engineVolume="";
            weight="";
            owner="";
            address="";
        }

    }
    public boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true; // Це дійсне ціле число
        } catch (NumberFormatException e) {
            return false; // Не ціле число
        }
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumberCar() {
        return numberCar;
    }

    public void setNumberCar(String numberCar) {
        this.numberCar = numberCar;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getEngineVolume() {
        return engineVolume;
    }

    public void setEngineVolume(String engineVolume) {
        this.engineVolume = engineVolume;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Car{" +
                "numberCar='" + numberCar + '\'' +
                ", brand='" + brand + '\'' +
                ", year=" + year +
                ", color='" + color + '\'' +
                ", type='" + type + '\'' +
                ", body='" + body + '\'' +
                ", engineVolume='" + engineVolume + '\'' +
                ", weight='" + weight + '\'' +
                ", owner='" + owner + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
