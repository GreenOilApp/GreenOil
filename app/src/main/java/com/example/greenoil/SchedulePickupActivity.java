package com.example.greenoil;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.app.TimePickerDialog;
import android.widget.TimePicker;
import java.util.Calendar;
import java.util.Locale;

public class SchedulePickupActivity extends AppCompatActivity {

    private static final String TAG = "SchedulePickupActivity";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TextView valueTextView;
    private double value = 5.0;
    private TextView mDisplayTime;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;
    private TextView changeAddressText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_pickup);

        valueTextView = findViewById(R.id.quantityAmount);
        updateValueText();

        changeAddressText = findViewById(R.id.changeAddress);

        changeAddressText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeAddressPicker();
            }
        });

        mDisplayDate = (TextView) findViewById(R.id.tvDate);
        mDisplayTime = findViewById(R.id.tvTime);

        //DatePicker
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                // Set the maximum date by a week & minimum date by today
                cal.add(Calendar.WEEK_OF_YEAR, 1);
                int maxYear = cal.get(Calendar.YEAR);
                int maxMonth = cal.get(Calendar.MONTH);
                int maxDay = cal.get(Calendar.DAY_OF_MONTH);

                cal = Calendar.getInstance();
                int minYear = cal.get(Calendar.YEAR);
                int minMonth = cal.get(Calendar.MONTH);
                int minDay = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(SchedulePickupActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                // Set the maximum & minimum date
                dialog.getDatePicker().setMaxDate(cal.getTimeInMillis());
                dialog.getDatePicker().setMinDate(cal.getTimeInMillis());

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }
        };

        //TimePicker
        mDisplayTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minute = cal.get(Calendar.MINUTE);

                // Calculate minimum time (1 hour from current time)
                cal.add(Calendar.HOUR_OF_DAY, 1);
                int minHour = cal.get(Calendar.HOUR_OF_DAY);
                int minMinute = cal.get(Calendar.MINUTE);

                // Calculate maximum time (end of the day)
                cal.set(Calendar.HOUR_OF_DAY, 23);
                cal.set(Calendar.MINUTE, 59);
                int maxHour = cal.get(Calendar.HOUR_OF_DAY);
                int maxMinute = cal.get(Calendar.MINUTE);

                TimePickerDialog dialog = new TimePickerDialog(SchedulePickupActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mTimeSetListener,
                        hour, minute, false);

                // Set minimum time
                dialog.getButton(TimePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE);
                dialog.getButton(TimePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.RED);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {

            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                Log.d(TAG, "onTimeSet: hh:mm: " + hourOfDay + ":" + minute);

                String time = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute);
                mDisplayTime.setText(time);
            }
        };
    }



    public void addValue(View view) {
        value+=0.5;
        updateValueText();
    }

    public void subtractValue(View view) {
        if (value > 5) {
            value--;
            updateValueText();
        }
    }

    private void updateValueText() {
        valueTextView.setText(String.valueOf(value));
    }

    private void changeAddressPicker() {
        Intent intent = new Intent(this, LocationPickerActivity.class);
        startActivity(intent);
    }

}