package com.seproject.reservemac.facility_manager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.seproject.reservemac.R;
import com.seproject.reservemac.model.UserModel;
import com.seproject.reservemac.ui.common.SearchFacilityActivity;
import com.seproject.reservemac.ui.login.LoginActivity;
import com.seproject.reservemac.user.view_profile_user;

public class Facility_manager_screen extends AppCompatActivity {

    TextView username = null;
    Button SearchFacility = null;
    Button searchUser =null;
    Button signout = null;
    Button ViewProfile = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facility_manager_screen);
        username = findViewById(R.id.username);
        signout = findViewById(R.id.signout);
        SearchFacility = findViewById(R.id.BtnFacilitySearch);
        signout = findViewById(R.id.signout);
        searchUser = findViewById(R.id.search_user);
        ViewProfile = findViewById(R.id.BtnViewProfile);
        final UserModel usermodel = (UserModel)getIntent().getParcelableExtra("usermodel");
        username.setText(usermodel.getUsername());

        ViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), view_profile_user.class);
                intent.putExtra("usermodel", (Parcelable) usermodel);
                startActivity(intent);
            }
        });

        SearchFacility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchFacilityActivity.class);
                intent.putExtra("usermodel", (Parcelable) usermodel);
                startActivity(intent);
            }
        });


        searchUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SearchUserActivity.class);
                startActivity(intent);
            }
        });
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
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
                        Intent intent = new Intent(Facility_manager_screen.this,LoginActivity.class);
                        startActivity(intent);
                        Facility_manager_screen.this.finish();
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
