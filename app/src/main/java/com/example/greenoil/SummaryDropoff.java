package com.example.greenoil;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.HashMap;

public class SummaryDropoff extends AppCompatActivity {

    ImageButton backBut;
    Button submitBtn;
    TextView quantity, point, city, factory;
    DatabaseReference databaseReference;

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

        String PointEarned = intent.getStringExtra("quantity");

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


                SharedPreferences sharedPreferences = getSharedPreferences("my_shared_preferences", Context.MODE_PRIVATE);
                String value = sharedPreferences.getString("user", "default_value");

                if (value != null){

                    databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(value);
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            String PointDatabase = snapshot.child("Point").getValue(String.class);
                            Long TotalPoint1 = Long.parseLong(PointDatabase);
                            Float TotalPoint12 = Float.parseFloat(PointEarned);
                            Long TotalPoint = TotalPoint1 + Math.round(TotalPoint12);
                            String point = String.valueOf(TotalPoint);

                            HashMap user = new HashMap<>();
                            user.put("Point", point);
                            databaseReference.updateChildren(user).addOnCompleteListener(new OnCompleteListener() {
                                @Override
                                public void onComplete(@NonNull Task task) {
                                    if(task.isSuccessful()){

                                        startActivity(new Intent(getApplicationContext(), Confirmation.class));
                                        overridePendingTransition(R.anim.to_right1, R.anim.to_right2);
                                        finish();

                                    }
                                }
                            });
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.d(TAG, "Error getting user data", error.toException());
                        }
                    });
                }
            }
        });
    }
}