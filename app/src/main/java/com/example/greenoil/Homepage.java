package com.example.greenoil;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;

import java.io.File;
import java.io.IOException;

public class Homepage extends AppCompatActivity {

    Button recycleBut ,pointBut ,serviceBut ,rewardBut ,exploreBut;
    TextView point;
    ImageButton ehsanBut;
    BottomNavigationView bottomNav;
    DatabaseReference databaseReference;

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
        point = findViewById(R.id.point);
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

        SharedPreferences sharedPreferences = getSharedPreferences("my_shared_preferences", Context.MODE_PRIVATE);
        String value = sharedPreferences.getString("user", "default_value");

        if (value != null || !value.equals("default_value")){

            databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(value);
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    String Point = snapshot.child("Point").getValue(String.class);
                    point.setText(Point);

                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.d(TAG, "Error getting user data", error.toException());
                }
            });

        }

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