package com.example.myapplication;

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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CarAdapter.CarSelected{

    Button btnCar, btnOwner, btnNewCar, btnDone;
    ImageView ivCarImage;
    TextView tvCarModel, tvOwnerInfo, tvOwnerName, tvPhone;
    FragmentManager fragmentManager;
    Fragment ListFragment, ButtonFragment, CarFragment, OwnerFragment, NewCarFragment;
    ListFragment listFragment;
    EditText etCarName, etCarModel, etOwnerName, etOwnerPhone;

    ArrayList<Car> cars;  // List of cars
    private static final String FILE_NAME = "Cars.txt"; //  Name of file

    String car_name, car_model, owner_name, owner_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        listFragment = (ListFragment) fragmentManager.findFragmentById(R.id.list_fragment);

        ListFragment = fragmentManager.findFragmentById(R.id.list_fragment);
        ButtonFragment = fragmentManager.findFragmentById(R.id.button_fragment);
        CarFragment = fragmentManager.findFragmentById(R.id.car_info_fragment);
        OwnerFragment = fragmentManager.findFragmentById(R.id.owner_info_fragment);
        NewCarFragment = fragmentManager.findFragmentById(R.id.new_car_fragment);

        //Intent recIntent = getIntent();

        fragmentManager.beginTransaction().show(ListFragment).show(ButtonFragment).show(CarFragment).hide(OwnerFragment).hide(NewCarFragment).commit();

        btnCar.setOnClickListener(new View.OnClickListener() {  // This function would only show the Car Information Fragment
            // and will hide the Owner Information Fragment
            @Override
            public void onClick(View view) {  // The Car Info Button will show only the Car Information Fragment,
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

                car_name = etCarName.getText().toString().trim();
                car_model = etCarModel.getText().toString().trim();
                owner_name = etOwnerName.getText().toString().trim();
                owner_number = etOwnerPhone.getText().toString().trim();

                ApplicationClass.cars.add(new Car(car_name, car_model, owner_name, owner_number));

                listFragment.notifyDataChanged();

                etCarName.setText("");
                etCarModel.setText("");
                etOwnerName.setText("");
                etOwnerPhone.setText("");

                fragmentManager.beginTransaction().show(ListFragment).show(ButtonFragment).show(CarFragment).hide(OwnerFragment).hide(NewCarFragment).commit();
                Toast.makeText(MainActivity.this, "New Car Added to List", Toast.LENGTH_SHORT).show();
            }
        });

        /*if(findViewById(R.id.layout_portrait) !=null ) {
            fragmentManager.beginTransaction().show(ListFragment).show(ButtonFragment).hide(CarFragment).hide(OwnerFragment).hide(NewCarFragment).commit();

            btnCar.setOnClickListener(new View.OnClickListener() {  // This function would only show the Car Information Fragment
                // and will hide the Owner Information Fragment
                @Override
                public void onClick(View view) {
                    fragmentManager.beginTransaction().hide(ListFragment).show(CarFragment).hide(NewCarFragment).hide(OwnerFragment).hide(NewCarFragment).commit();
                }
            });
        }*/

        /*if(findViewById(R.id.layout_landscape) !=null ) {
            fragmentManager.beginTransaction().show(ListFragment).show(ButtonFragment).show(CarFragment).hide(OwnerFragment).hide(NewCarFragment).commit();
        }*/

        onItemClicked(0);  //Initially the first item in the list will be displayed every time
    }

    @Override
    public void onItemClicked(int index) {

        //To set Images on Car Information Fragment, we have implemented the below conditions
        if ("Volkswagen".equals(ApplicationClass.cars.get(index).getCarName())) {
            ivCarImage.setImageResource(R.drawable.volkswagen);
            Toast.makeText(MainActivity.this, "Volkswagen Car", Toast.LENGTH_SHORT).show();
        } else if ("Nissan".equals(ApplicationClass.cars.get(index).getCarName())) {
            ivCarImage.setImageResource(R.drawable.nissan);
            Toast.makeText(MainActivity.this, "Nissan Car", Toast.LENGTH_SHORT).show();
        } else if ("Mercedes".equals(ApplicationClass.cars.get(index).getCarName())) {
            ivCarImage.setImageResource(R.drawable.mercedes);
            Toast.makeText(MainActivity.this, "Mercedes Car", Toast.LENGTH_SHORT).show();
        } else {
            ivCarImage.setImageResource(R.drawable.car_new);  //If the new car name is not in the list, then show this image
        }

        //Attaching the respective TextViews with their implementations
        tvCarModel.setText(ApplicationClass.cars.get(index).getCarModel());
        tvOwnerInfo.setText(ApplicationClass.cars.get(index).getCarModel());
        tvOwnerName.setText(ApplicationClass.cars.get(index).getOwnerName());
        tvPhone.setText(ApplicationClass.cars.get(index).getOwnerPhone());
    }
}
