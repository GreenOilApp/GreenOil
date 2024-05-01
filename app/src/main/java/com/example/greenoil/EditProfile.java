package com.example.greenoil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class EditProfile extends AppCompatActivity {


    TextInputEditText name,phone;
    TextView email;
    Button sava;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        sava = findViewById(R.id.apply_changes);

        Intent intent = getIntent();
        email.setText(intent.getStringExtra("Email"));

        sava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name1 = String.valueOf(name.getText()).trim();
                String phone1 = String.valueOf(phone.getText()).trim();


                HashMap user = new HashMap<>();
                user.put("Name", name1);
                user.put("Phone Number", phone1);


                SharedPreferences sharedPreferences = getSharedPreferences("my_shared_preferences", Context.MODE_PRIVATE);
                String value = sharedPreferences.getString("user", "default_value");

                if (value != null){

                    databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(value);
                    databaseReference.updateChildren(user).addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if(task.isSuccessful()){

                                Toast.makeText(EditProfile.this, "Info has been updated.",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(EditProfile.this, profile.class);
                                startActivity(intent);
                                finish();

                            }else{
                                Toast.makeText(EditProfile.this, "There is a problem updating the info.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });
    }
}