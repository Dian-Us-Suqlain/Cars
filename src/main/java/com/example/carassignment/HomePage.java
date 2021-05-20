package com.example.carassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {

    TextView tvWelcome, tvName;
    EditText etName, etEmail, etPhone;
    Button btnClear, btnNext, btnSave;
    String name;

    private static final String FILE_NAME = "com.example.carassignment.UsersList"; //  Name of file where all username's will be stored

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        tvWelcome = findViewById(R.id.tv_welcome);
        tvName = findViewById(R.id.tv_name);

        etName = findViewById(R.id.et_username);
        etEmail = findViewById(R.id.et_email);
        etPhone = findViewById(R.id.et_phone);

        btnClear = findViewById(R.id.clear_button);
        btnNext = findViewById(R.id.next_button);
        btnSave = findViewById(R.id.save_button);

        getIntent();

        SharedPreferences preferences = getSharedPreferences(FILE_NAME,MODE_PRIVATE);
        String username = preferences.getString("username", "");

        tvName.setText(username);

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  // This will clear all plain text views and the name displayed on top too will be disappeared
                tvName.setText("");
                etName.setText("");
                etEmail.setText("");
                etPhone.setText("");
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = etName.getText().toString().trim();
                tvName.setText(name);

                SharedPreferences.Editor editor = getSharedPreferences(FILE_NAME, MODE_PRIVATE).edit();
                editor.putString("username",name);
                editor.commit();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(HomePage.this, MainActivity.class);
                startActivity(sendIntent);
                finish();
            }
        });
    }
}
