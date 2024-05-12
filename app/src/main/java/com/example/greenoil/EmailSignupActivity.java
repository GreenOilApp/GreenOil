package com.example.greenoil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.regex.Pattern;

public class EmailSignupActivity extends AppCompatActivity {

    public static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" + "(?=.*[0-9])" + "(?=.*[a-z])" + "(?=.*[A-Z])" +
                    "(?=.*[a-zA-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$)" + ".{8,30}" + "$");
    TextInputEditText fullName,phoneNum,emailSignUp,password,passConfirm;
    Button signUpCompleteBtn;
    ProgressBar Progress;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_signup);

        fullName = findViewById(R.id.fullName);
        phoneNum = findViewById(R.id.phoneNum);
        emailSignUp = findViewById(R.id.emailSignUp);
        password = findViewById(R.id.passSignUp);
        passConfirm = findViewById(R.id.passSignUpConfirm);
        signUpCompleteBtn = findViewById(R.id.signUpComplete);
        Progress = findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();



        signUpCompleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = String.valueOf(fullName.getText()).trim();
                String num = String.valueOf(phoneNum.getText()).trim();
                String email = String.valueOf( emailSignUp.getText()).trim();
                String pass = String.valueOf( password.getText()).trim();

                boolean validName = validateName(name);
                boolean validNum = validateNum(num);
                boolean validEmail = validateEmail(email);
                boolean validPass = validatePass(pass);
                boolean passConfirm = validatePassConfirm(pass);

                Progress.setVisibility(View.VISIBLE);

                if (validName == true && validNum == true && validEmail && true && validPass == true && passConfirm == true) {

                    mAuth.createUserWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    Progress.setVisibility(View.GONE);

                                    if (task.isSuccessful()) {

                                        FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();
                                        HashMap<String, String> user = new HashMap<>();
                                        user.put("Name", name);
                                        user.put("Email", email);
                                        user.put("Phone Number", num);
                                        user.put("Image", "");
                                        user.put("Point", "0");

                                        mDatabase.child(user1.getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                Toast.makeText(EmailSignupActivity.this, "Account created.",
                                                        Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(EmailSignupActivity.this, LoginActivity.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                        });
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(EmailSignupActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });

                }else {
                    Progress.setVisibility(View.GONE);
                    Toast.makeText(EmailSignupActivity.this, "Please enter the information.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean validateName(String name){

        if (name.isEmpty()){
            fullName.setError("Please enter a name");
            return false;
        }
        return true;
    }

    public boolean validateNum(String num){

        if (!Patterns.PHONE.matcher(num).matches()){
            phoneNum.setError("Please enter a valid mobile number");
            return false;
        }
        return true;
    }

    public boolean validateEmail(String email){

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailSignUp.setError("Please enter a valid email");
            return false;
        }
        return true;
    }

    public boolean validatePass(String pass){

        if (!PASSWORD_PATTERN.matcher(pass).matches()){
            password.setError("Please enter a valid password");
            return false;
        }
        return true;
    }

    public boolean validatePassConfirm(String Pass){

        String passCon = String.valueOf(passConfirm.getText()).trim();

        if (!passCon.matches(Pass)){
            passConfirm.setError("Password doesn't match");
            return false;
        }
        return true;
    }
}