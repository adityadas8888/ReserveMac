package com.seproject.reservemac.user;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.seproject.reservemac.R;
import com.seproject.reservemac.model.UserModel;
import com.seproject.reservemac.ui.common.SearchFacilityActivity;
import com.seproject.reservemac.ui.common.ViewReservation;
import com.seproject.reservemac.ui.common.ViewViolations;
import com.seproject.reservemac.ui.login.LoginActivity;

public class User_screen extends AppCompatActivity {


    TextView Txtusername;
    Button BtnSearchFacility, BtnReservation, signout, BtnViewProfile,BtnViewViolation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_screen);
        final UserModel usermodel = (UserModel) getIntent().getParcelableExtra("usermodel");
        String username = usermodel.getUsername();

        Txtusername = (TextView) findViewById(R.id.Txtusername);
        signout = findViewById(R.id.signout);
        BtnSearchFacility = findViewById(R.id.BtnSearchFacility);
        BtnReservation = findViewById(R.id.BtnReservation);
        BtnViewProfile = findViewById(R.id.BtnViewProfile);
        BtnViewViolation = findViewById(R.id.BtnViewViolation);
        signout = findViewById(R.id.signout);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        Toast.makeText(getApplicationContext(), "Logged in successfully " , Toast.LENGTH_SHORT).show();
        Txtusername.setText(username);


        BtnSearchFacility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(getBaseContext(), SearchFacilityActivity.class);
                myintent.putExtra("usermodel", (Parcelable) usermodel);
                startActivity(myintent);
            }
        });

        BtnViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(User_screen.this, view_profile_user.class);
                myintent.putExtra("usermodel", (Parcelable) usermodel);
                startActivity(myintent);
            }
        });

        BtnReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(User_screen.this, ViewReservation.class);
                myintent.putExtra("usermodel", (Parcelable) usermodel);
                startActivity(myintent);
            }
        });
        BtnViewViolation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(User_screen.this, ViewViolations.class);
                myintent.putExtra("usermodel", (Parcelable) usermodel);
                startActivity(myintent);
            }
        });

    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to Logout?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(User_screen.this,LoginActivity.class);
                        startActivity(intent);
                        User_screen.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }
}
