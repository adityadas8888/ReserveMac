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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;


public class SearchFacilityActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    Button BtnSearchFacility;
    Button BtnDate;
    Button BtnTime;
    TimePickerDialog timePickerDialog;
    TextView header;
    Spinner SpinnerFType;
    String facilityCode = "";

    HashMap<String, String> facilitycode = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_facility);
        SpinnerFType = findViewById(R.id.SpinnerFType);
        BtnSearchFacility = findViewById(R.id.BtnSearchFacility);
        BtnDate = findViewById(R.id.EtxDatePicker);
        header = findViewById(R.id.activityheader);
        BtnTime = findViewById(R.id.EtxTimePicker);
        final UserModel usermodel = (UserModel) getIntent().getParcelableExtra("usermodel");

        if (usermodel.getRole().equals("fm")) {
            header.setText("Search Facility FM");
        } else
            header.setText("Search Facility");


        facilitycode.put("Multipurpose Rooms", "MR");
        facilitycode.put("5 Indoor Basketball Court", "IBBC");
        facilitycode.put("9 Volleyball Court", "IVBC");
        facilitycode.put("Indoor Soccer Gymnasium", "SCG");
        facilitycode.put("5 Racquetball Courts", "RBC");
        facilitycode.put("10 Badminton Courts", "BMC");
        facilitycode.put("Table Tennis", "TT");
        facilitycode.put("Conference Rooms", "CR");
        facilitycode.put("2 Outdoor Volleyball Courts", "OVBC");
        facilitycode.put("2 Outdoor Basketball Courts", "OBBC");

        SpinnerFType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String faclity = SpinnerFType.getSelectedItem().toString();

                facilityCode = facilitycode.get(faclity);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                facilityCode = "MR";
            }
        });


        BtnSearchFacility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SearchFacilityActivity.this, "" + facilityCode, Toast.LENGTH_SHORT).show();
                Intent myintent = new Intent(getBaseContext(), ListOfFacilitiesActivity.class);

                myintent.putExtra("facilitycode", facilityCode);
                myintent.putExtra("date", BtnDate.getText());
                myintent.putExtra("time", BtnTime.getText());
                startActivity(myintent);
            }
        });


        BtnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });


        BtnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(SearchFacilityActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        String roundMinutes = "";
                        if (minutes == 0 || minutes <= 15) {
                            roundMinutes = "00";

                        } else if (minutes > 15 && minutes < 59) {
                            roundMinutes = "30";
                        }
                        String roundHours = "";
                        if (hourOfDay == 0) {
                            roundHours = "00";
                        } else
                            roundHours = Integer.toString(hourOfDay);

                        BtnTime.setText(roundHours + ":" + roundMinutes);
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

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

        String currentDateString = format1.format(c.getTime());

//        String currentDateString = DateFormat.getDateInstance(DateFormat.DEFAULT).format(c.getTime());
        BtnDate.setText(currentDateString);
    }


}


