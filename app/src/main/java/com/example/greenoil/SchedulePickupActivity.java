package com.example.greenoil;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
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
    private ImageButton backToOptionsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_pickup);

        backToOptionsBtn = findViewById(R.id.backToOptionsBtn);

        backToOptionsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToScheduleOptions();
            }
        });

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
        mDisplayTime = (TextView) findViewById(R.id.tvTime);

        //DatePicker
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

//                // Set the maximum date by a week & minimum date by today
//                cal.add(Calendar.WEEK_OF_YEAR, 1);
//                int maxYear = cal.get(Calendar.YEAR);
//                int maxMonth = cal.get(Calendar.MONTH);
//                int maxDay = cal.get(Calendar.DAY_OF_MONTH);

                cal = Calendar.getInstance();
                int minYear = cal.get(Calendar.YEAR);
                int minMonth = cal.get(Calendar.MONTH);
                int minDay = cal.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog dialog = new DatePickerDialog(SchedulePickupActivity.this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getDatePicker().setMinDate(cal.getTimeInMillis());

                cal.add(Calendar.WEEK_OF_YEAR, 2);
                int maxYear = cal.get(Calendar.YEAR);
                int maxMonth = cal.get(Calendar.MONTH);
                int maxDay = cal.get(Calendar.DAY_OF_MONTH);

                // Set the maximum & minimum date
                dialog.getDatePicker().setMaxDate(cal.getTimeInMillis());

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
                cal.add(Calendar.HOUR_OF_DAY, 3);
                int minHour = cal.get(Calendar.HOUR_OF_DAY);
                int minMinute = cal.get(Calendar.MINUTE);

                // Calculate maximum time (end of the day)
                cal.set(Calendar.HOUR_OF_DAY, 23);
                cal.set(Calendar.MINUTE, 59);
                int maxHour = cal.get(Calendar.HOUR_OF_DAY);
                int maxMinute = cal.get(Calendar.MINUTE);

                TimePickerDialog dialog = new TimePickerDialog(SchedulePickupActivity.this,
                        android.R.style.Theme_DeviceDefault_Dialog_MinWidth,
                        mTimeSetListener,
                        hour, minute, false);


                dialog.show();
            }
        });
        mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            boolean flag = false;

            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                Log.d(TAG, "onTimeSet: hh:mm: " + hourOfDay + ":" + minute);
                minute = roundToNearest30Minutes(minute);
                if (flag == true){
                    hourOfDay++;
                }
                String time = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute);
                flag=false;
                mDisplayTime.setText(time);
            }
            private int roundToNearest30Minutes(int minute) {
                if (minute >= 45) {
                    flag = true;
                    return 0;
                } else if (minute >= 30) {
                    return 30;
                } else if (minute >= 15) {
                    return 30;
                } else {
                    return 0;
                }
            }

        };
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

    private void changeAddressPicker() {
        Intent intent = new Intent(this, LocationPickerActivity.class);
        startActivity(intent);
    }
    private void backToScheduleOptions() {
        Intent intent = new Intent(this, ScheduleOptions.class);
        startActivity(intent);
    }

}