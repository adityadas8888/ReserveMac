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
import com.seproject.reservemac.model.FacilityModel;
import com.seproject.reservemac.model.ReservationModel;
import com.seproject.reservemac.ui.common.DatePickerFragment;
import com.seproject.reservemac.ui.common.ViewReservation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    Integer maxDate = 0;
    //    ArrayList<FacilityModel> facilityModelArrayList;
    ReservationModel reservationModel;


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

        reservationModel = getIntent().getParcelableExtra("reservationModel");

        FacilityName.setText(reservationModel.getFacilityName());
        FacilityCode.setText(reservationModel.getFacilitycode());
        FacilityDescription.setText(reservationModel.getFacilitydescription());
        FacilityDeposit.setText(String.valueOf(reservationModel.getDeposit()));

        BtnDate.setText(reservationModel.getDate());
        BtnTime.setText(reservationModel.getstarttime());


        ModifyReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = "ViewReservation";
                StringBuilder stringBuilder = new StringBuilder();

//                http://mohammedmurtuzabhaiji.uta.cloud/se1project/user/facility_search.php?
                // facilitycode=BMC&date=2019-11-25&time=02:00

                stringBuilder.append("user/facility_search.php?facilitycode=").append(reservationModel.getFacilitycode().replaceAll("[0-9]", ""));
                stringBuilder.append("&date=").append(BtnDate.getText().toString());
                stringBuilder.append("&time=").append(BtnTime.getText().toString());
                String url = stringBuilder.toString();
                new GetRequests(ModifyReservation.this, url, ModifyReservation.this, "CheckAvailability").execute("");

            }
        });

        CancelReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(ModifyReservation.this);
                builder.setMessage("Are you sure you want to cancel your resrevation?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                StringBuilder stringBuilder = new StringBuilder();

//                http://mohammedmurtuzabhaiji.uta.cloud/se1project/user/cancel_reservation.php?
//                reservationid=105

                                stringBuilder.append("user/cancel_reservation.php?reservationid=")
                                        .append(reservationModel.getReservationid());
                                String url = stringBuilder.toString();
                                new GetRequests(ModifyReservation.this, url, ModifyReservation.this, "CancelReservation").execute("");

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
                TimePickerDialog timePickerDialog = new TimePickerDialog(ModifyReservation.this, new TimePickerDialog.OnTimeSetListener() {
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
        String Fname = FacilityName.getText().toString();
        String[] outdoor = new String[]{"2 Outdoor Volleyball Courts", "2 Outdoor Basketball Courts"};

        if (Arrays.asList(outdoor).contains(Fname)) {
            SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
            String selectedate = dayOfMonth + " " + month + " " + year;
            String todaysdate = c.get(Calendar.DAY_OF_MONTH) + " " + c.get(Calendar.MONTH) + " " + c.get(Calendar.YEAR);
            try {
                Date date1 = myFormat.parse(selectedate);
                Date date2 = myFormat.parse(todaysdate);
                long diff = date1.getTime() - date2.getTime();
                float days = diff / (1000 * 60 * 60 * 24);
                maxDate = (int) days;
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (maxDate > 6) {
                Toast.makeText(getApplicationContext(), "Outdoor Facilities are limited to just 7 days including today", Toast.LENGTH_SHORT).show();
            } else {
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                c.set(Calendar.MONTH, month);
                c.set(Calendar.YEAR, year);
            }

        } else {
            Toast.makeText(getApplicationContext(), "Indoor Facilities are limited to just today's date ", Toast.LENGTH_SHORT).show();
        }
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateString = format1.format(c.getTime());
        BtnDate.setText(currentDateString);
    }

    @Override
    public void ProcessFinish(String output, JSONObject jsonObject, String Identity) {
        if (jsonObject != null) {
            String result = "";
//            facilityModelArrayList = new ArrayList<>();
            try {
                if (Identity.equalsIgnoreCase("CheckAvailability")) {


                    JSONArray jsonArray = jsonObject.getJSONArray("content");
                    if (jsonArray.length() != 0) {
                        FacilityModel facilityModel = new FacilityModel();
                        JSONObject json = jsonArray.getJSONObject(0);
                        facilityModel.setDeposit(Integer.parseInt((json.getString("deposit"))));
                        facilityModel.setFacilitycode((json.getString("facilitycode")));
                        facilityModel.setFacilityname((json.getString("name")));
                        facilityModel.setFacilitydescription((json.getString("description")));
                        facilityModel.setDate(json.getString("date"));
                        facilityModel.setStartTime(json.getString("start"));
                        facilityModel.setEndTime(json.getString("end"));

                        String type = "ModifyReservation";
                        StringBuilder stringBuilder = new StringBuilder();

                        //mohammedmurtuzabhaiji.uta.cloud/se1project/user/modify_reservation.php
                        // ?facilitycode=OVBC1&date=2019-11-30&start=12:00&end=14:00&reservationid=4
                        stringBuilder.append("user/modify_reservation.php?facilitycode=").append(facilityModel.getFacilitycode());
                        stringBuilder.append("&date=").append(BtnDate.getText().toString());
                        stringBuilder.append("&start=").append(BtnTime.getText().toString());
//                        stringBuilder.append("&end=").append(reservationModel.getEndTime());
                        stringBuilder.append("&reservationid=").append(reservationModel.getReservationid());
                        String url = stringBuilder.toString();
                        new GetRequests(ModifyReservation.this, url, ModifyReservation.this, "ModifyReservation").execute("");
                    } else {
                        Toast.makeText(this, "Nothing Available at this specific time.!!", Toast.LENGTH_SHORT).show();

                    }

                } else if (Identity.equalsIgnoreCase("ModifyReservation")) {
                    result = jsonObject.getString("content");

                    if (result.equalsIgnoreCase("True")) {
                        Toast.makeText(this, "Reservation Modified.!!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(ModifyReservation.this, ViewReservation.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
                        startActivity(i);
                        finish();
                    }
                } else {
                    result = jsonObject.getString("response_desc");

                    if (result.equalsIgnoreCase("OK")) {
                        Toast.makeText(this, "Reservation Cancelled.!!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(ModifyReservation.this, ViewReservation.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
                        startActivity(i);
                        finish();

                    }
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
