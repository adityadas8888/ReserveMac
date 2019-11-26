package com.seproject.reservemac.facility_manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.seproject.reservemac.R;
import com.seproject.reservemac.background.GetRequests;
import com.seproject.reservemac.model.FacilityModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Facility_manager_reservation extends AppCompatActivity implements GetRequests.AsyncResponse {

    Button DeleteReservation = null;
    Button ReportViolation = null;
    TextView ReservationId;
    TextView username;
    TextView FacilityCode;
    TextView Bookingtime;
    String reservationId = "";
    Button Violationsubmit;
    EditText violationdetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation_details);

        DeleteReservation = findViewById(R.id.BtnDltReservation);
        ReportViolation = findViewById(R.id.BtnReportVio);
        ReservationId = findViewById(R.id.reservationid);
        username = findViewById(R.id.username);
        FacilityCode = findViewById(R.id.FacilityCode);
        Bookingtime = findViewById(R.id.reservationdates);
        Violationsubmit = findViewById(R.id.BtnViolaton);
        violationdetails = findViewById(R.id.Violatiodetails);
        Violationsubmit.setVisibility(View.INVISIBLE);
        violationdetails.setVisibility(View.INVISIBLE);
        reservationId = getIntent().getStringExtra("ReservationId");

        loadData();

        DeleteReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                http://mohammedmurtuzabhaiji.uta.cloud/se1project/fm/delete_reservation.php?reservationid=102

                String type = "DeleteReservation";
                StringBuilder stringBuilder = new StringBuilder();
                final FacilityModel facilityModel = getIntent().getParcelableExtra("facilityModel");
                stringBuilder.append("fm/delete_reservation.php?reservationid=").append(reservationId);

                String url = stringBuilder.toString();
                new GetRequests(Facility_manager_reservation.this, url, Facility_manager_reservation.this, "DeleteReservation").execute("");


            }
        });

        ReportViolation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReportViolation.setVisibility(View.INVISIBLE);
                Violationsubmit.setVisibility(View.VISIBLE);
                violationdetails.setVisibility(View.VISIBLE);
                Violationsubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//               http://mohammedmurtuzabhaiji.uta.cloud/se1project/fm/report_violation.php?
//               reservationid=36&viodetails=damaged
                        String violations = violationdetails.getText().toString();
                        String type = "ReportViolation";
                        StringBuilder stringBuilder = new StringBuilder();
                        final FacilityModel facilityModel = getIntent().getParcelableExtra("facilityModel");
                        stringBuilder.append("fm/report_violation.php?reservationid=").append(reservationId);
                        stringBuilder.append("&viodetails=").append(violations);
                        String url = stringBuilder.toString();
                        new GetRequests(Facility_manager_reservation.this, url, Facility_manager_reservation.this, "ReportViolation").execute("");


                    }
                });

            }
        });
    }

    private void loadData() {

//        http://mohammedmurtuzabhaiji.uta.cloud/se1project/fm/search_reservation.php?reservationid=2

        String type = "SearchReservation";
        StringBuilder stringBuilder = new StringBuilder();
        final FacilityModel facilityModel = getIntent().getParcelableExtra("facilityModel");
        stringBuilder.append("fm/search_reservation.php?reservationid=").append(reservationId);

        String url = stringBuilder.toString();
        new GetRequests(Facility_manager_reservation.this, url, Facility_manager_reservation.this, "SearchReservation").execute("");


    }

    @Override
    public void ProcessFinish(String output, JSONObject jsonObject, String Identity) {

        if (jsonObject != null) {

            if (Identity.equalsIgnoreCase("SearchReservation")) {

                try {
                    JSONArray jsonContent = jsonObject.getJSONArray("content");
                    JSONObject json = jsonContent.getJSONObject(0);
                    ReservationId.setText(String.valueOf(json.getString("reservationid")));
                    username.setText(String.valueOf(json.getString("username")));
                    FacilityCode.setText(String.valueOf(json.getString("name")));
                    Bookingtime.setText(String.valueOf(json.getString("time")));


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (Identity.equalsIgnoreCase("DeleteReservation")) {
                try {
                    String resp = jsonObject.getString("response_code");
                    if (resp.equalsIgnoreCase("OK")) {
                        Toast.makeText(this, "Reservation Deleted.", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Facility_manager_reservation.this, SearchReservation.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//**Change Here**
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(this, "Reservation not updated.", Toast.LENGTH_SHORT).show();

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                String resp = "";
                try {
                    resp = jsonObject.getString("response_code");

                    if (resp.equalsIgnoreCase("OK")) {
                        Toast.makeText(this, "Reservation Deleted.", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Facility_manager_reservation.this, SearchReservation.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//**Change Here**
                        startActivity(i);
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }


    }
}
