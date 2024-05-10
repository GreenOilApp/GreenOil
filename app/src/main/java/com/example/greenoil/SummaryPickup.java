package com.example.greenoil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SummaryPickup extends AppCompatActivity {

    ImageButton backBut;
    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_pickup);


        backBut = findViewById(R.id.backBtn);
        submitBtn = findViewById(R.id.submitBtn);


        backBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SchedulePickupActivity.class));
                overridePendingTransition(R.anim.to_left1, R.anim.to_left2);
                finish();
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Confirmation.class));
                overridePendingTransition(R.anim.to_right1, R.anim.to_right2);
                finish();
            }
        });
    }
}