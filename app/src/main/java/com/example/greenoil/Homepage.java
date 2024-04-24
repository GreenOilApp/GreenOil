package com.example.greenoil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Homepage extends AppCompatActivity {

    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        bottomNav = findViewById(R.id.bottomnavigation);
        bottomNav.setSelectedItemId(R.id.home);

        bottomNav.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){

                case R.id.home:
                    return true;

                case R.id.schedule:
                    startActivity(new Intent(getApplicationContext(), ScheduleOptions.class));
                    overridePendingTransition(R.anim.slide_right, R.anim.slide_left);
                    finish();
                    return true;

                case R.id.profile:
                    startActivity(new Intent(getApplicationContext(), profile.class));
                    overridePendingTransition(R.anim.slide_right, R.anim.slide_left);
                    finish();
                    return true;
            }
            return false;
        });
    }
}