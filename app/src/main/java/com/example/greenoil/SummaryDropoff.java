package com.example.greenoil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SummaryDropoff extends AppCompatActivity {

    ImageButton backBut;
    Button submitBtn;
    TextView quantity, point, city, factory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_dropoff);


        backBut = findViewById(R.id.backBtn);
        submitBtn = findViewById(R.id.submitBtn);
        quantity = findViewById(R.id.quantity);
        point = findViewById(R.id.point);
        city = findViewById(R.id.city);
        factory = findViewById(R.id.factory);


        Intent intent = getIntent();
        quantity.setText(intent.getStringExtra("quantity") + " L Oil");
        point.setText(intent.getStringExtra("quantity")+ " Points");
        city.setText(intent.getStringExtra("city"));
        factory.setText(intent.getStringExtra("factory"));

        backBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ScheduleDropoff.class));
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