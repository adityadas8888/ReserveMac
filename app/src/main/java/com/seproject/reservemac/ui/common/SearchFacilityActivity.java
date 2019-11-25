package com.seproject.reservemac.ui.common;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.seproject.reservemac.R;
import com.seproject.reservemac.model.UserModel;
import com.seproject.reservemac.user.ReserveFacility;
import com.seproject.reservemac.user.User_screen;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

public class SearchFacilityActivity extends AppCompatActivity implements  DatePickerDialog.OnDateSetListener{

    Button BtnSearchFacility;
    Button BtnDate;
    Button BtnTime;
    TimePickerDialog timePickerDialog;
    TextView header;
    Spinner FacilityName;
    Integer maxDate=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_facility);
        BtnSearchFacility = findViewById(R.id.BtnSearchFacility);
        BtnDate = findViewById(R.id.EtxDatePicker);
        header = findViewById(R.id.activityheader);
        BtnTime = findViewById(R.id.EtxTimePicker);
        FacilityName = findViewById(R.id.SpinnerFType);
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

        FacilityName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String Fname = FacilityName.getSelectedItem().toString();
                String[] outdoor = new String[] { "2 Outdoor Volleyball Courts", "2 Outdoor Basketball Courts"};
                if(Arrays.asList(outdoor).contains(Fname))
                {
                    maxDate = 6;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
        if(Calendar.DAY_OF_MONTH<=Calendar.DAY_OF_MONTH+maxDate)
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        else {
            c.set(Calendar.DAY_OF_MONTH, Calendar.DAY_OF_MONTH);
            Toast.makeText(getApplicationContext(), "Invalid date for the facility type: " , Toast.LENGTH_SHORT).show();
        }
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateString = format1.format(c.getTime());
        BtnDate.setText(currentDateString);
    }


    }
