package com.example.greenoil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Homepage extends AppCompatActivity {

    Button recycleBut ,pointBut ,serviceBut ,rewardBut ,exploreBut;
    ImageButton ehsanBut;
    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);


        recycleBut = findViewById(R.id.recycleBut);
        ehsanBut = findViewById(R.id.ehsanBut);
        pointBut = findViewById(R.id.pointBut);
        serviceBut = findViewById(R.id.serviceBut);
        rewardBut = findViewById(R.id.rewardBut);
        exploreBut = findViewById(R.id.exploreBut);
        bottomNav = findViewById(R.id.bottomnavigation);

        recycleBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ScheduleOptions.class));
                overridePendingTransition(R.anim.to_right1, R.anim.to_right2);
                finish();
            }
        });

        ehsanBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String websiteUrl = "https://ehsan.sa/";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(websiteUrl));
                startActivity(intent);
            }
        });

        pointBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Point.class));
                overridePendingTransition(R.anim.to_right1, R.anim.to_right2);
                finish();
            }
        });

        serviceBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Service.class));
                overridePendingTransition(R.anim.to_right1, R.anim.to_right2);
                finish();
            }
        });

        rewardBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Reward.class));
                overridePendingTransition(R.anim.to_right1, R.anim.to_right2);
                finish();
            }
        });

        exploreBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Explore.class));
                overridePendingTransition(R.anim.to_right1, R.anim.to_right2);
                finish();
            }
        });

        bottomNav.setSelectedItemId(R.id.home);
        bottomNav.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){

                case R.id.home:
                    return true;

                case R.id.schedule:
                    startActivity(new Intent(getApplicationContext(), ScheduleOptions.class));
                    overridePendingTransition(R.anim.to_right1, R.anim.to_right2);
                    finish();
                    return true;

                case R.id.profile:
                    startActivity(new Intent(getApplicationContext(), Profile.class));
                    overridePendingTransition(R.anim.to_right1, R.anim.to_right2);
                    finish();
                    return true;
            }
            return false;
        });
    }
}