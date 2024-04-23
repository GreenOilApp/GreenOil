package com.example.greenoil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ScheduleOptions extends AppCompatActivity {

    BottomNavigationView bottomNav;

    private Button pickupOptionBtn;
    private Button dropoffOptionBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_options);

        pickupOptionBtn = findViewById(R.id.pickupBtn);
        dropoffOptionBtn = findViewById(R.id.dropoffBtn);

        pickupOptionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickupOption();
            }
        });

        dropoffOptionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropoffOption();
            }
        });

        bottomNav = findViewById(R.id.bottomnavigation);
        bottomNav.setSelectedItemId(R.id.schedule);

        bottomNav.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){

                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), Homepage.class));
                    overridePendingTransition(R.anim.slide_right, R.anim.slide_left);
                    finish();
                    return true;
                case R.id.schedule:
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

    private void pickupOption() {
        Intent intent = new Intent(this, SchedulePickupActivity.class);
        startActivity(intent);
    }

    private void dropoffOption() {
        Intent intent = new Intent(this, ScheduleDropoff.class);
        startActivity(intent);
    }
}