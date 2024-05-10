package com.example.greenoil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScheduleDropoff extends AppCompatActivity {

    private ImageButton backToOptionsBtn;
    private Spinner citySpinner;
    private Spinner optionsSpinner;
    private TextView forDirectionsText;
    private TextView directionsLink;
    private TextView valueTextView;
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

        forDirectionsText = findViewById(R.id.forDirectionsTxt);
        directionsLink = findViewById(R.id.directionsLink);

        Spinner citySpinner = findViewById(R.id.spinner_city);
        Spinner optionsSpinner = findViewById(R.id.spinner_options);
        ArrayAdapter<CharSequence> cityAdapter = ArrayAdapter.createFromResource(
                this, R.array.saudi_arabia_cities, android.R.layout.simple_spinner_item);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(cityAdapter);

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
                ArrayAdapter<String> optionsAdapter = new ArrayAdapter<>(ScheduleDropoff.this,
                        android.R.layout.simple_spinner_item, optionsArray);
                optionsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                optionsSpinner.setAdapter(optionsAdapter);
                if (position == 0) {
                } else {
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
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