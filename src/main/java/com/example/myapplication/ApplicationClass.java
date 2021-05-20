package com.example.myapplication;

import android.app.Application;

import java.util.ArrayList;

public class ApplicationClass extends Application {
    static ArrayList<Car> cars;

    @Override
    public void onCreate() {
        super.onCreate();
        cars = new ArrayList<Car>();
        cars.add(new Car("Mercedes",   "E200",   "Malik Riaz",   "12345678"));
        cars.add(new Car("Nissan",     "Almera", "Batman",        "87654321"));
        cars.add(new Car("Volkswagen", "Polo",   "Altaf Hussain", "12348765"));
        cars.add(new Car("Nissan",     "GT", "Nawaz Sharif",  "43215678"));
        cars.add(new Car("Mercedes",   "Kompressor",   "Allama Iqbal",  "18273645"));
    }
}

