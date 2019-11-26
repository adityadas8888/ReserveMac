package com.seproject.reservemac.facility_manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.seproject.reservemac.R;
import com.seproject.reservemac.ui.login.LoginActivity;

public class SearchReservation extends AppCompatActivity {

    Button SearchReservation;
    EditText ReservationId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_reservation_fm);
        SearchReservation = findViewById(R.id.button);
        ReservationId = findViewById(R.id.TxtReservationID);

        SearchReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Facility_manager_reservation.class);
                startActivity(intent);
            }
        });
    }
}
