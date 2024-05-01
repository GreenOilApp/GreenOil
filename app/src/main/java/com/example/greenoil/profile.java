package com.example.greenoil;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class profile extends AppCompatActivity {

    TextView textUsername1,textUsername2,textEmail,textPhone;
    BottomNavigationView bottomNav;
    Button editProfile;
    FirebaseUser user;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        textUsername1 = findViewById(R.id.fullName1);
        textUsername2 = findViewById(R.id.fullName2);
        textEmail = findViewById(R.id.email);
        textPhone = findViewById(R.id.phone);
        editProfile = findViewById(R.id.edit_profile);
        bottomNav = findViewById(R.id.bottomnavigation);

        String email = String.valueOf(textEmail.getText()).trim();
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), EditProfile.class).putExtra("Email",email));
                overridePendingTransition(R.anim.slide_right, R.anim.slide_left);
                finish();
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("my_shared_preferences", Context.MODE_PRIVATE);
        String value = sharedPreferences.getString("user", "default_value");

        if (value != null){
            databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(value);
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        // Retrieve user data
                        String username = snapshot.child("Name").getValue(String.class);
                        String email = snapshot.child("Email").getValue(String.class);
                        String phone = snapshot.child("Phone Number").getValue(String.class);

                        textUsername1.setText(username);
                        textUsername2.setText(username);
                        textEmail.setText(email);
                        textPhone.setText(phone);

                    } else {
                        Log.d(TAG, "No such document");
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.d(TAG, "Error getting user data", error.toException());
                }
            });
        }

        bottomNav.setSelectedItemId(R.id.profile);

        bottomNav.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){

                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), Homepage.class));
                    overridePendingTransition(R.anim.slide_right, R.anim.slide_left);
                    finish();
                    return true;
                case R.id.schedule:
                    startActivity(new Intent(getApplicationContext(), ScheduleOptions.class));
                    overridePendingTransition(R.anim.slide_right, R.anim.slide_left);
                    finish();
                    return true;
                case R.id.profile:
                    return true;
            }
            return false;
        });
    }

}