package com.seproject.reservemac.facility_manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.seproject.reservemac.R;
import com.seproject.reservemac.mainscreens.ChangePassword;
import com.seproject.reservemac.model.UserModel;
import com.seproject.reservemac.ui.login.LoginActivity;

public class Facility_manager_screen extends AppCompatActivity {

    TextView username = null;

    Button signout = null;
    Button BtnChangePassword = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facility_manager_screen);

        username = findViewById(R.id.username);
        signout = findViewById(R.id.signout);
        BtnChangePassword = findViewById(R.id.BtnChangePassword);
        signout = findViewById(R.id.signout);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent1 = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(myintent1);
            }
        });
        UserModel usermodel = (UserModel)getIntent().getParcelableExtra("usermodel");


        username.setText("Welcome "+usermodel.getUsername());

        BtnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(getBaseContext(), ChangePassword.class);
                myintent.putExtra("username",username.getText().toString());
                startActivity(myintent);
            }
        });
    }
}
