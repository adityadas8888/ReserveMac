package com.seproject.reservemac.facility_manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.seproject.reservemac.R;

public class SearchReservation extends AppCompatActivity {

    Button BtnSearchReservation;
    EditText TxtReservationID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_reservation_fm);
        BtnSearchReservation = findViewById(R.id.BtnSearchReservation);
        TxtReservationID = findViewById(R.id.TxtReservationID);

        BtnSearchReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Facility_manager_reservation.class);
                intent.putExtra("ReservationId", TxtReservationID.getText().toString());
                startActivity(intent);
            }
        });
    }
}
