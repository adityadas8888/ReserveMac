package com.seproject.reservemac.user;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.seproject.reservemac.R;
import com.seproject.reservemac.model.FacilityModel;

public class ReserveFacility extends AppCompatActivity {

    Button BtnMakeReservation;
    TextView FacilityName;
    TextView FacilityCode;
    TextView FacilityDescription;
    TextView Date;
    TextView StartTime, EndTime;
    TextView Deposit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_facility);

        final FacilityModel facilityModel = getIntent().getParcelableExtra("facilityModel");

        BtnMakeReservation = findViewById(R.id.BtnMakeReservation);

        FacilityName = findViewById(R.id.TxtFName);
        FacilityName.setText(facilityModel.getFacilityname());

        FacilityCode = findViewById(R.id.TxtFCode);
        FacilityCode.setText(facilityModel.getFacilitycode());

        Date = findViewById(R.id.TxtDate);
        Date.setText(getIntent().getStringExtra("date"));/// fix this

        StartTime = findViewById(R.id.TxtStartTime);
        StartTime.setText(facilityModel.getStartTime());/// fix this

        EndTime = findViewById(R.id.TxtEndTime);
        EndTime.setText(facilityModel.getEndTime());/// fix this

        Deposit = findViewById(R.id.TxtDeposit);
        Deposit.setText(String.valueOf(facilityModel.getDeposit()));/// fix this


        BtnMakeReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ReserveFacility.this, "Facility Successfully Reserved..!!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
