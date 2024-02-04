package com.example.greenoil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class EmailSignupActivity extends AppCompatActivity {

    Button signUpCompleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_signup);

        signUpCompleteBtn = findViewById(R.id.signUpComplete);

        signUpCompleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmailSignupActivity.this, LoginActivity.class);
                //add data validation
                if (true){
                    startActivity(intent);
                }

            }
        });
    }
}