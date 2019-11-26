package com.seproject.reservemac.facility_manager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.seproject.reservemac.R;
import com.seproject.reservemac.background.GetRequests;
import com.seproject.reservemac.model.FacilityModel;

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
        ReportViolation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReportViolation.setVisibility(View.INVISIBLE);
                Violationsubmit.setVisibility(View.VISIBLE);
                violationdetails.setVisibility(View.VISIBLE);
                Violationsubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String violations = violationdetails.getText().toString();
                        String type = "ReportViolation";
                        StringBuilder stringBuilder = new StringBuilder();
                        final FacilityModel facilityModel = getIntent().getParcelableExtra("facilityModel");
                        stringBuilder.append("fm/report_violation.php?reservationid=").append(reservationId);
                        stringBuilder.append("&viodetails=").append(violations);
                        String url = stringBuilder.toString();
                        new GetRequests(Facility_manager_reservation.this, url, Facility_manager_reservation.this, "report violations").execute("");


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
        new GetRequests(Facility_manager_reservation.this, url, Facility_manager_reservation.this, "Facility_manager_reservation").execute("");


    }

    @Override
    public void ProcessFinish(String output, JSONObject jsonObject, String Identity) {

        if (jsonObject != null) {

            try {
                JSONObject jsonContent = jsonObject.getJSONObject("content");
                ReservationId.setText(String.valueOf(jsonContent.getString("reservationid")));
                username.setText(String.valueOf(jsonContent.getString("username")));
                FacilityCode.setText(String.valueOf(jsonContent.getString("name")));
                Bookingtime.setText(String.valueOf(jsonContent.getString("time")));


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


    }
}
