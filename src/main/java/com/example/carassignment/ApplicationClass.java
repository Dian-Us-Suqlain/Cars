package com.example.carassignment;

import android.app.Application;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ApplicationClass extends Application {
    public static ArrayList<Car> cars;

    @NonNull
    @Override
    public void onCreate() {
        super.onCreate();
        cars = new ArrayList<Car>();
        cars.add(new Car("Mercedes","E200","Malik Riaz","12345"));
        cars.add(new Car("Nissan","Almera","Batman","123"));
        cars.add(new Car("Volkswagen","Polo","Altaf Hussain","1234"));
        cars.add(new Car("Nissan","GT","Nawaz Sharif","4321"));
        cars.add(new Car("Mercedes","Kompressor","Allama Iqbal","123456"));
    }
}
