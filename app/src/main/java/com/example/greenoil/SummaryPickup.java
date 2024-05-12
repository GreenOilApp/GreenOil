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

public class SummaryPickup extends AppCompatActivity {

    TextView quantity, point, date, time;
    ImageButton backBut;
    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_pickup);


        backBut = findViewById(R.id.backBtn);
        submitBtn = findViewById(R.id.submitBtn);

        quantity = findViewById(R.id.quantity);
        point = findViewById(R.id.point);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);

        Intent intent = getIntent();
        quantity.setText(intent.getStringExtra("quantity") + " L Oil");
        point.setText(intent.getStringExtra("quantity")+ " Points");
        date.setText(intent.getStringExtra("date"));
        time.setText(intent.getStringExtra("time"));


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