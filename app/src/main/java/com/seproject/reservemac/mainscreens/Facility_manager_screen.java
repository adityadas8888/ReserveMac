package com.seproject.reservemac.mainscreens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.seproject.reservemac.R;

public class Facility_manager_screen extends AppCompatActivity {

    EditText username = null;

    Button signout = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facility_manager_screen);

        username = findViewById(R.id.username);
        signout = findViewById(R.id.signout);
    }
}
