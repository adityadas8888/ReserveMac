package com.seproject.reservemac.user;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.seproject.reservemac.R;
import com.seproject.reservemac.model.ReservationModel;

public class ReserveFacility extends AppCompatActivity {

    Button BtnMakeReservation;
    TextView FacilityName;
    TextView FacilityCode;
    TextView FacilityDescription;
    TextView Date;
    TextView Time;
    TextView Deposit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_facility);

        final ReservationModel reservationModel =  getIntent().getParcelableExtra("reservationmodel");

        BtnMakeReservation =  findViewById(R.id.BtnMakeReservation);

        FacilityName = findViewById(R.id.TxtFName);
        FacilityName.setText(reservationModel.getFacilityName());

        FacilityCode = findViewById(R.id.TxtFCode);
        FacilityCode.setText(reservationModel.getFacilitycode());

        Date = findViewById(R.id.TxtDate);
        Date.setText(reservationModel.getDatetime());/// fix this

        Time = findViewById(R.id.TxtInterval);
        Time.setText(reservationModel.getDatetime());/// fix this

        Deposit = findViewById(R.id.TxtDeposit);
//        Deposit.setText(reservationModel.getDeposit());/// fix this


        BtnMakeReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ReserveFacility.this, "Facility Successfully Reserved..!!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
