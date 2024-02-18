package com.example.greenoil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    //Declaration
    Button btnReset, btnBack;
    EditText edtEmail;
    ProgressBar progressBar;
    FirebaseAuth mAuth;
    String strEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        //Initializaton
        btnBack = findViewById(R.id.btnForgotPasswordBack);
        btnReset = findViewById(R.id.btnReset);
        edtEmail = findViewById(R.id.edtForgotPasswordEmail);
        progressBar = findViewById(R.id.forgetPasswordProgressbar);

        mAuth = FirebaseAuth.getInstance();

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = String.valueOf( edtEmail.getText()).trim();
                boolean validEmail = validateEmail(email);
                if(validEmail == true){

                    mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()){
                                Toast.makeText(ForgotPasswordActivity.this, "check YOUR EMAIL.",
                                        Toast.LENGTH_SHORT).show();

                            }
                            else{
                                Toast.makeText(ForgotPasswordActivity.this, "this email is not rejestared.",
                                        Toast.LENGTH_SHORT).show();

                            }

                        }
                    });
                }else {
                    Toast.makeText(ForgotPasswordActivity.this, "not valid email.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public boolean validateEmail(String email){

        if (email.isEmpty()){
            return false;
        }
        return true;
    }
}