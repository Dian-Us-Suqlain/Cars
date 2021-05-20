package com.example.carassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity implements CarAdapter.CarSelected {

    Button btnCar, btnOwner, btnNewCar, btnDone;
    ImageView ivCarImage;
    TextView tvCarModel, tvOwnerInfo, tvOwnerName, tvPhone;
    FragmentManager fragmentManager;
    Fragment ListFragment, ButtonFragment, CarFragment, OwnerFragment, NewCarFragment;
    ListFrag listFrag;
    EditText etCarName, etCarModel, etOwnerName, etOwnerPhone;

    String car_name, car_model, owner_name, owner_number;
    private static final String FILE_NAME = "MyCars.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent recIntent = getIntent();

        //Fragment New Car
        etCarName = findViewById(R.id.et_car_name);
        etCarModel = findViewById(R.id.et_car_model);
        etOwnerName = findViewById(R.id.et_owner_name);
        etOwnerPhone = findViewById(R.id.et_phone);

        //Fragment Button
        btnCar = findViewById(R.id.car_button);
        btnOwner = findViewById(R.id.owner_button);
        btnNewCar = findViewById(R.id.add_button);
        btnDone = findViewById(R.id.done_button);

        //Fragment Car Information
        ivCarImage = findViewById(R.id.iv_car_image);
        tvCarModel = findViewById(R.id.tv_car_model);

        //Fragment Owner Information
        tvOwnerInfo = findViewById(R.id.tv_owner_info);
        tvOwnerName = findViewById(R.id.tv_owner_name);
        tvPhone = findViewById(R.id.tv_phone);

        fragmentManager = getSupportFragmentManager();
        listFrag = (ListFrag) fragmentManager.findFragmentById(R.id.list_frag);

        ListFragment = fragmentManager.findFragmentById(R.id.list_frag);
        ButtonFragment = fragmentManager.findFragmentById(R.id.button_frag);
        CarFragment = fragmentManager.findFragmentById(R.id.car_info_frag);
        OwnerFragment = fragmentManager.findFragmentById(R.id.owner_info_frag);
        NewCarFragment = fragmentManager.findFragmentById(R.id.new_car_frag);

        fragmentManager.beginTransaction().show(ListFragment).show(ButtonFragment).show(CarFragment).hide(OwnerFragment).hide(NewCarFragment).commit();

        btnCar.setOnClickListener(new View.OnClickListener() {  // This function would only show the Car Information Fragment
            // and will hide the Owner Information Fragment
            @Override
            public void onClick(View view) {    // The Car Info Button will show only the Car Information Fragment,
                                                // and will hide the Owner Info Fragment
                fragmentManager.beginTransaction().show(CarFragment).hide(NewCarFragment).hide(OwnerFragment).commit();
                Toast.makeText(MainActivity.this, "Car related information displayed", Toast.LENGTH_SHORT).show();
            }
        });

        btnOwner.setOnClickListener(new View.OnClickListener() {  // This function would only show the Owner Information Fragment
            // and will hide the Car Information Fragment
            @Override
            public void onClick(View view) {  // The Owner Info Button will show only the Owner Information Fragment,
                                                // and will hide the Car Info Fragment
                fragmentManager.beginTransaction().hide(CarFragment).hide(NewCarFragment).show(OwnerFragment).commit();
                Toast.makeText(MainActivity.this, "Owner related information displayed", Toast.LENGTH_SHORT).show();
            }
        });

        btnNewCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction().show(NewCarFragment).hide(CarFragment).hide(OwnerFragment).hide(ButtonFragment).commit();
            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {  // To add a new new Car and Owner of that car
            @Override
            public void onClick(View view) {

                try {
                    FileOutputStream file = openFileOutput(FILE_NAME,MODE_PRIVATE);
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(file);

                    for (int i = 0; i < ApplicationClass.cars.size(); i++) {
                        outputStreamWriter.write(ApplicationClass.cars.get(i).getCarName() + ","
                                + ApplicationClass.cars.get(i).getCarModel() + ","
                                + ApplicationClass.cars.get(i).getOwnerName() + ","
                                + ApplicationClass.cars.get(i).getOwnerPhone() + "\n");
                    }

                    outputStreamWriter.flush();
                    outputStreamWriter.close();

                    Toast.makeText(MainActivity.this, "Data successfully saved to file!", Toast.LENGTH_SHORT).show();
                }
                catch (IOException e) {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                car_name = etCarName.getText().toString().trim();
                car_model = etCarModel.getText().toString().trim();
                owner_name = etOwnerName.getText().toString().trim();
                owner_number = etOwnerPhone.getText().toString().trim();

                ApplicationClass.cars.add(new Car(car_name, car_model, owner_name, owner_number));

                listFrag.notifyDataChanged();

                etCarName.setText("");
                etCarModel.setText("");
                etOwnerName.setText("");
                etOwnerPhone.setText("");

                fragmentManager.beginTransaction()
                        .show(ListFragment)
                        .show(ButtonFragment)
                        .show(CarFragment)
                        .hide(OwnerFragment)
                        .hide(NewCarFragment)
                        .commit();

                Toast.makeText(MainActivity.this, "New Car Added to List", Toast.LENGTH_SHORT).show();
            }
        });

        onItemClicked(0);  //Initially the first item in the list will be displayed every time
        LoadCarData();
    }

    @Override
    public void onItemClicked(int index) {

        //To set Images on Car Information Fragment, we have implemented the below conditions
        switch (ApplicationClass.cars.get(index).getCarName()) {
            case "Volkswagen":
                ivCarImage.setImageResource(R.drawable.volkswagen);
                Toast.makeText(MainActivity.this, "Volkswagen Car", Toast.LENGTH_SHORT).show();
                break;
            case "Nissan":
                ivCarImage.setImageResource(R.drawable.nissan);
                Toast.makeText(MainActivity.this, "Nissan Car", Toast.LENGTH_SHORT).show();
                break;
            case "Mercedes":
                ivCarImage.setImageResource(R.drawable.mercedes);
                Toast.makeText(MainActivity.this, "Mercedes Car", Toast.LENGTH_SHORT).show();
                break;
            default:
                ivCarImage.setImageResource(R.drawable.car_new);  //If the new car name is not in the list, then show this image

                break;
        }

        //Attaching the respective TextViews with their implementations
        tvCarModel.setText(ApplicationClass.cars.get(index).getCarModel());
        tvOwnerInfo.setText(ApplicationClass.cars.get(index).getCarModel());
        tvOwnerName.setText(ApplicationClass.cars.get(index).getOwnerName());
        tvPhone.setText(ApplicationClass.cars.get(index).getOwnerPhone());
    }

    public void LoadCarData() {
        ApplicationClass.cars.clear();
        File file = getApplication().getFileStreamPath(FILE_NAME);
        String read_data_by_line;

        if(file.exists()) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openFileInput(FILE_NAME)));

                while ((read_data_by_line = bufferedReader.readLine()) != null) {
                    StringTokenizer stringTokenizer = new StringTokenizer(read_data_by_line, ",");
                    if(stringTokenizer.hasMoreTokens()) {
                        ApplicationClass.cars.add(new Car(stringTokenizer.nextToken(),
                                stringTokenizer.nextToken(),
                                stringTokenizer.nextToken(),
                                stringTokenizer.nextToken()));
                    }
                }

                bufferedReader.close();
            }
            catch (IOException e) {
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
