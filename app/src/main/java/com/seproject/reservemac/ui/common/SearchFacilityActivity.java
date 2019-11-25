package com.seproject.reservemac.ui.common;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.seproject.reservemac.R;
import com.seproject.reservemac.model.UserModel;
import com.seproject.reservemac.user.ReserveFacility;

import java.text.DateFormat;
import java.util.Calendar;

public class SearchFacilityActivity extends AppCompatActivity implements  DatePickerDialog.OnDateSetListener{

    Button BtnSearchFacility;
    Button BtnDate;
    Button BtnTime;
    TimePickerDialog timePickerDialog;
    TextView header;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_facility);
        BtnSearchFacility = findViewById(R.id.BtnSearchFacility);
        BtnDate = findViewById(R.id.EtxDatePicker);
        header = findViewById(R.id.activityheader);
        BtnTime = findViewById(R.id.EtxTimePicker);
        final UserModel usermodel = (UserModel) getIntent().getParcelableExtra("usermodel");

        if (usermodel.getRole().equals("fm")){
            header.setText("Search Facility FM");
        }
        else
            header.setText("Search Facility");

        BtnSearchFacility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(getBaseContext(), ReserveFacility.class);
                startActivity(myintent);
            }
        });


        BtnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"date picker");
            }
            });


        BtnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(SearchFacilityActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        String roundMinutes="";
                        if (minutes==0 ||minutes<=15){
                            roundMinutes="00";

                        }
                        else if (minutes>15 && minutes<59 ){
                            roundMinutes="30";
                        }
                        String roundHours="";
                        if (hourOfDay==0 ){
                            roundHours="00";
                        }
                        else
                            roundHours=Integer.toString(hourOfDay);

                    BtnTime.setText(roundHours+":"+roundMinutes);
                    }
                }, 0, 0, false);
                timePickerDialog.show();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.DEFAULT).format(c.getTime());
        BtnDate.setText(currentDateString);
    }


    }
