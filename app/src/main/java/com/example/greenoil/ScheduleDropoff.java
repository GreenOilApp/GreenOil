package com.example.greenoil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ScheduleDropoff extends AppCompatActivity {

    ImageButton backToOptionsBtn;
    Spinner citySpinner, optionsSpinner;
    TextView valueTextView;
    Button next;
    private double value = 5.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_dropoff);

        //Back arrow
        backToOptionsBtn = findViewById(R.id.backToOptionsBtn);
        backToOptionsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToScheduleOptions();
            }
        });


        valueTextView = findViewById(R.id.quantityAmount);
        updateValueText();

        citySpinner = findViewById(R.id.spinner_city);
        optionsSpinner = findViewById(R.id.spinner_options);
        ArrayAdapter<CharSequence> cityAdapter = ArrayAdapter.createFromResource(
                this, R.array.saudi_arabia_cities, android.R.layout.simple_spinner_item);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(cityAdapter);

        final String[] directions = {""};

        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] optionsArray;
                switch (position) {
                    case 0: // Riyadh
                        optionsArray = getResources().getStringArray(R.array.riyadh_options);
                        break;
                    case 1: // Jeddah
                        optionsArray = getResources().getStringArray(R.array.jeddah_options);
                        break;
                    case 2: // Dammam
                    default:
                        optionsArray = getResources().getStringArray(R.array.dammam_options);
                        break;
                }
                ArrayAdapter<String> optionsAdapter3 = new ArrayAdapter<>(ScheduleDropoff.this,
                        android.R.layout.simple_spinner_item, optionsArray);
                optionsAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                optionsSpinner.setAdapter(optionsAdapter3);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String quantity = valueTextView.getText().toString();
                String city = citySpinner.getSelectedItem().toString();
                String factory = optionsSpinner.getSelectedItem().toString();

                Intent intent = new Intent(getApplicationContext(), SummaryDropoff.class);
                intent.putExtra("quantity",quantity);
                intent.putExtra("city",city);
                intent.putExtra("factory",factory);
                startActivity(intent);
                overridePendingTransition(R.anim.to_right1, R.anim.to_right2);
                finish();

            }
        });
    }

    private void backToScheduleOptions() {
        startActivity(new Intent(getApplicationContext(), ScheduleOptions.class));
        overridePendingTransition(R.anim.to_left1, R.anim.to_left2);
        finish();
    }

    public void addValue(View view) {
        value+=0.5;
        updateValueText();
    }

    public void subtractValue(View view) {
        if (value > 5) {
            value-=0.5;
            updateValueText();
        }
    }

    private void updateValueText() {
        if (value < 5){
            value = 5;
        }
        valueTextView.setText(String.valueOf(value));
    }

}