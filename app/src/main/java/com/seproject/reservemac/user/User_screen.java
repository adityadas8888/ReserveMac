package com.seproject.reservemac.user;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.seproject.reservemac.R;
import com.seproject.reservemac.model.UserModel;
import com.seproject.reservemac.ui.common.SearchFacilityActivity;
import com.seproject.reservemac.mainscreens.ChangePassword;
import com.seproject.reservemac.ui.login.LoginActivity;

public class User_screen extends AppCompatActivity {


    TextView Txtusername;
    Button BtnSearchFacility;
    Button signout;

    Button BtnViewProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_screen);
        final UserModel usermodel = (UserModel)getIntent().getParcelableExtra("usermodel");
        String username = usermodel.getUsername();

        Txtusername = (TextView) findViewById(R.id.Txtusername);
        signout = findViewById(R.id.signout);
        BtnSearchFacility = findViewById(R.id.BtnSearchFacility);
        BtnViewProfile = findViewById(R.id.BtnViewProfile);
        signout = findViewById(R.id.signout);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent1 = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(myintent1);
            }
        });
        Toast.makeText(User_screen.this, "Logged in username: " + username, Toast.LENGTH_SHORT).show();
        Txtusername.setText(username);



        BtnSearchFacility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(getBaseContext(), SearchFacilityActivity.class);
                startActivity(myintent);
            }
        });

        BtnViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(User_screen.this, view_profile_user.class);
                myintent.putExtra("usermodel",(Parcelable)usermodel);
                startActivity(myintent);
            }
        });

    }
}
