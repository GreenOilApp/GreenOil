package com.example.greenoil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;

public class EditProfile extends AppCompatActivity {

    ImageView imageView;
    TextInputEditText name,email,phone;
    Button sava;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        imageView = findViewById(R.id.profileImage);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        sava = findViewById(R.id.apply_changes);


    }
}