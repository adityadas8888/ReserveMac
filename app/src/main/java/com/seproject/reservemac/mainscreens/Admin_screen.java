package com.seproject.reservemac.mainscreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.seproject.reservemac.R;
import com.seproject.reservemac.ui.login.LoginActivity;

public class Admin_screen extends AppCompatActivity {


    TextView username = null;

    Button signout = null;
    Button BtnChangePassword = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_screen);

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

        username.setText("Welcome "+getIntent().getStringExtra("username"));


        BtnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ChangePassword.class);
                intent.putExtra("username",username.getText().toString());
                startActivity(intent);
            }
        });

    }
}
