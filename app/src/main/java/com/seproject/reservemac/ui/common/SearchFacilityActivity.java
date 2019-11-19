package com.seproject.reservemac.ui.common;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.seproject.reservemac.R;

public class SearchFacilityActivity extends AppCompatActivity {

    Button BtnSearchFacility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_facility);

        BtnSearchFacility = (Button) findViewById(R.id.BtnSearchFacility);
        BtnSearchFacility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(getBaseContext(), ReserveFacility.class);
                startActivity(myintent);
            }
        });


    }
}
