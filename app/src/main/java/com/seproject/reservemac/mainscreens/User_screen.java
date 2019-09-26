package com.seproject.reservemac.mainscreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.seproject.reservemac.R;

public class User_screen extends AppCompatActivity {


    TextView username = null;

    Button signout = null;
    Button BtnChangePassword = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_screen);

        username = findViewById(R.id.username);
        signout = findViewById(R.id.signout);
        BtnChangePassword = findViewById(R.id.BtnChangePassword);

        username.setText("Welcome "+getIntent().getStringExtra("username"));

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
