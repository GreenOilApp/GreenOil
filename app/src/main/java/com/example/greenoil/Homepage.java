package com.example.greenoil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Homepage extends AppCompatActivity {


    Button recycl;
    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);


        recycl = findViewById(R.id.recycl);
        bottomNav = findViewById(R.id.bottomnavigation);
        bottomNav.setSelectedItemId(R.id.home);

        recycl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ScheduleOptions.class));
                overridePendingTransition(R.anim.slide_right, R.anim.slide_left);
                finish();
            }
        });


        bottomNav.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){

                case R.id.home:
                    return true;

                case R.id.schedule:
                    startActivity(new Intent(getApplicationContext(), schedule.class));
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