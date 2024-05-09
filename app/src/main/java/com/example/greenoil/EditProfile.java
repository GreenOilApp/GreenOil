package com.example.greenoil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class EditProfile extends AppCompatActivity {

    ImageView image;
    TextInputEditText name,phone;
    TextView email;
    Button sava;
    Uri uri;
    UploadTask  uploadTask;
    StorageReference storageReference;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);


        image = findViewById(R.id.profileImage);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        sava = findViewById(R.id.apply_changes);

        Intent intent = getIntent();
        String email1 = intent.getStringExtra("Email");
        email.setText(email1);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImagePicker.with(EditProfile.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });

        sava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name1 = String.valueOf(name.getText()).trim();
                String phone1 = String.valueOf(phone.getText()).trim();

                SharedPreferences sharedPreferences = getSharedPreferences("my_shared_preferences", Context.MODE_PRIVATE);
                String value = sharedPreferences.getString("user", "default_value");

                if (value != null){

                    if (uri != null){

                        storageReference = FirebaseStorage.getInstance().getReference().child("images/"+uri.getLastPathSegment());
                        uploadTask = storageReference.putFile(uri);
                    }

                    HashMap user = new HashMap<>();
                    user.put("Name", name1);
                    user.put("Phone Number", phone1);
                    user.put("Image", uri.getLastPathSegment());

                    databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(value);
                    databaseReference.updateChildren(user).addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if(task.isSuccessful()){


                                Toast.makeText(EditProfile.this, "Info has been updated.",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(EditProfile.this, Profile.class);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        uri = data.getData();
        image.setImageURI(uri);
    }
}