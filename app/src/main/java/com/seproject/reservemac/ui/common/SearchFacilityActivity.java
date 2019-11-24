package com.seproject.reservemac.ui.common;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.seproject.reservemac.R;
import com.seproject.reservemac.user.User_screen;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SearchFacilityActivity extends AppCompatActivity implements  DatePickerDialog.OnDateSetListener{

    Button BtnSearchFacility;
    Button BtnDate;
    Spinner SpinnerTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_facility);
        BtnSearchFacility = findViewById(R.id.BtnSearchFacility);
        BtnDate = findViewById(R.id.EtxDatePicker);
        SpinnerTime = findViewById(R.id.EtxTimePicker);
        String[] items = new String[]{"00:00", "01:00","02:00", "03:00","04:00", "05:00","06:00", "07:00","08:00", "09:00","10:00", "11:00","12:00", "13:00","14:00", "15:00","16:00", "17:00","18:00", "19:00","20:00", "21:00","22:00", "23:00"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        SpinnerTime.setSelection(0);
        SpinnerTime.setAdapter(adapter);

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

        SpinnerTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String time = SpinnerTime.getSelectedItem().toString();
                DateFormat sdf = new SimpleDateFormat("hh:mm");
                try {
                    Date date = sdf.parse(time);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    Toast.makeText(getApplicationContext(), "time: " + cal.get(Calendar.HOUR), Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
