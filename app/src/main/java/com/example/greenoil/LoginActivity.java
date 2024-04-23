package com.example.greenoil;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    Button login;
    Button signupEmailBtn;
    TextView forgotPass;
    TextInputEditText emailLogin,password;
    FirebaseAuth mAuth;
    ProgressBar Progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signupEmailBtn = findViewById(R.id.signUpEmail);
        forgotPass = findViewById(R.id.forgotPass);
        login = findViewById(R.id.loginComplete);
        emailLogin = findViewById(R.id.emailLogin);
        password = findViewById(R.id.passLogin);
        mAuth = FirebaseAuth.getInstance();
        Progress = findViewById(R.id.progressBar);




        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = String.valueOf( emailLogin.getText()).trim();
                String pass = String.valueOf( password.getText()).trim();

                Progress.setVisibility(View.VISIBLE);

                boolean validEmail = validateEmail(email);
                boolean validPass = validatePass(pass);


                if (validEmail == true && validPass == true){

                    mAuth.signInWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    Progress.setVisibility(View.GONE);

                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information

                                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                        SharedPreferences sharedPreferences = getSharedPreferences("my_shared_preferences", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString("user", user.getUid());
                                        editor.apply();

                                        Toast.makeText(LoginActivity.this, "Successful login.",
                                                Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(LoginActivity.this, Homepage.class);
//                                        intent.putExtra("user",user);
                                        startActivity(intent);
                                        finish();

                                    } else {
                                        Toast.makeText(LoginActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }else {
                    Progress.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, "Please enter your email and password.",
                            Toast.LENGTH_SHORT).show();

                }
            }
        });

        forgotPass.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,forgot_pass.class);
                startActivity(intent);

            }
        });

        signupEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,EmailSignupActivity.class);
                startActivity(intent);
            }
        });
    }
    public boolean validateEmail(String email){

        if (email.isEmpty()){
            return false;
        }
        return true;
    }

    public boolean validatePass(String pass){

        if (pass.isEmpty()){
            return false;
        }
        return true;
    }
}
