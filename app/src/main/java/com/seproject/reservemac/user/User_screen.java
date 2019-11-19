package com.seproject.reservemac.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.seproject.reservemac.R;
import com.seproject.reservemac.SearchFacilityActivity;
import com.seproject.reservemac.mainscreens.ChangePassword;
import com.seproject.reservemac.ui.login.LoginActivity;

public class User_screen extends AppCompatActivity {


    TextView username = null;
    Button BtnSearchFacility;
    Button signout = null;
    Button BtnChangePassword = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_screen);

        username = findViewById(R.id.username);
        signout = findViewById(R.id.signout);
        BtnChangePassword = findViewById(R.id.BtnChangePassword);
        BtnSearchFacility = findViewById(R.id.BtnSearchFacility);
        signout = findViewById(R.id.signout);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent1 = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(myintent1);
            }
        });

        username.setText("Welcome "+getIntent().getStringExtra("username"));

        BtnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(getBaseContext(), ChangePassword.class);
                myintent.putExtra("username",username.getText().toString());
                startActivity(myintent);
            }
        });

        BtnSearchFacility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(getBaseContext(), SearchFacilityActivity.class);
                startActivity(myintent);
            }
        });



    }
}
