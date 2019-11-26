package com.seproject.reservemac.user;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.seproject.reservemac.R;
import com.seproject.reservemac.background.GetRequests;
import com.seproject.reservemac.facility_manager.Facility_manager_screen;
import com.seproject.reservemac.model.UserModel;
import com.seproject.reservemac.ui.common.DatePickerFragment;
import com.seproject.reservemac.ui.common.SearchFacilityActivity;
import com.seproject.reservemac.ui.common.ViewReservation;
import com.seproject.reservemac.ui.login.LoginActivity;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class ModifyReservation extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, GetRequests.AsyncResponse {


    Button ModifyReservation;
    Button CancelReservation;
    Button BtnDate;
    Button BtnTime;
    TimePickerDialog timePickerDialog;
    TextView FacilityName;
    TextView FacilityCode;
    TextView FacilityDescription;
    TextView FacilityDeposit;
    Integer maxDate =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_reservation);
        ModifyReservation = findViewById(R.id.BtnModifyReservation);
        CancelReservation = findViewById(R.id.BtnCancelReservation);
        BtnDate = findViewById(R.id.EtxDatePicker);
        BtnTime = findViewById(R.id.EtxTimePicker);
        FacilityName = findViewById(R.id.TxtName);
        FacilityCode = findViewById(R.id.TxtFCode);
        FacilityDescription = findViewById(R.id.TxtDescripton);
        FacilityDeposit = findViewById(R.id.TxtDeposit);

        ModifyReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.setMessage("Are you sure you want to Delete this reservation?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                             //   deleteReservation();
//                                Intent intent = new Intent(ModifyReservation.this, LoginActivity.class);
//                                startActivity(intent);                                                        /// Call cancell reservation.php
//                                Facility_manager_screen.this.finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        CancelReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                TimePickerDialog timePickerDialog = new TimePickerDialog(ModifyReservation.this, new TimePickerDialog.OnTimeSetListener() {
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
        String Fname = FacilityName.getText().toString();
        String[] outdoor = new String[] { "2 Outdoor Volleyball Courts", "2 Outdoor Basketball Courts"};

        if(Arrays.asList(outdoor).contains(Fname))
        {
            SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
            String selectedate = dayOfMonth+" "+month+" "+year;
            String todaysdate = c.get(Calendar.DAY_OF_MONTH)+" "+c.get(Calendar.MONTH)+" "+c.get(Calendar.YEAR);
            try {
                Date date1 = myFormat.parse(selectedate);
                Date date2 = myFormat.parse(todaysdate);
                long diff = date1.getTime() - date2.getTime();
                float days = diff / (1000*60*60*24);
                maxDate = (int)days;
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if(maxDate>6){
                Toast.makeText(getApplicationContext(), "Outdoor Facilities are limited to just 7 days including today" , Toast.LENGTH_SHORT).show();
            }
            else{
                c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                c.set(Calendar.MONTH,month);
                c.set(Calendar.YEAR,year);
            }

        }
        else {
            Toast.makeText(getApplicationContext(), "Indoor Facilities are limited to just today's date " , Toast.LENGTH_SHORT).show();
        }
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateString = format1.format(c.getTime());
        BtnDate.setText(currentDateString);
    }

    @Override
    public void ProcessFinish(String output, JSONObject jsonObject, String Identity) {

    }
}
