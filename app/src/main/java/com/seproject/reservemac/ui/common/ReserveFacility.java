package com.seproject.reservemac.ui.common;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.seproject.reservemac.R;

public class ReserveFacility extends AppCompatActivity {

    Button BtnMakeReservation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_facility);

        BtnMakeReservation = (Button) findViewById(R.id.BtnMakeReservation);
        BtnMakeReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ReserveFacility.this, "Facility Successfully Reserved..!!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
